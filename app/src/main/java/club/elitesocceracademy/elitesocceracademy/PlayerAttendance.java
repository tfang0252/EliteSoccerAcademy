package club.elitesocceracademy.elitesocceracademy;

/**
 * Created by Tony on 3/12/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by zfred on 3/12/2018.
 */

public class PlayerAttendance extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerattendance);

        Button SubmitButton = (Button)findViewById(R.id.SubmitButton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayerAttendance.this, PracticeMainMenu.class));
            }
        });
    }
}