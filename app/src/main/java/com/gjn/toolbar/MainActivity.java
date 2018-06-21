package com.gjn.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gjn.toolbarlibrary.TitleBar;
import com.gjn.toolbarlibrary.ToolBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToolBar bar = findViewById(R.id.bar);

        bar.getCenterView().setBackgroundColor(Color.LTGRAY);

        TitleBar bar2 = findViewById(R.id.bar2);

        TextView title = bar2.getCenterView();
        title.setTextSize(20);

        bar2.setPaddingTopBar(true);
        bar2.getLeftView().setBackgroundColor(Color.YELLOW);
        bar2.getCenterView().setBackgroundColor(Color.GREEN);
        bar2.getRightView().setBackgroundColor(Color.RED);
    }
}
