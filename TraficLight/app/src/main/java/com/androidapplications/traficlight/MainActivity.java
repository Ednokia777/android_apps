package com.androidapplications.traficlight;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout l1, l2, l3;
    private boolean start_stop = true;
    private Button button_1;
    private int couner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.lamp_one);
        l2 = findViewById(R.id.lamp_two);
        l3 = findViewById(R.id.lamp_three);
        button_1 = findViewById(R.id.button1);
    }

    public void onClickStart(View view) {
        if(!start_stop) {
            button_1.setText("Stop");
            start_stop = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (start_stop) {
                        couner++;
                        switch (couner){
                            case 1:
                                l1.setBackgroundColor(getResources().getColor(R.color.red_color));
                                l2.setBackgroundColor(getResources().getColor(R.color.grey));
                                l3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 2:
                                l1.setBackgroundColor(getResources().getColor(R.color.grey));
                                l2.setBackgroundColor(getResources().getColor(R.color.yellow_color));
                                l3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 3:
                                l1.setBackgroundColor(getResources().getColor(R.color.grey));
                                l2.setBackgroundColor(getResources().getColor(R.color.grey));
                                l3.setBackgroundColor(getResources().getColor(R.color.green_color));
                                couner = 0;
                                break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
            //l1.setBackgroundColor(Color.RED);
            //l2.setBackgroundColor(Color.YELLOW);
            //l3.setBackgroundColor(Color.GREEN);
        }else {
            start_stop = false;
            button_1.setText("Start");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start_stop = false;
    }
}
