package com.gjn.toolbarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by gjn on 2018/6/15.
 */

public class ToolBar extends LinearLayout {
    private static final String TAG = "ToolBar";

    private View LeftView;
    private View CenterView;
    private View RightView;

    public ToolBar(@NonNull Context context) {
        this(context, null);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        init(attrs, defStyleAttr);
        create();
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Toolbar);


        LeftView = new View(getContext());
        CenterView = new View(getContext());
        RightView = new View(getContext());
    }

    public void setLeftView(View view){
        LeftView = view;
    }

    public void setCenterView(View view){
        CenterView = view;
    }

    public void setRightView(View view){
        RightView = view;
    }

    public void create(){
        removeAllViews();

        LeftView.setLayoutParams(setViewLayoutParams());
        CenterView.setLayoutParams(setViewLayoutParams(1));
        RightView.setLayoutParams(setViewLayoutParams());

        addView(LeftView);
        addView(CenterView);
        addView(RightView);
    }

    private LayoutParams setViewLayoutParams(int w, int h, int weight) {
        return new LayoutParams(w, h, weight);
    }

    private LayoutParams setViewLayoutParams(int w, int h) {
        return new LayoutParams(w, h);
    }

    private LayoutParams setViewLayoutParams(int weight) {
        return setViewLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, weight);
    }

    private LayoutParams setViewLayoutParams() {
        return setViewLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    }
}
