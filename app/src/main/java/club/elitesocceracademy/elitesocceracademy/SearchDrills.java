package club.elitesocceracademy.elitesocceracademy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class SearchDrills extends AddDrills {

    SearchView searchView;
    ListView listView;
    String[] drill_names = {
            "Power Shooting",
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
            "Small Sided Transition Game"
    };
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_drills);

        listView = (ListView)findViewById(R.id.Drills_list);
        searchView = (SearchView)findViewById(R.id.Search_bar);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                descPopUp(position);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    public void descPopUp(int position){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(desc.get(position));
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
}