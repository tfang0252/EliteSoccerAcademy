package club.elitesocceracademy.elitesocceracademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class GameHistoryActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);


        // gets listview
        expListView = (ExpandableListView) findViewById(R.id.mobile_list);

        // list data
        prepareListData();

        listAdapter = new club.elitesocceracademy.elitesocceracademy.ExpandableListAdapter(this,
                listDataHeader, listDataChild);

        // sets list adapter
        expListView.setAdapter(listAdapter);

        // list view click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub


                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    // preparing list data
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("4/1/2018\t\t\tDutch Lions");
        listDataHeader.add("4/8/2018\t\t\tNaples Fire");
        listDataHeader.add("4/15/2018\t\t\tGPS");
        listDataHeader.add("4/22/2018\t\t\tChargers SC");
        listDataHeader.add("4/29/2018\t\t\tKey Biscayne");


        // Adding child data
        List<String> gameOne = new ArrayList<String>();
        //gameOne.add("vs. Dutch Liona");
        gameOne.add("Score: 1-0");
        //gameOne.add("View Game Notes");
        //Button button = new Button(this);
        //button.setText("Game Notes");
        //gameOne.add(button);

        List<String> gameTwo = new ArrayList<String>();
        //gameTwo.add("vs. Naples Fire");
        gameTwo.add("Score: 3-2");
        //gameTwo.add("View Game Notes");
        List<String> gameThree = new ArrayList<String>();
        //gameThree.add("vs. GPS");
        gameThree.add("Score: 2-2");
        //gameThree.add("View Game Notes");
        List<String> gameFour = new ArrayList<String>();
        //gameFour.add("vs. Chargers SC");
        gameFour.add("Score: 0-0");
        //gameFour.add("View Game Notes");
        List<String> gameFive = new ArrayList<String>();
        //gameFive.add("vs. Key Biscayne");
        gameFive.add("Score: 0-1");
        //gameFive.add("View Game Notes");
        listDataChild.put(listDataHeader.get(0), gameOne); // Header, Child data
        listDataChild.put(listDataHeader.get(1), gameTwo);
        listDataChild.put(listDataHeader.get(2), gameThree);
        listDataChild.put(listDataHeader.get(3), gameFour);
        listDataChild.put(listDataHeader.get(4), gameFive);
    }


}

