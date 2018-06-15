package com.gjn.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.gjn.toolbarlibrary.ToolBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ToolBar bar = findViewById(R.id.bar);

        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText("左边");

        TextView textView2 = new TextView(this);
        textView2.setGravity(Gravity.CENTER);
        textView2.setText("右边");

        TextView textView3 = new TextView(this);
        textView3.setGravity(Gravity.CENTER);
        textView3.setText("中间");

        bar.setLeftView(textView);
        bar.setRightView(textView2);
        bar.setCenterView(textView3);
    }
}
