package com.gjn.toolbarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by gjn on 2018/6/15.
 */

public class ToolBar extends LinearLayout {
    private static final String TAG = "ToolBar";

    protected View LeftView;
    protected View CenterView;
    protected View RightView;

    protected int leftId;
    protected int centerId;
    protected int rightId;

    protected boolean isPaddingTopBar;

    protected OnClickListener leftOnClickListener;
    protected OnClickListener centerOnClickListener;
    protected OnClickListener rightOnClickListener;

    public ToolBar(@NonNull Context context) {
        this(context, null);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.myToolBar, defStyleAttr, 0);
            leftId = ta.getResourceId(R.styleable.myToolBar_leftViewId, -1);
            centerId = ta.getResourceId(R.styleable.myToolBar_centerViewId, -1);
            rightId = ta.getResourceId(R.styleable.myToolBar_rightViewId, -1);
            isPaddingTopBar = ta.getBoolean(R.styleable.myToolBar_isPaddingTopBar, false);
            ta.recycle();
        }
        init();
        create();
    }

    protected void init() {
        CenterView = createViewById(centerId);
        LeftView = createViewById(leftId);
        RightView = createViewById(rightId);
    }

    public void create() {
        setOrientation(HORIZONTAL);
        removeAllViews();

        if (LeftView != null) {
            LeftView.setLayoutParams(setViewLayoutParams());
            addView(LeftView);

            if (leftOnClickListener != null) {
                LeftView.setOnClickListener(leftOnClickListener);
            }
        }
        if (CenterView != null) {
            CenterView.setLayoutParams(setViewLayoutParams(1));
            addView(CenterView);

            if (centerOnClickListener != null) {
                CenterView.setOnClickListener(centerOnClickListener);
            }
        }
        if (RightView != null) {
            RightView.setLayoutParams(setViewLayoutParams());
            addView(RightView);

            if (rightOnClickListener != null) {
                RightView.setOnClickListener(rightOnClickListener);
            }
        }

        int left = getPaddingLeft();
        int top = 0;
        int right = getPaddingRight();
        int bottom = getPaddingBottom();

        if (isPaddingTopBar) {
            setPadding(left, getBarHeight(), right, bottom);
        } else {
            setPadding(left, top, right, bottom);
        }
    }

    private int getBarHeight() {
        int barId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getContext().getResources().getDimensionPixelSize(barId);
    }

    public void setLeftOnClickListener(OnClickListener leftOnClickListener) {
        this.leftOnClickListener = leftOnClickListener;
    }

    public void setCenterOnClickListener(OnClickListener centerOnClickListener) {
        this.centerOnClickListener = centerOnClickListener;
    }

    public void setRightOnClickListener(OnClickListener rightOnClickListener) {
        this.rightOnClickListener = rightOnClickListener;
    }

    public boolean isPaddingTopBar() {
        return isPaddingTopBar;
    }

    public void setPaddingTopBar(boolean paddingTopBar) {
        isPaddingTopBar = paddingTopBar;
        create();
    }

    public <T extends View> T getLeftView() {
        return (T) LeftView;
    }

    public void setLeftView(int id) {
        leftId = id;
        LeftView = createViewById(leftId);
        create();
    }

    public <T extends View> T getCenterView() {
        return (T) CenterView;
    }

    public void setCenterView(int id) {
        centerId = id;
        CenterView = createViewById(centerId);
        create();
    }

    public <T extends View> T getRightView() {
        return (T) RightView;
    }

    public void setRightView(int id) {
        rightId = id;
        RightView = createViewById(rightId);
        create();
    }

    public void setLeftView(View view) {
        LeftView = view;
        create();
    }

    public void setCenterView(View view) {
        CenterView = view;
        create();
    }

    public void setRightView(View view) {
        RightView = view;
        create();
    }

    private View createViewById(int id) {
        if (id > 0) {
            return LayoutInflater.from(getContext()).inflate(id, null);
        }
        return null;
    }

    private LayoutParams setViewLayoutParams() {
        return setViewLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    }

    private LayoutParams setViewLayoutParams(int weight) {
        return setViewLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, weight);
    }

    private LayoutParams setViewLayoutParams(int w, int h) {
        return new LayoutParams(w, h);
    }

    private LayoutParams setViewLayoutParams(int w, int h, int weight) {
        return new LayoutParams(w, h, weight);
    }
}
