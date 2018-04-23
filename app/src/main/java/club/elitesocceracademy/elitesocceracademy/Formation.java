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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Formation extends GameTimer {

    private static String player;
    private static String playerName;
    private ArrayList<String> onPlayers;
    private HashMap<String, Boolean> visibilityMap = CustomFormation.visibilityMap;
    private HashMap<String, ArrayList<Float>> posMap = CustomFormation.posMap;
    private Button addButton;
    private Button removeButton;
    private Button SaveButton;
    private Button startGame;
    private Button returnBtn;
    private TextView textView_0,textView_1,textView_2,textView_3,textView_4,textView_5,textView_6,textView_7
            ,textView_8,textView_9,textView_10;
    int counter =0;
    ImageButton touchView0,touchView1,touchView2, touchView3, touchView4, touchView5,
            touchView6, touchView7, touchView8, touchView9, touchView10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_formation);
        startGame = findViewById(R.id.StartGameButton);
        returnBtn = findViewById(R.id.BackButton);
        startGame.setVisibility(View.VISIBLE);
        returnBtn.setVisibility(View.VISIBLE);
        touchView0 = findViewById(R.id.ImageView00);
        touchView1 = findViewById(R.id.ImageView01);
        touchView2 = findViewById(R.id.ImageView02);
        touchView3 = findViewById(R.id.ImageView03);
        touchView4 = findViewById(R.id.ImageView04);
        touchView5 = findViewById(R.id.ImageView05);
        touchView6 = findViewById(R.id.ImageView06);
        touchView7 = findViewById(R.id.ImageView07);
        touchView8 = findViewById(R.id.ImageView08);
        touchView9 = findViewById(R.id.ImageView09);
        touchView10 = findViewById(R.id.ImageView10);
        textView_0 = findViewById(R.id.textView_0);
        textView_1 = findViewById(R.id.textView_1);
        textView_2 = findViewById(R.id.textView_2);
        textView_3 = findViewById(R.id.textView_3);
        textView_4 = findViewById(R.id.textView_4);
        textView_5 = findViewById(R.id.textView_5);
        textView_6 = findViewById(R.id.textView_6);
        textView_7 = findViewById(R.id.textView_7);
        textView_8 = findViewById(R.id.textView_8);
        textView_9 = findViewById(R.id.textView_9);
        textView_10 = findViewById(R.id.textView_10);


        addButton = findViewById(R.id.addPosButton);
        removeButton = findViewById(R.id.removePosButton);
        SaveButton = findViewById(R.id.saveButton);


        for (String pos : visibilityMap.keySet()) {
            if (pos == "touchView0" && visibilityMap.get(pos) == true) {
                touchView0.bringToFront();
                touchView0.setX(posMap.get(pos).get(0));
                touchView0.setY(posMap.get(pos).get(1));
                touchView0.setVisibility(View.VISIBLE);
                textView_0.bringToFront();
                textView_0.setX(posMap.get(pos).get(0));
                textView_0.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView1" && visibilityMap.get(pos) == true) {
                touchView1.bringToFront();
                touchView1.setX(posMap.get(pos).get(0));
                touchView1.setY(posMap.get(pos).get(1));
                touchView1.setVisibility(View.VISIBLE);
                textView_1.bringToFront();
                textView_1.setX(posMap.get(pos).get(0));
                textView_1.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView2" && visibilityMap.get(pos) == true) {
                touchView2.bringToFront();
                touchView2.setX(posMap.get(pos).get(0));
                touchView2.setY(posMap.get(pos).get(1));
                touchView2.setVisibility(View.VISIBLE);
                textView_2.bringToFront();
                textView_2.setX(posMap.get(pos).get(0));
                textView_2.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView3" && visibilityMap.get(pos) == true) {
                touchView3.bringToFront();
                touchView3.setX(posMap.get(pos).get(0));
                touchView3.setY(posMap.get(pos).get(1));
                touchView3.setVisibility(View.VISIBLE);
                textView_3.bringToFront();
                textView_3.setX(posMap.get(pos).get(0));
                textView_3.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView4" && visibilityMap.get(pos) == true) {
                touchView4.bringToFront();
                touchView4.setX(posMap.get(pos).get(0));
                touchView4.setY(posMap.get(pos).get(1));
                touchView4.setVisibility(View.VISIBLE);
                textView_4.bringToFront();
                textView_4.setX(posMap.get(pos).get(0));
                textView_4.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView5" && visibilityMap.get(pos) == true) {
                touchView5.bringToFront();
                touchView5.setX(posMap.get(pos).get(0));
                touchView5.setY(posMap.get(pos).get(1));
                touchView5.setVisibility(View.VISIBLE);
                textView_5.bringToFront();
                textView_5.setX(posMap.get(pos).get(0));
                textView_5.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView6" && visibilityMap.get(pos) == true) {
                touchView6.bringToFront();
                touchView6.setX(posMap.get(pos).get(0));
                touchView6.setY(posMap.get(pos).get(1));
                touchView6.setVisibility(View.VISIBLE);
                textView_6.bringToFront();
                textView_6.setX(posMap.get(pos).get(0));
                textView_6.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView7" && visibilityMap.get(pos) == true) {
                touchView7.bringToFront();
                touchView7.setX(posMap.get(pos).get(0));
                touchView7.setY(posMap.get(pos).get(1));
                touchView7.setVisibility(View.VISIBLE);
                textView_7.bringToFront();
                textView_7.setX(posMap.get(pos).get(0));
                textView_7.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView8" && visibilityMap.get(pos) == true) {
                touchView8.bringToFront();
                touchView8.setX(posMap.get(pos).get(0));
                touchView8.setY(posMap.get(pos).get(1));
                touchView8.setVisibility(View.VISIBLE);
                textView_8.bringToFront();
                textView_8.setX(posMap.get(pos).get(0));
                textView_8.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView9" && visibilityMap.get(pos) == true) {
                touchView9.bringToFront();
                touchView9.setX(posMap.get(pos).get(0));
                touchView9.setY(posMap.get(pos).get(1));
                touchView9.setVisibility(View.VISIBLE);
                textView_9.bringToFront();
                textView_9.setX(posMap.get(pos).get(0));
                textView_9.setY(posMap.get(pos).get(1));
            }
            if (pos == "touchView10" && visibilityMap.get(pos) == true) {
                touchView10.bringToFront();
                touchView10.setX(posMap.get(pos).get(0));
                touchView10.setY(posMap.get(pos).get(1));
                touchView10.setVisibility(View.VISIBLE);
                textView_10.bringToFront();
                textView_10.setX(posMap.get(pos).get(0));
                textView_10.setY(posMap.get(pos).get(1));
            }
        }

        addButton.setVisibility(View.INVISIBLE);
        removeButton.setVisibility(View.INVISIBLE);
        SaveButton.setVisibility(View.INVISIBLE);

        for (String players : list) {
            adapter.onNames.add(players);
            adapter.autoSwitch.put(players, false);
        }


        Button BackButton = (Button) findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Formation.this, GameMainMenu.class));

            }
        });

        Button StartGameButton = (Button) findViewById(R.id.StartGameButton);
        StartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Formation.this, GameTimer.class));
            }
        });

        touchView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_0);
            }

        });

        touchView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_1);
            }

        });

        touchView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_2);
            }

        });

        touchView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_3);
            }

        });
        touchView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_4);
            }

        });  touchView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_5);
            }

        });  touchView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_6);
            }

        });  touchView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_7);
            }

        });  touchView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_8);
            }

        });  touchView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_9);
            }

        });  touchView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogButtonClicked(view, textView_10);
            }

        });

