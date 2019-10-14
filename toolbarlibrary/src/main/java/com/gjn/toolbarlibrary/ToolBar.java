package com.gjn.toolbarlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by gjn on 2018/6/15.
 */

public class ToolBar extends LinearLayout {
    private static final String TAG = "ToolBar";

    protected View leftView;
    protected View centerView;
    protected View rightView;
    protected int leftId;
    protected int centerId;
    protected int rightId;
    //是否设置一个通知栏高度topbar
    protected boolean isPaddingTopBar;
    //是否设置默认左边视图为关闭Activity
    protected boolean leftIsFinish;
    //设置过的topbar会被保存下来
    protected int defaultTop;
    //是否展示LCR3个view的区域
    protected boolean showLayout;
    private int leftWidth;
    private int leftHeight;
    private int rightWidth;
    private int rightHeight;

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
            leftIsFinish = ta.getBoolean(R.styleable.myToolBar_left_is_finish, false);
            leftWidth = (int) ta.getDimension(R.styleable.myToolBar_left_width, LayoutParams.WRAP_CONTENT);
            leftHeight = (int) ta.getDimension(R.styleable.myToolBar_left_height, LayoutParams.MATCH_PARENT);
            rightWidth = (int) ta.getDimension(R.styleable.myToolBar_right_width, LayoutParams.WRAP_CONTENT);
            rightHeight = (int) ta.getDimension(R.styleable.myToolBar_right_height, LayoutParams.MATCH_PARENT);
            showLayout = ta.getBoolean(R.styleable.myToolBar_show_layout, false);
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
            if (showLayout) {
                leftView.setBackgroundColor(Color.BLUE);
            }
            addView(leftView);
        }
        if (centerView != null) {
            centerView.setLayoutParams(setViewLayoutParams(1));
            if (showLayout) {
                centerView.setBackgroundColor(Color.RED);
            }
            addView(centerView);
        }
        if (rightView != null) {
            rightView.setLayoutParams(setViewLayoutParams(rightWidth, rightHeight));
            if (showLayout) {
                rightView.setBackgroundColor(Color.GREEN);
            }
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

        if (leftIsFinish) {
            leftView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        }

        updataPadding();
    }

    public void updataPadding() {
        if (leftWidth < 0 && leftView != null) {
            leftView.measure(0, 0);
            if (leftView.getMeasuredWidth() > 0) {
                leftWidth = leftView.getMeasuredWidth();
            }
        }
        if (rightWidth < 0 && rightView != null) {
            rightView.measure(0, 0);
            if (rightView.getMeasuredWidth() > 0) {
                rightWidth = rightView.getMeasuredWidth();
            }
        }
        if (getChildCount() == 1) {
            if (rightView != null) {
                setGravity(Gravity.END | Gravity.TOP);
            } else {
                setGravity(Gravity.START | Gravity.TOP);
            }
        } else if (getChildCount() == 2) {
            if (centerView != null) {
                if (leftView != null) {
                    centerView.setPadding(0, 0, leftWidth, 0);
                } else {
                    centerView.setPadding(rightWidth, 0, 0, 0);
                }
            }
        } else if (getChildCount() == 3) {
            if (leftWidth - rightWidth > 0) {
                centerView.setPadding(0, 0, leftWidth - rightWidth, 0);
            } else {
                centerView.setPadding(rightWidth - leftWidth, 0, 0, 0);
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

    public void setLeftView(View view) {
        leftView = view;
        create();
    }

    public void setLeftView(int id) {
        leftId = id;
        leftView = createViewById(leftId);
        create();
    }

    public <T extends View> T getCenterView() {
        return (T) centerView;
    }

    public void setCenterView(View view) {
        centerView = view;
        create();
    }

    public void setCenterView(int id) {
        centerId = id;
        centerView = createViewById(centerId);
        create();
    }

    public <T extends View> T getRightView() {
        return (T) rightView;
    }

    public void setRightView(View view) {
        rightView = view;
        create();
    }

    public void setRightView(int id) {
        rightId = id;
        rightView = createViewById(rightId);
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
