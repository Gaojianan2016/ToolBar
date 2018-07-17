package com.gjn.toolbarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by gjn on 2018/6/15.
 */

public class ToolBar extends LinearLayout {
    private static final String TAG = "ToolBar";

    protected View leftView;
    protected View centerView;
    protected View rightView;
    
    private int leftWidth;
    private int leftHeight;
    private int rightWidth;
    private int rightHeight;

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
            
            leftWidth = (int) ta.getDimension(R.styleable.myToolBar_left_width, LayoutParams.WRAP_CONTENT);
            leftHeight = (int) ta.getDimension(R.styleable.myToolBar_left_height, LayoutParams.MATCH_PARENT);
            rightWidth = (int) ta.getDimension(R.styleable.myToolBar_right_width, LayoutParams.WRAP_CONTENT);
            rightHeight = (int) ta.getDimension(R.styleable.myToolBar_right_height, LayoutParams.MATCH_PARENT);
            ta.recycle();
        }

        init();
        create();
    }

    protected void init() {
        centerView = createViewById(centerId);
        leftView = createViewById(leftId);
        rightView = createViewById(rightId);
    }

    public void create() {
        setOrientation(HORIZONTAL);
        removeAllViews();

        if (leftView != null) {
            leftView.setLayoutParams(setViewLayoutParams(leftWidth, leftHeight));
            addView(leftView);
        }
        if (centerView != null) {
            centerView.setLayoutParams(setViewLayoutParams(1));
            addView(centerView);
        }
        if (rightView != null) {
            rightView.setLayoutParams(setViewLayoutParams(rightWidth, rightHeight));
            addView(rightView);
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
        updataPadding();
    }

    public void updataPadding() {
        if (getChildCount() == 1) {
            if (rightView != null) {
                setGravity(Gravity.END | Gravity.TOP);
            }else {
                setGravity(Gravity.START | Gravity.TOP);
            }
        }else if (getChildCount() == 2) {
            if (centerView != null) {
                if (leftView != null) {
                    leftView.measure(0, 0);
                    int w = leftView.getMeasuredWidth();
                    centerView.setPadding(0, 0, w, 0);
                } else {
                    rightView.measure(0, 0);
                    int w = rightView.getMeasuredWidth();
                    centerView.setPadding(w, 0, 0, 0);
                }
            }
        } else if (getChildCount() == 3) {
            leftView.measure(0, 0);
            rightView.measure(0, 0);
            int lw = leftView.getMeasuredWidth();
            int rw = rightView.getMeasuredWidth();
            if (lw - rw > 0) {
                centerView.setPadding(0, 0, lw - rw, 0);
            } else {
                centerView.setPadding(rw - lw, 0, 0, 0);
            }
        }
    }

    private int getBarHeight() {
        int barId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getContext().getResources().getDimensionPixelSize(barId);
    }

    public void setLeftOnClickListener(OnClickListener leftOnClickListener) {
        if (leftView != null) {
            leftView.setOnClickListener(leftOnClickListener);
        }
    }

    public void setCenterOnClickListener(OnClickListener centerOnClickListener) {
        if (centerView != null) {
            centerView.setOnClickListener(centerOnClickListener);
        }
    }

    public void setRightOnClickListener(OnClickListener rightOnClickListener) {
        if (rightView != null) {
            rightView.setOnClickListener(rightOnClickListener);
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
        return (T) leftView;
    }

    public void setLeftView(int id) {
        leftId = id;
        leftView = createViewById(leftId);
        create();
    }

    public void setLeftView(View view) {
        leftView = view;
        create();
    }

    public <T extends View> T getCenterView() {
        return (T) centerView;
    }

    public void setCenterView(int id) {
        centerId = id;
        centerView = createViewById(centerId);
        create();
    }

    public void setCenterView(View view) {
        centerView = view;
        create();
    }

    public <T extends View> T getRightView() {
        return (T) rightView;
    }

    public void setRightView(int id) {
        rightId = id;
        rightView = createViewById(rightId);
        create();
    }

    public void setRightView(View view) {
        rightView = view;
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
