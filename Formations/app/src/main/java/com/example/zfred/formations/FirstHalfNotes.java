package com.example.zfred.formations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by zfred on 3/12/2018.
 */

public class FirstHalfNotes extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamenotes);

        Button StartNextHalfButton = (Button)findViewById(R.id.EndGameButton);
        StartNextHalfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstHalfNotes.this, GameTimer.class));
            }
        });
    }
}
