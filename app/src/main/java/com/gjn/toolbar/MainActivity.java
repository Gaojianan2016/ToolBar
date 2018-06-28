package com.gjn.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gjn.toolbarlibrary.TitleBar;
import com.gjn.toolbarlibrary.ToolBar;

public class MainActivity extends AppCompatActivity {

    private TitleBar bar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToolBar bar = findViewById(R.id.bar);

        bar.getCenterView().setBackgroundColor(Color.LTGRAY);

        bar2 = findViewById(R.id.bar2);

        bar2.getCenterView().setBackgroundColor(Color.GREEN);
        bar2.getRightView().setBackgroundColor(Color.RED);

    }
}
