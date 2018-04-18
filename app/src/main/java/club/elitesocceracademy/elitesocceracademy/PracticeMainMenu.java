package club.elitesocceracademy.elitesocceracademy;

/**
 * Created by Tony on 3/12/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PracticeMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicemainmenu);

        ImageButton PracticeDrillsButton = (ImageButton)findViewById(R.id.searchDrillsBtn);
        PracticeDrillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PracticeMainMenu.this, SearchDrills.class));
            }
        });

        ImageButton PracticeAttendanceButton = (ImageButton)findViewById(R.id.addDrillsBtn);
        PracticeAttendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PracticeMainMenu.this, SearchDrills.class));
            }
        });

        ImageButton HomeButton = (ImageButton)findViewById(R.id.playerAttendanceBtn);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PracticeMainMenu.this, PlayerAttendance.class));
            }
        });
    }
}