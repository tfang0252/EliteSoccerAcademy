package club.elitesocceracademy.elitesocceracademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class StatActivity2 extends AppCompatActivity {
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        returnButton();
        TextView assist = (TextView)findViewById(R.id.assist_display);
        TextView cleanSheets = (TextView)findViewById(R.id.clean_sheet_display);
        TextView goals = (TextView)findViewById(R.id.goals_display);
        TextView timePlayed = (TextView)findViewById(R.id.min_display);
        assist.setText(Integer.toString(rand.nextInt(4)));
        cleanSheets.setText(Integer.toString(rand.nextInt(3)));
        goals.setText(Integer.toString(rand.nextInt(12)));
        timePlayed.setText(Integer.toString(rand.nextInt(24)+10));

    }

    private void returnButton() {
       Button btn = (Button) findViewById(R.id.return_menu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatActivity2.this, StatActivity.class));
            }
        });
    }
}