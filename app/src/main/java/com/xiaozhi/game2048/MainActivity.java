package com.xiaozhi.game2048;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvScore;
    private int score;
   static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView)findViewById(R.id.tvscore);
        mainActivity = this;
    }

    public void showScore(){
        tvScore.setText(score+"");
   }
    public void addScore(int s){
        score+=s;
        showScore();
    }
    public void clearScore(){
        score = 0;
        showScore();
    }
    public static MainActivity getMainActivity(){
        return mainActivity;
    }
    public void rank(View view){
        Toast.makeText(getApplicationContext(),"敬请期待",Toast.LENGTH_SHORT).show();
    }
}
