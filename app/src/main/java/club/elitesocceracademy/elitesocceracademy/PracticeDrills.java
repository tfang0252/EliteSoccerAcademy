package club.elitesocceracademy.elitesocceracademy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by zfred on 3/12/2018.
 */

public class PracticeDrills extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practicedrillsmain);

        Button AddDrillsButton = (Button)findViewById(R.id.AddDrillsButton);
        AddDrillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PracticeDrills.this, PracticeMainMenu.class));
            }
        });
    }
}