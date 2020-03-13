package com.example.numberprogressbar_awesomeprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  implements OnProgressBarListener {
    private Timer timer,timer2;

    private NumberProgressBar bnp,numberbar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.buttonPanel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberbar2.setProgress(0);

                timer2  = new Timer();
                timer2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                numberbar2.incrementProgressBy(1);

                            }
                        });
                    }
                },1500,300);


            }
        });


        bnp = (NumberProgressBar)findViewById(R.id.numberbar1);


        numberbar2 = (NumberProgressBar)findViewById(R.id.numberbar2);


        bnp.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(1);
                    }
                });
            }
        }, 1000, 100);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer2.cancel();
    }

    @Override
    public void onProgressChange(int current, int max) {
        if(current == max) {
            Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
            bnp.setProgress(0);
        }
    }
}
