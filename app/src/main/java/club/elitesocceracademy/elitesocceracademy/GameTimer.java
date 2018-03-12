package club.elitesocceracademy.elitesocceracademy;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by zfred on 3/12/2018.
 */

public class GameTimer extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametimer);

        ImageButton StartGameButton = (ImageButton)findViewById(R.id.StartButton);
        StartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameTimer.this, FirstHalfNotes.class));
            }
        });

        ImageButton PauseButton = (ImageButton)findViewById(R.id.PauseButton);
        PauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameTimer.this, EndGameNotes.class));
            }
        });
    }
}