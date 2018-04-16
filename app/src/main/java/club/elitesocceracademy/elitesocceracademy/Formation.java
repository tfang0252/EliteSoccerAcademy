package club.elitesocceracademy.elitesocceracademy;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Formation extends GameTimer {
    private MyCustomAdapter adapter = new MyCustomAdapter(RosterActivity.list, this);
    private static String player;
    private static String playerName;
    private ArrayList<String> onPlayers;

    int counter =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation);

        for (String players : list) {
            adapter.onNames.add(players);
            adapter.autoSwitch.put(players, false);
        }


        Button BackButton = (Button)findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Formation.this, GameMainMenu.class));

            }
        });

        Button StartGameButton = (Button)findViewById(R.id.StartGameButton);
        StartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Formation.this, GameTimer.class));
            }
        });

        ImageButton leftStrikerButton = (ImageButton)findViewById(R.id.LeftStriker);
        final TextView leftStrikerText = (TextView) findViewById(R.id.leftStrikerText);
        leftStrikerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Left Striker";
                showAlertDialogButtonClicked(position,view,leftStrikerText);
            }

        });

        ImageButton rightStrikerButton = (ImageButton)findViewById(R.id.RightStriker);
        final TextView rightStrikerText = (TextView) findViewById(R.id.rightStrikerText);
        rightStrikerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Right Striker";
                showAlertDialogButtonClicked(position,view,rightStrikerText);
            }
        });
        ImageButton centerMiddleButton = (ImageButton)findViewById(R.id.CenterMiddle);
        final TextView centerMidText = (TextView) findViewById(R.id.centerMidText);
        centerMiddleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Center Middle";
                showAlertDialogButtonClicked(position,view,centerMidText);
            }
        });
        ImageButton leftMiddleButton = (ImageButton)findViewById(R.id.LeftMiddle);
        final TextView leftMiddleText = (TextView) findViewById(R.id.leftMidText);
        leftMiddleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Left Middle";
                showAlertDialogButtonClicked(position,view,leftMiddleText);
            }
        });
        ImageButton rightMiddleButton = (ImageButton)findViewById(R.id.RightMiddle);
        final TextView rightMiddleText = (TextView) findViewById(R.id.rightMidText);
        rightMiddleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Right Middle";
                showAlertDialogButtonClicked(position,view,rightMiddleText);
            }
        });
        ImageButton leftDefenderButton = (ImageButton)findViewById(R.id.LeftDefender);
        final TextView leftDefText = (TextView) findViewById(R.id.leftDefText);
        leftDefenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Left Defender";
                showAlertDialogButtonClicked(position,view,leftDefText);
            }
        });
        ImageButton rightDefenderButton = (ImageButton)findViewById(R.id.RightDefender);
        final TextView rightDefText = (TextView) findViewById(R.id.rightDefText);
        rightDefenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Right Defender";
                showAlertDialogButtonClicked(position,view,rightDefText);
            }
        });
        ImageButton centerDefenderButton = (ImageButton)findViewById(R.id.CenterDefender);
        final TextView centerDefText = (TextView) findViewById(R.id.CenterDefText);
        centerDefenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Center Defender";
                showAlertDialogButtonClicked(position,view,centerDefText);
            }
        });
        final ImageButton GoalieButton = (ImageButton)findViewById(R.id.Goalie);
        final TextView goalieText = (TextView) findViewById(R.id.goalieText);
        GoalieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = "Goalie";
                showAlertDialogButtonClicked(position,view,goalieText);
            }
        });

    }

    public void showAlertDialogButtonClicked(String position, View view, final TextView text) {


        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a " + position);


        final String[] myArray = list.toArray(new String[list.size()]);
        player = myArray[0];
        int checkedItem = 0; // cow
        builder.setSingleChoiceItems(myArray, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //System.out.println(myArray[which]);
                player = myArray[which];
                playerName=myArray[which];
                String lastName = "";
                String firstName= "";
                if(player.split("\\w+").length>1){

                    lastName = player.substring(player.lastIndexOf(" ")+1);
                    firstName = player.substring(0, 1) + ". ";
                }
                else{
                    firstName = player;
                }

                player = firstName + lastName;
            }
        });

// add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                text.setText(player);
                adapter.autoSwitch.put(playerName,true);
                //adapter.onNames.add(playerNa);

                //System.out.println(adapter.autoSwitch.get(playerName));
            }
        });
        builder.setNegativeButton("Cancel", null);

// create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}