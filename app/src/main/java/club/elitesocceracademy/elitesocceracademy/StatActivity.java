package club.elitesocceracademy.elitesocceracademy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;

public class StatActivity extends RosterActivity {

    String[] mobileArray = {"Vanessa Flores","Kassandra Gonzalez","Ava Hick","Skylar Hubbard",
            "Maryah Maldonado","Ellana Mena","Mackenzie Molina","Ashlyn Morales",
            "Rebekah Strickland",
            "Paige Troyer", "Sophia Uliani", "Alyssa Vargas", "Rachel Wiltgen"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, RosterActivity.list);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                startActivity(new Intent(StatActivity.this, StatActivity2.class));

            }
        });
    }



}