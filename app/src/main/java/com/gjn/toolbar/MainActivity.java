package com.gjn.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

//        bar2.getLeftView().setBackgroundColor(Color.BLUE);
//        bar2.getCenterView().setBackgroundColor(Color.GREEN);
//        bar2.getRightView().setBackgroundColor(Color.RED);

//        bar2.setLeftText("我是左边");
//        bar2.setRightText("我是右边");
//        bar2.setTitleText("我是中间标题");

        bar2.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "左边", Toast.LENGTH_SHORT).show();
            }
        });

        bar2.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "右边", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
