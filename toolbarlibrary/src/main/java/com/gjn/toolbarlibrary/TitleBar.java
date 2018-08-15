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
    private String leftText;
    private String rightText;
    private int leftImage;
    private int rightImage;

    private int titleTextColor;
    private int leftTextColor;
    private int rightTextColor;

    private int titleTextSize;
    private int leftTextSize;
    private int rightTextSize;

    private int leftPaddingleft;
    private int rightPaddingright;

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
            leftText = ta.getString(R.styleable.TitleBar_leftText);
            rightText = ta.getString(R.styleable.TitleBar_rightText);
            leftImage = ta.getResourceId(R.styleable.TitleBar_leftImage, -1);
            rightImage = ta.getResourceId(R.styleable.TitleBar_rightImage, -1);

            titleTextColor = ta.getColor(R.styleable.TitleBar_titleTextColor, 0);
            leftTextColor = ta.getColor(R.styleable.TitleBar_leftTextColor, 0);
            rightTextColor = ta.getColor(R.styleable.TitleBar_rightTextColor, 0);

            titleTextSize = (int) ta.getDimension(R.styleable.TitleBar_titleTextSize, -1);
            leftTextSize = (int) ta.getDimension(R.styleable.TitleBar_leftTextSize, -1);
            rightTextSize = (int) ta.getDimension(R.styleable.TitleBar_rightTextSize, -1);

            leftPaddingleft = (int) ta.getDimension(R.styleable.TitleBar_left_paddingLeft, 0);
            rightPaddingright = (int) ta.getDimension(R.styleable.TitleBar_right_paddingRight, 0);
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
            if (titleTextColor != 0) {
                center.setTextColor(titleTextColor);
            }
            if (titleTextSize != -1) {
                center.getPaint().setTextSize(titleTextSize);
            }
            center.setGravity(Gravity.CENTER);
            centerView = center;
        }
        if (!TextUtils.isEmpty(leftText)) {
            TextView left = new TextView(getContext());
            left.setText(leftText);
            if (leftTextColor != 0) {
                left.setTextColor(leftTextColor);
            }
            if (leftTextSize != -1) {
                left.getPaint().setTextSize(leftTextSize);
            }
            left.setGravity(Gravity.CENTER);
            leftView = left;
        }
        if (!TextUtils.isEmpty(rightText)) {
            TextView right = new TextView(getContext());
            right.setText(rightText);
            if (rightTextColor != 0) {
                right.setTextColor(rightTextColor);
            }
            if (rightTextSize != -1) {
                right.getPaint().setTextSize(rightTextSize);
            }
            right.setGravity(Gravity.CENTER);
            rightView = right;
        }
        if (leftImage != -1) {
            ImageView left = new ImageView(getContext());
            left.setImageResource(leftImage);
            leftView = left;
        }
        if (rightImage != -1) {
            ImageView right = new ImageView(getContext());
            right.setImageResource(rightImage);
            rightView = right;
        }

        if (leftPaddingleft > 0) {
            if (leftView != null) {
                leftView.setPadding(leftPaddingleft, 0, 0, 0);
            }
        }

        if (rightPaddingright > 0) {
            if (rightView != null) {
                rightView.setPadding(0, 0, rightPaddingright, 0);
            }
        }
    }

    public void setLeftText(String str) {
        if (leftImage == -1 && getLeftView() != null) {
            ((TextView) getLeftView()).setText(str);
            updataPadding();
        }
    }

    public void setRightText(String str) {
        if (rightImage == -1 && getRightView() != null) {
            ((TextView) getRightView()).setText(str);
            updataPadding();
        }
    }

    public void setTitleText(String str) {
        if (getCenterView() != null) {
            ((TextView) getCenterView()).setText(str);
            updataPadding();
        }
    }
}
