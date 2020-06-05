package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    CountDownTimer countDownTimer;
    SeekBar seekbar;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar=findViewById(R.id.seekBar);

        // max time 60 sec
        seekbar.setMax(60);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            TextView tview=findViewById(R.id.textView);
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                       Log.d("seekbar progress", progress+"");

                       SetTimer((long)progress*1000+100);

                       tview.setText(0+":"+progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
    public void SetTimer(long millis)
    {

    countDownTimer = new CountDownTimer(millis,1000)
        {
           TextView tview=findViewById(R.id.textView);
           Button mbutton=findViewById(R.id.button);

            @Override
            public void onTick(long millisUntilFinished)
            {

                tview.setText(0+":"+millisUntilFinished/1000+"");
                mbutton.setText("Stop!");
                flag=true;
            }

            @Override
            public void onFinish() {
                ImageView iview=findViewById(R.id.imageView);
                iview.animate().translationY(150).rotation(36000);
                iview.setImageResource(R.drawable.hatched);
                seekbar.setVisibility(View.GONE);
                tview.setVisibility(View.GONE);
                mbutton.setVisibility(View.GONE);
                MediaPlayer mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.dog);
                mediaPlayer.start();


            }
        };
    }
    public void startOrStopTimer(View view)
    {
        Button mButton=(Button) view;
        TextView textView=findViewById(R.id.textView);
        if(!flag)
        {
            try{
                countDownTimer.start();
            }catch (NullPointerException np)
            {
                Toast.makeText(MainActivity.this,"Set value to timer",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            countDownTimer.cancel();
            flag=false;
            mButton.setText("Start");
            textView.setText("0:00");


        }



    }

}