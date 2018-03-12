package com.example.danny.stats;
//package com.example.admin.listviewexample;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] mobileArray = {"Vanessa Flores","Kassandra Gonzalez","Ava Hick","Skylar Hubbard",
            "Maryah Maldonado","Ellana Mena","Mackenzie Molina","Ashlyn Morales",
            "Rebekah Strickland",
            "Paige Troyer", "Sophia Uliani", "Alyssa Vargas", "Rachel Wiltgen"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }
}
