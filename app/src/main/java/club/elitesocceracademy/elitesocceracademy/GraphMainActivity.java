package club.elitesocceracademy.elitesocceracademy;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GraphMainActivity extends AppCompatActivity {
    Random rand = new Random();
    static HashMap<String,LinkedList<HashMap<String,String>>> gameIDTimeMap = new HashMap();
    Set<Integer>  gameIdSet = new HashSet();
    List<Integer> gameTimeList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_main);
        Button btn = (Button) findViewById(R.id.return_menu);
        readCSV();
        String id = getIntent().getExtras().getString("id");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GraphMainActivity.this, StatActivity.class));
            }
        });
        TextView assist = (TextView) findViewById(R.id.assist_display);
        TextView cleanSheets = (TextView) findViewById(R.id.clean_sheet_display);
        TextView goals = (TextView) findViewById(R.id.goal_display);
        //TextView timePlayed = (TextView)findViewById(R.id.min_display);
        assist.setText(Integer.toString(rand.nextInt(4)));
        cleanSheets.setText(Integer.toString(rand.nextInt(3)));
        goals.setText(Integer.toString(rand.nextInt(12)));
        //timePlayed.setText(Integer.toString(rand.nextInt(24)+10));
        // GraphView graph = (GraphView) findViewById(R.id.graph);
        // first series is a line
        GraphView graph = (GraphView) findViewById(R.id.graph);
        int minutesPlayed = 0;
        int gameID = 0;

        DataPoint[] dataPoints = new DataPoint[gameIDTimeMap.get(id).size()]; // declare an array of DataPoint objects with the same size as your list


            for(int i =0;i<gameIDTimeMap.get(id).size();i++){
                for(String GID : gameIDTimeMap.get(id).get(i).keySet()){
                    gameID = Integer.parseInt(GID);
                    minutesPlayed = Integer.parseInt(gameIDTimeMap.get(id).get(i).get(GID));
                    dataPoints[i] = new DataPoint(gameID,minutesPlayed);
                }
            }





        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
        series.setDrawDataPoints(true);
        series.setColor(Color.RED);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(5);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraphMainActivity.this, "Time Played: "
                        + dataPoint.getY() + " minutes.", Toast.LENGTH_SHORT).show();
            }
        });


        graph.setTitle("Average Playing Time");
        graph.addSeries(series);


    }

    public void readCSV(){
        try{
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath2 = baseDir + File.separator + "PlayerTimes.csv";
        File f2 = new File(filePath2);
        CSVReader reader = new CSVReader(new FileReader(f2));
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            String player = nextLine[0];
            String gameID = nextLine[1];
            String gameTime = nextLine[2];

            if(gameIDTimeMap.get(player)==null){
                LinkedList<HashMap<String,String>> idTimeList = new LinkedList<>();
                gameIDTimeMap.put(player,idTimeList);
            }
                HashMap<String,String> idTimePair = new HashMap<>();
                idTimePair.put(gameID,gameTime);
                gameIDTimeMap.get(player).add(idTimePair);


            System.out.println(player + " / Game ID:" + gameID + " /Game Time:" + gameTime);
        }
            for(String player: gameIDTimeMap.keySet()){
                    System.out.println(player+"--------------");
                    for (HashMap map : gameIDTimeMap.get(player)) {
                        System.out.println("======" + player + ":" + map + "======");
                    }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } {

        }


    }



}
