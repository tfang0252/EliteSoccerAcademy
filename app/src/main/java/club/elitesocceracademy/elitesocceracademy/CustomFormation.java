package club.elitesocceracademy.elitesocceracademy;
import android.annotation.SuppressLint;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomFormation extends AppCompatActivity{

    int layoutId = R.layout.custom_formation;
    protected static HashMap<String,Boolean> visibilityMap = new HashMap<>();
    protected static HashMap<String,ArrayList<Float>> posMap = new HashMap<>();
    ImageButton touchView0,touchView1,touchView2, touchView3, touchView4, touchView5,
            touchView6, touchView7, touchView8, touchView9, touchView10;
    ImageButton button;
    Button addButton;
    Button removeButton;
    Button SaveButton;
    boolean firstClick = true;
    boolean addButtonClicked = false;
    boolean removeButtonClicked = false;
    int arrayPosi = 0;
    int arrayPosj = 0;
    ArrayList<Float> posArrList;
    static float x;
    static float y;

    //Array to store screen clicks
    float[][] playerPosition = new float[2][11];

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("layoutId", layoutId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            layoutId = savedInstanceState.getInt("layoutId", R.layout.custom_formation);
        }
        setContentView(layoutId);
        touchView0 = findViewById(R.id.ImageView00);
        touchView1 = findViewById(R.id.ImageView01);
        touchView2 = findViewById(R.id.ImageView02);
        touchView3 = findViewById(R.id.ImageView03);
        touchView4 = findViewById(R.id.ImageView04);
        touchView5 = findViewById(R.id.ImageView05);
        touchView6 = findViewById(R.id.ImageView06);
        touchView7 = findViewById(R.id.ImageView07);
        touchView8 = findViewById(R.id.ImageView08);
        touchView9 = findViewById(R.id.ImageView09);
        touchView10 = findViewById(R.id.ImageView10);



        RelativeLayout relativeLayout = new RelativeLayout(this);
        // Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //addButton = (Button)findViewById(R.id.addPosButton);

        addTouchListener();

        addButton = findViewById(R.id.addPosButton);
        addButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (firstClick == true){
                    Toast.makeText(getBaseContext(),
                            "Click the screen where you would like to have the player " +
                                    "positioned.",
                            Toast.LENGTH_LONG).show();
                    addButtonClicked = true;
                }
                addButtonClicked = true;
                firstClick = false;
            }

        });

        removeButton = findViewById(R.id.removePosButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View touchView0 = findViewById(R.id.ImageView00);
                final View touchView1 = findViewById(R.id.ImageView01);
                final View touchView2 = findViewById(R.id.ImageView02);
                final View touchView3 = findViewById(R.id.ImageView03);
                final View touchView4 = findViewById(R.id.ImageView04);
                final View touchView5 = findViewById(R.id.ImageView05);
                final View touchView6 = findViewById(R.id.ImageView06);
                final View touchView7 = findViewById(R.id.ImageView07);
                final View touchView8 = findViewById(R.id.ImageView08);
                final View touchView9 = findViewById(R.id.ImageView09);
                final View touchView10 = findViewById(R.id.ImageView10);

                removeButtonClicked = true;


                if(removeButtonClicked == true && arrayPosi > 0 && arrayPosj > 0){
                    arrayPosi--;
                    arrayPosj--;
                    switch(arrayPosi){
                        case 0:
                            touchView0.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView0",false);
                        case 1:
                            touchView1.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView1",false);
                        case 2:
                            touchView2.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView2",false);
                        case 3:
                            touchView3.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView3",false);
                        case 4:
                            touchView4.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView4",false);
                        case 5:
                            touchView5.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView5",false);
                        case 6:
                            touchView6.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView6",false);
                        case 7:
                            touchView7.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView7",false);
                        case 8:
                            touchView8.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView8",false);
                        case 9:
                            touchView9.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView9",false);
                        case 10:
                            touchView10.setVisibility(View.INVISIBLE);
                            visibilityMap.put("touchView10",false);

                    }
                    addButton.setClickable(true);
                    Toast.makeText(getBaseContext(),"Last position removed.",
                            Toast.LENGTH_LONG).show();
                    removeButtonClicked = false;
                }


            }
        });

        SaveButton = findViewById(R.id.saveButton);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(), "Formation Saved", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });
    }


    @SuppressLint("ClickableViewAccessibility")

    private void addTouchListener(){
        final View touchView0 = findViewById(R.id.ImageView00);
        final View touchView1 = findViewById(R.id.ImageView01);
        final View touchView2 = findViewById(R.id.ImageView02);
        final View touchView3 = findViewById(R.id.ImageView03);
        final View touchView4 = findViewById(R.id.ImageView04);
        final View touchView5 = findViewById(R.id.ImageView05);
        final View touchView6 = findViewById(R.id.ImageView06);
        final View touchView7 = findViewById(R.id.ImageView07);
        final View touchView8 = findViewById(R.id.ImageView08);
        final View touchView9 = findViewById(R.id.ImageView09);
        final View touchView10 = findViewById(R.id.ImageView10);

        final ImageView image = findViewById(R.id.screen);

        //final ImageButton playerIcon = (ImageButton)findViewById(R.id.playerIcon);
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //image.bringToFront();
                //75 is subtract to offset image from touch event. ImageButton setXY sets from the
                //top left corner.
                x = event.getX() - 75;
                y = event.getY() - 75;

                if (arrayPosi == 11){
                    addButton.setClickable(false);

                }

                if(addButtonClicked == true){
                    playerPosition[0][arrayPosi] = x;

                    playerPosition[1][arrayPosj] = y;



//                    touchView0.bringToFront();
//                    touchView0.setX(x);
//                    touchView0.setY(y);
//                    touchView0.setVisibility(View.VISIBLE);

                    switch(arrayPosi){
                        case 0:
                            touchView0.bringToFront();
                            touchView0.setX(x);
                            touchView0.setY(y);
                            touchView0.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView0",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView0",posArrList);
                            break;

                        case 1:
                            touchView1.bringToFront();
                            touchView1.setX(x);
                            touchView1.setY(y);
                            touchView1.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView1",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView1",posArrList);
                            break;


                        case 2:
                            touchView2.bringToFront();
                            touchView2.setX(x);
                            touchView2.setY(y);
                            touchView2.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView2",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView2",posArrList);
                            break;

                        case 3:
                            touchView3.bringToFront();
                            touchView3.setX(x);
                            touchView3.setY(y);
                            touchView3.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView3",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView3",posArrList);
                            break;

                        case 4:
                            touchView4.bringToFront();
                            touchView4.setX(x);
                            touchView4.setY(y);
                            touchView4.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView4",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView4",posArrList);
                            break;

                        case 5:
                            touchView5.bringToFront();
                            touchView5.setX(x);
                            touchView5.setY(y);
                            touchView5.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView5",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView5",posArrList);
                            break;

                        case 6:
                            touchView6.bringToFront();
                            touchView6.setX(x);
                            touchView6.setY(y);
                            touchView6.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView6",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView6",posArrList);
                            break;


                        case 7:
                            touchView7.bringToFront();
                            touchView7.setX(x);
                            touchView7.setY(y);
                            touchView7.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView7",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView7",posArrList);
                            break;


                        case 8:
                            touchView8.bringToFront();
                            touchView8.setX(x);
                            touchView8.setY(y);
                            touchView8.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView8",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView8",posArrList);
                            break;


                        case 9:
                            touchView9.bringToFront();
                            touchView9.setX(x);
                            touchView9.setY(y);
                            touchView9.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView9",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView9",posArrList);
                            break;


                        case 10:
                            touchView10.bringToFront();
                            touchView10.setX(x);
                            touchView10.setY(y);
                            touchView10.setVisibility(View.VISIBLE);
                            visibilityMap.put("touchView10",true);
                            posArrList = new ArrayList<>();
                            posArrList.add(x);
                            posArrList.add(y);
                            posMap.put("touchView10",posArrList);
                            break;

                    }

                    arrayPosi++;
                    arrayPosj++;

//                    String message = String.format("x= %.2f, y= %.2f", x, y);
//                   Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
                    addButtonClicked = false;
                }


                return false;
            }
        });
    }
}
