package club.elitesocceracademy.elitesocceracademy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.Arrays;

public class AddDrills extends AppCompatActivity {
    private static final String TAG = "AddDrill";
    protected static ArrayList<String> list = new ArrayList<>(Arrays.asList("Power Shooting",
            "Shooting Accuracy",
            "Skills Warmup",
            "Fast Feet Dribbling",
            "Exchange Ball and Shoot",
            "Wall Passing",
            "Possession Passing",
            "Defending and Pressing",
            "Technical Circle",
            "Fitness Sprints",
            "First Touch Control",
            "Pass and Move ",
            "Three Team Transition Drill",
            "Transition Defense",
            "Small Sided Transition Game"));
    protected static ArrayList<String> desc = new ArrayList<>(Arrays.asList("Player passes ball to coach, coach returns ball, and player shoots with power towards the goal. Make sure player hits it with laces to get most power","The player will shoot the ball in between sets of cones with varying distances. 20 m , 50 m, 75 m.","The player will juggle the ball alternating between feet for 30 seconds each foot. Then pass to the next player in a circle and so forth.","Cones will placed on the field spaced out 3 feet apart. The player will dribble the ball going from one side to the other.","Two players will run alongside each other passing the ball on the first touch. After two passes each the receiving player will shoot to goal","The player will kick the ball towards a wall and chase after the rebound hitting the ball with the opposite foot after each rebound.","The players will form groups of 5 and form a circle. The will pass the ball around with only one touch. Alternating between their strong and weak foot.","This drill will be performed in pairs. One player will dribble the ball towards goal and the second player will defend the player and not allow a shot towards goal.","Inside a circle the players will begin to dribble around. On the coaches command, the players will do various activities such as toe taps, v rolls, 6 touches, and three taps and a roll.","The players will run suicide sprints at 10 yards, 20 yards, 30 yards, and 40 yards. Adjust the distance based off of age of the players.","Player stands in a small box and are on their toes. The coach will play the ball into the player at increasing harder pace. The player must control the ball inside the box, if they dont they are out for the drill","Players spread out in grid and pass the ball to each other. Once they pass the ball, the player should move from the position they are in to an open space.","two teams are playing in a small field. When one team scores, the third team comes onto the field with the ball and plays against the team that scored.","Defender starts behind offensive player. On the coaches call, the offensive player takes off towards the goal with the ball and the defender tries to get back into position and defend."));
    protected static ArrayList<String> time= new ArrayList<>(Arrays.asList("10 minutes","1 minute","30 seconds"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drills);
        final SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listView);





        ArrayAdapter adapter = new ArrayAdapter(AddDrills.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                descPopUp(position);
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Edit");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_trash);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
                        break;
                    case 1:
                        removeHomework(listView,position);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        Context context = v.getContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Drill");

// Set up the input
        final EditText input = new EditText(this);
        final EditText input2 = new EditText(this);

        input.setHint("Drill Name");
        input2.setHint("Description");
        input2.setSingleLine(false);  //add this
        input2.setLines(4);
        input2.setMaxLines(7);
        input2.setGravity(Gravity.LEFT | Gravity.TOP);
        input2.setHorizontalScrollBarEnabled(false); //this

// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(input);
        layout.addView(input2);

        builder.setView(layout);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String drillTitle = input.getText().toString();
                String drillDesc = input2.getText().toString();

                list.add(drillTitle);
                desc.add(drillDesc);

                System.out.println(drillTitle);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();



    }

    public void descPopUp(int position){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(desc.get(position)+ " [" + time.get(position)+"]");
        //builder1.setMessage(time.get(position));
        builder1.setTitle(list.get(position));
        builder1.setCancelable(true);



        builder1.setNegativeButton(
                "Return",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    public void removeHomework(SwipeMenuListView listView, int position){
        list.remove(position);
        desc.remove(position);
        time.remove(position);
        listView.invalidateViews();

    }
}