//        ImageButton rightStrikerButton = (ImageButton)findViewById(R.id.RightStriker);
//        final TextView rightStrikerText = (TextView) findViewById(R.id.rightStrikerText);
//        rightStrikerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String position = "Right Striker";
//                showAlertDialogButtonClicked(position,view,rightStrikerText);
//            }
//        });
//        ImageButton centerMiddleButton = (ImageButton)findViewById(R.id.CenterMiddle);
//        final TextView centerMidText = (TextView) findViewById(R.id.centerMidText);
//        centerMiddleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String position = "Center Middle";
//                showAlertDialogButtonClicked(position,view,centerMidText);
//            }
//        });
//        ImageButton leftMiddleButton = (ImageButton)findViewById(R.id.LeftMiddle);
//        final TextView leftMiddleText = (TextView) findViewById(R.id.leftMidText);
//        leftMiddleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String position = "Left Middle";
//                showAlertDialogButtonClicked(position,view,leftMiddleText);
//            }
//        });
//        ImageButton rightMiddleButton = (ImageButton)findViewById(R.id.RightMiddle);
//        final TextView rightMiddleText = (TextView) findViewById(R.id.rightMidText);
//        rightMiddleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String position = "Right Middle";
//                showAlertDialogButtonClicked(position,view,rightMiddleText);
//            }
//        });
//        ImageButton leftDefenderButton = (ImageButton)findViewById(R.id.LeftDefender);
//        final TextView leftDefText = (TextView) findViewById(R.id.leftDefText);
//        leftDefenderButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String position = "Left Defender";
//                showAlertDialogButtonClicked(position,view,leftDefText);
//            }
//        });
//        ImageButton rightDefenderButton = (ImageButton)findViewById(R.id.RightDefender);
//        final TextView rightDefText = (TextView) findViewById(R.id.rightDefText);
//        rightDefenderButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String position = "Right Defender";
//                showAlertDialogButtonClicked(position,view,rightDefText);
//            }
//        });
//        ImageButton centerDefenderButton = (ImageButton)findViewById(R.id.CenterDefender);
//        final TextView centerDefText = (TextView) findViewById(R.id.CenterDefText);
//        centerDefenderButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String position = "Center Defender";
//                showAlertDialogButtonClicked(position,view,centerDefText);
//            }
//        });
//        final ImageButton GoalieButton = (ImageButton)findViewById(R.id.Goalie);
//        final TextView goalieText = (TextView) findViewById(R.id.goalieText);
//        GoalieButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String position = "Goalie";
//                showAlertDialogButtonClicked(position,view,goalieText);
//            }
//        });
//
   }
//
        public void showAlertDialogButtonClicked (View view,final TextView text){


            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose a player");


            final String[] myArray = list.toArray(new String[list.size()]);
            player = myArray[0];
            int checkedItem = 0; // cow
            builder.setSingleChoiceItems(myArray, checkedItem, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //System.out.println(myArray[which]);
                    player = myArray[which];
                    playerName = myArray[which];
                    String lastName = "";
                    String firstName = "";
                    if (player.split("\\w+").length > 1) {

                        lastName = player.substring(player.lastIndexOf(" ") + 1);
                        firstName = player.substring(0, 1) + ". ";
                    } else {
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
                    adapter.autoSwitch.put(playerName, true);
                    //adapter.onNames.add(playerName);

                    //System.out.println(adapter.autoSwitch.get(playerName));
                }
            });
            builder.setNegativeButton("Cancel", null);

// create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }

}