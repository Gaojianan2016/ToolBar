package com.gjn.toolbarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gjn on 2018/6/19.
 */

public class TitleBar extends ToolBar {
    private static final String TAG = "TitleBar";

    private String title;
    private int leftImage;
    private int rightImage;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, 0);
            title = ta.getString(R.styleable.TitleBar_title);
            leftImage = ta.getResourceId(R.styleable.TitleBar_leftImage, -1);
            rightImage = ta.getResourceId(R.styleable.TitleBar_rightImage, -1);
            ta.recycle();
        }
        init();
        create();
    }

    @Override
    protected void init() {
        super.init();
        if (!TextUtils.isEmpty(title)) {
            TextView center = new TextView(getContext());
            center.setText(title);
            center.setGravity(Gravity.CENTER);
            CenterView = center;
        }
        if (leftImage > 0) {
            ImageView left = new ImageView(getContext());
            left.setImageResource(leftImage);
            LeftView = left;
        }
        if (rightImage > 0) {
            ImageView right = new ImageView(getContext());
            right.setImageResource(rightImage);
            RightView = right;
        }
    }
}
