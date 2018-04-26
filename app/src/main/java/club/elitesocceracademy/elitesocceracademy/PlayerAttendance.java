package club.elitesocceracademy.elitesocceracademy;

/**
 * Created by Tony on 3/12/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by zfred on 3/12/2018.
 */

public class PlayerAttendance extends AppCompatActivity {

    protected AttendanceAdapter adapter = new AttendanceAdapter(RosterActivity.list, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.playerattendance);
            ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle_btn);
            final ListView listView = (ListView) findViewById(R.id.listView);


            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the selected item text from ListView
                    String selectedItem = (String) parent.getItemAtPosition(position);
                    // adapter.getTimers(selectedItem,position,playerTimers.timeLeftInSeconds);
                    //adapter.getTotalTime(selectedItem);


                }
            });


        }

    }
}