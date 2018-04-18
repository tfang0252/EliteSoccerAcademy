package club.elitesocceracademy.elitesocceracademy;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.Random;

public class GraphMainActivity extends AppCompatActivity {
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_main);
        Button btn = (Button) findViewById(R.id.return_menu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GraphMainActivity.this, StatActivity.class));
            }
        });
        TextView assist = (TextView)findViewById(R.id.assist_display);
        TextView cleanSheets = (TextView)findViewById(R.id.clean_sheet_display);
        TextView goals = (TextView)findViewById(R.id.goal_display);
        //TextView timePlayed = (TextView)findViewById(R.id.min_display);
        assist.setText(Integer.toString(rand.nextInt(4)));
        cleanSheets.setText(Integer.toString(rand.nextInt(3)));
        goals.setText(Integer.toString(rand.nextInt(12)));
        //timePlayed.setText(Integer.toString(rand.nextInt(24)+10));
        // GraphView graph = (GraphView) findViewById(R.id.graph);
        // first series is a line
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 31),
                new DataPoint(2, 27),
                new DataPoint(3, 35),
                new DataPoint(4, 34),
                new DataPoint(5, 40),
                new DataPoint(6, 43),
                new DataPoint(7, 37),
                new DataPoint(8, 33),
                new DataPoint(9, 44),
                new DataPoint(10, 12)
        });

        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
        series.setDrawDataPoints(true);
        series.setColor(Color.RED);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(5);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraphMainActivity.this,"Time Played: "
                        + dataPoint.getY() + " minutes.", Toast.LENGTH_SHORT).show();
            }
        });



        graph.setTitle("Average Playing Time");
        graph.addSeries(series);


    }


}
