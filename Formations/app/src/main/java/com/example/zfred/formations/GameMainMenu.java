package com.example.zfred.formations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


/**
 * Created by zfred on 3/12/2018.
 */

public class GameMainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main_menu);

        ImageButton NewGameButton = (ImageButton) findViewById(R.id.NewGameButton);
        NewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameMainMenu.this, Formation.class));
            }
        });

        ImageButton GameHistoryButton = (ImageButton)findViewById(R.id.GameHistoryButton);
        GameHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameMainMenu.this, GameHistory.class));
            }
        });
        ImageButton EditFormationButton = (ImageButton)findViewById(R.id.EditFormationButton);
        EditFormationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameMainMenu.this, EditFormations.class));
            }
        });

    }

//    private void goToNewGame() {
//        NewGameButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(GameMainMenu.this, Formation.class));
//            }
//        });
//    }


}


