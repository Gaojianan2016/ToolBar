package com.gjn.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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

        TextView title = bar2.getCenterView();
        title.setTextSize(20);

        bar2.getLeftView().setBackgroundColor(Color.YELLOW);
        bar2.getCenterView().setBackgroundColor(Color.GREEN);
        bar2.getRightView().setBackgroundColor(Color.RED);

        bar2.getLeftView().setPadding(10,0,10,0);
        bar2.getRightView().setPadding(10,0,10,0);

        bar2.getRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar2.setPaddingTopBar(true);
            }
        });

        bar2.getLeftView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar2.setPaddingTopBar(false);
            }
        });

    }
}
