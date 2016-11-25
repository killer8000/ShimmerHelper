package com.example.ndh.activity;

import android.graphics.Color;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ndh.shimmerhelper.R;
import com.example.ndh.shimmerhelper.ShimmerHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.tv);
        Button bt = (Button) findViewById(R.id.bt);
        EditText et = (EditText) findViewById(R.id.et);

        ShimmerHelper helper2 = new ShimmerHelper(et, et.getPaint());
        helper2.setDuration(3000);
        helper2.setMode(Shader.TileMode.REPEAT);
        helper2.setColors(Color.parseColor("#ff00ff"), Color.parseColor("#00ffff"));
        helper2.start();

        ShimmerHelper helper1 = new ShimmerHelper(bt, bt.getPaint());
        helper1.setDuration(2000);
        helper1.setColors(Color.parseColor("#ff00ff"), Color.parseColor("#00ffff"));
        helper1.start();

        ShimmerHelper helper = new ShimmerHelper(textView, textView.getPaint());
        helper.setDuration(1000);
        helper.start();
    }
}
