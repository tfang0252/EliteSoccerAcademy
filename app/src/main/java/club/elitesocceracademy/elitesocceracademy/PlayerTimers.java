package club.elitesocceracademy.elitesocceracademy;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerTimers {

    private Chronometer mChronometer;
    private static Button startButton;
    private static Button stopButton;
    private Button resetButton;
    private int secondsPassed;
    private static MyCustomAdapter adapter;
    private static TextView chronometer;
    static int tempTime;
    private static int startingTime = 1500;
    public static int timeLeftInSeconds = startingTime;
    //When the timer is stopped the time remaining originalTime is used to reset the GUI label.
    public static int originalTime = timeLeftInSeconds;

    static String intialTimerLabel = timeLeftInSeconds / 60 + ":00";
    //Creates and instance of the timer
    public static Timer timer = new Timer();
    private static String updateTimerLabel;

    PlayerTimers(TextView x,int timeLeftInSeconds){
       chronometer=x;
       this.timeLeftInSeconds=timeLeftInSeconds;
    }


    public static void startTimer(){

        timer.scheduleAtFixedRate(new TimerTask() { // Below is the timerTask }, 0, 1000);
            // (i = minutes -1) so that the timer will not start with an extra minute
            int i = timeLeftInSeconds / 60; // minutes
            int j = timeLeftInSeconds % 60; // seconds


            public void run() {

                updateTimerLabel = timeLeftInSeconds + "";
                //GUI.updateLabel(updateTimerLabel);
                j--;
                timeLeftInSeconds--;

                if (j < 0) {
                    i--;
                    j = 59;

                }
                // Updates the GUI's label with the time every second for second values of 10 or greater.
                if (j >= 10) {
                    // Creates a string and saves it to the updateTimerLabel variable
                    updateTimerLabel = i + ":" + j;
                    // System.out.println(updateTimerLabel);
                    tempTime = timeLeftInSeconds;
                    chronometer.setText(updateTimerLabel);

                }
                // Updates the GUI's label with the time every second for second values less than 10.
                //This basically formats the GUI label to have a double zero for seconds ex. :00.
                else
                    // Creates a string and saves it to the updateTimerLabel variable
                    // updateTimerLabel = i + ":0" + j;
                tempTime = timeLeftInSeconds;
                chronometer.setText(updateTimerLabel);

                // System.out.println(i + ":0" + j);

                // Updates the GUI's label when the timer's minutes and seconds are zero.
                if (i == 0 && j == 0) {
                    // Stops the timer and purges it when the timer's minutes and seconds are both zero.
                    timer.cancel();
                    timer.purge();
                    updateTimerLabel = "00:00";
                    chronometer.setText(updateTimerLabel);



                    // System.out.println("End of the game.");
                }

            }// End of run method


        }, 0, 1000);

    }
    public static void stopTimer(){

        timer.cancel();
        timer.purge();
        timer = new Timer();
        tempTime = timeLeftInSeconds;
    }

    public static void restartTimer(){

        timer.cancel();
        timer.purge();
        timer = new Timer();
        timeLeftInSeconds = originalTime;
        updateTimerLabel = originalTime / 60 + ":00";
        chronometer.setText(updateTimerLabel);


// mChronometer.setBase(SystemClock.elapsedRealtime());
    }
}
