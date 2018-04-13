package club.elitesocceracademy.elitesocceracademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        rosterButton();
        gameButton();
        statButton();
        announcementButton();
        homeworkButton();
        practiceButton();
        settingsButton();
    }

    private void rosterButton() {
        ImageButton btn = (ImageButton) findViewById(R.id.rosterBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, RosterActivity.class));
            }
        });
    }

    private void gameButton() {
        ImageButton btn = (ImageButton) findViewById(R.id.gameBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, GameMainMenu.class));
            }
        });
    }

    private void statButton() {
        ImageButton btn = (ImageButton) findViewById(R.id.statBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, StatActivity.class));
            }
        });
    }

    private void announcementButton() {
        ImageButton btn = (ImageButton) findViewById(R.id.announcementBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, AnnouncementActivity.class));
            }
        });
    }

    private void homeworkButton() {
        ImageButton btn = (ImageButton) findViewById(R.id.homeworkBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, HomeworkActivity.class));
            }
        });
    }
    private void practiceButton() {
        ImageButton btn = (ImageButton) findViewById(R.id.practiceBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, PracticeMainMenu.class));
            }
        });
    }

    private void settingsButton() {
        ImageButton btn = (ImageButton) findViewById(R.id.settingsBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, AWS.class));
            }
        });
    }

}
