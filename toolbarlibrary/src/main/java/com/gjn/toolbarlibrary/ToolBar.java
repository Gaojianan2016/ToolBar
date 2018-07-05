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
    
    private int left_width;
    private int left_height;
    private int right_width;
    private int right_height;

    protected int leftId;
    protected int centerId;
    protected int rightId;

    protected boolean isPaddingTopBar;
    protected int defaultTop;

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
            
            left_width = (int) ta.getDimension(R.styleable.myToolBar_left_width, LayoutParams.WRAP_CONTENT);
            left_height = (int) ta.getDimension(R.styleable.myToolBar_left_height, LayoutParams.MATCH_PARENT);
            right_width = (int) ta.getDimension(R.styleable.myToolBar_right_width, LayoutParams.WRAP_CONTENT);
            right_height = (int) ta.getDimension(R.styleable.myToolBar_right_height, LayoutParams.MATCH_PARENT);
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
            LeftView.setLayoutParams(setViewLayoutParams(left_width, left_height));
            addView(LeftView);
        }
        if (CenterView != null) {
            CenterView.setLayoutParams(setViewLayoutParams(1));
            addView(CenterView);
        }
        if (RightView != null) {
            RightView.setLayoutParams(setViewLayoutParams(right_width, right_height));
            addView(RightView);
        }

        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getPaddingRight();
        int bottom = getPaddingBottom();

        if (defaultTop == 0 && top > 0) {
            defaultTop = top;
        }

        if (isPaddingTopBar) {
            setPadding(left, getBarHeight(), right, bottom);
        } else {
            setPadding(left, defaultTop, right, bottom);
        }

        if (getChildCount() == 2) {
            if (CenterView != null) {
                if (LeftView != null) {
                    LeftView.measure(0, 0);
                    int w = LeftView.getMeasuredWidth();
                    CenterView.setPadding(0, 0, w, 0);
                } else {
                    RightView.measure(0, 0);
                    int w = RightView.getMeasuredWidth();
                    CenterView.setPadding(w, 0, 0, 0);
                }
            }
        } else if (getChildCount() == 3) {
            LeftView.measure(0, 0);
            RightView.measure(0, 0);
            int lw = LeftView.getMeasuredWidth();
            int rw = RightView.getMeasuredWidth();
            if (lw - rw > 0) {
                CenterView.setPadding(0, 0, lw - rw, 0);
            } else {
                CenterView.setPadding(rw - lw, 0, 0, 0);
            }
        }
    }

    private int getBarHeight() {
        int barId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getContext().getResources().getDimensionPixelSize(barId);
    }

    public void setLeftOnClickListener(OnClickListener leftOnClickListener) {
        if (LeftView != null) {
            LeftView.setOnClickListener(leftOnClickListener);
        }
    }

    public void setCenterOnClickListener(OnClickListener centerOnClickListener) {
        if (CenterView != null) {
            CenterView.setOnClickListener(centerOnClickListener);
        }
    }

    public void setRightOnClickListener(OnClickListener rightOnClickListener) {
        if (RightView != null) {
            RightView.setOnClickListener(rightOnClickListener);
        }
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

    public void setLeftView(View view) {
        LeftView = view;
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

    public void setCenterView(View view) {
        CenterView = view;
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
