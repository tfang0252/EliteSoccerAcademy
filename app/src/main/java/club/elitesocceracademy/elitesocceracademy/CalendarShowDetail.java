package club.elitesocceracademy.elitesocceracademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

//
//    public class CalendarShowDetail extends AppCompatActivity {
//
//        private static final String TAG = "CalendarShowDetail";
//
//        private TextView theDate;
//        private Button btnGoCalendar;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);//please add to xml
//            theDate = (TextView) findViewById(R.id.date);
//            btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);
//
//            Intent incomingIntent = getIntent();
//            String date = incomingIntent.getStringExtra("date");
//            theDate.setText(date);
//
//            btnGoCalendar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(CalendarShowDetail.this, CalendarActivity.class);
//                    startActivity(intent);
//                }
//            });
//        }
//    }


//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;

public class CalendarShowDetail extends AppCompatActivity {

    private static final String TAG = "CalendarShowDetail";

    private TextView theDate;
    private Button btnGoCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callendar);
        theDate = (TextView) findViewById(R.id.date);
        btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarShowDetail.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}




