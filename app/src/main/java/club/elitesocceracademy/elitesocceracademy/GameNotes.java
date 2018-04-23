package club.elitesocceracademy.elitesocceracademy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zfred on 3/12/2018.
 */

public class GameNotes extends Formation {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
    private Date c = Calendar.getInstance().getTime();
    private Context context = this;
    private static String GameID = "1";
    private String GameDate =df.format(c);
    private static String EliteScore;
    private static String OppScore;
    private static String TeamID = "1";
    private static String OpponentTeam;
    private static String GameNotes;


    private String fileName = "GameHistory.csv";
    private String timeCSV = "PlayerTimes.csv";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamenotes);

        Button StartNextHalfButton = (Button) findViewById(R.id.StartNextHalf);
        final EditText eliteScore = (EditText) findViewById(R.id.eliteScore);
        final EditText oppScore = (EditText) findViewById(R.id.oppScore);
        final EditText oppName = (EditText) findViewById(R.id.oppName);
        final EditText notes = (EditText) findViewById(R.id.gamenotes);

       // System.out.println("Current time => " + c);
       // SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        //final String formattedDate = df.format(c);
       // GameDate = df.format(c);



        StartNextHalfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyStoragePermissions(GameNotes.this);
                try {

                    EliteScore = eliteScore.getText().toString();
                    OppScore = oppScore.getText().toString();
                    OpponentTeam =  oppName.getText().toString();
                    GameNotes = notes.getText().toString();
                    playerGameTimes();
                    writeToCSV();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                S3Upload s3Upload = new S3Upload(context,fileName,view);
                S3Upload s3Upload2 = new S3Upload(context,timeCSV,view);
            }
        });


    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void writeToCSV() throws IOException {
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

        String filePath = baseDir + File.separator + fileName;
        File f = new File(filePath);
        CSVWriter writer;
        FileWriter mFileWriter;
        if (f.exists() && !f.isDirectory()) {
            mFileWriter = new FileWriter(filePath, true);
            writer = new CSVWriter(mFileWriter);
        } else {
            writer = new CSVWriter(new FileWriter(filePath));
            String[] Headers = {"GameID","GameDate","OpponentTeam","EliteScore","OppScore","GameNotes","TeamID"};
            writer.writeNext(Headers);
        }
        String[] data = {GameID,GameDate,OpponentTeam,EliteScore,OppScore,GameNotes,TeamID};
        System.out.println(filePath);
        writer.writeNext(data);
        writer.close();

        String filePath2 = baseDir + File.separator + timeCSV;
        File f2 = new File(filePath2);
        CSVWriter writer2;
        FileWriter mFileWriter2;
        if (f2.exists() && !f2.isDirectory()) {
            mFileWriter2 = new FileWriter(filePath2, true);
            writer2 = new CSVWriter(mFileWriter2);
        } else {
            writer2 = new CSVWriter(new FileWriter(filePath2));
            String[] time = {"PlayerName","GameID","TimePlayed"};
            writer2.writeNext(time);
        }
        adapter.getTotalTime();
        System.out.println("***************" + GetTime.totalPlayerTime + "***************");
        for(String players:list) {
            if(GetTime.totalPlayerTime.get(players).get("3") != null) {
                System.out.println("######" + players);
                String[] time = {players, GameID, Integer.toString(GetTime.totalPlayerTime.get(players).get("3"))};
                System.out.println(filePath2);
                writer2.writeNext(time);
            }
        }


        writer2.close();
    }


    public void playerGameTimes(){
        //adapter.getTotalTime(GameID);
    }
}