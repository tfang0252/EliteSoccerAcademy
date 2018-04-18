package club.elitesocceracademy.elitesocceracademy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class SearchDrills extends AppCompatActivity {

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
                android.R.layout.simple_list_item_1, drill_names);

        listView.setAdapter(adapter);

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
}