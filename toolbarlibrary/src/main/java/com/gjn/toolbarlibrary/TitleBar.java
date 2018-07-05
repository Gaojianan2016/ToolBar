package com.gjn.toolbarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
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

    private int left_paddingLeft;
    private int right_paddingRight;

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

            titleTextColor = ta.getColor(R.styleable.TitleBar_titleTextColor, -1);
            leftTextColor = ta.getColor(R.styleable.TitleBar_leftTextColor, -1);
            rightTextColor = ta.getColor(R.styleable.TitleBar_rightTextColor, -1);

            titleTextSize = (int) ta.getDimension(R.styleable.TitleBar_titleTextSize, -1);
            leftTextSize = (int) ta.getDimension(R.styleable.TitleBar_leftTextSize, -1);
            rightTextSize = (int) ta.getDimension(R.styleable.TitleBar_rightTextSize, -1);

            left_paddingLeft = (int) ta.getDimension(R.styleable.TitleBar_left_paddingLeft, 0);
            right_paddingRight = (int) ta.getDimension(R.styleable.TitleBar_right_paddingRight, 0);
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
            if (titleTextColor != -1) {
                center.setTextColor(titleTextColor);
            }
            if (titleTextSize != -1) {
                center.setTextSize(titleTextSize);
            }
            center.setGravity(Gravity.CENTER);
            CenterView = center;
        }
        if (!TextUtils.isEmpty(leftText)) {
            TextView left = new TextView(getContext());
            left.setText(leftText);
            if (leftTextColor != -1) {
                left.setTextColor(leftTextColor);
            }
            if (leftTextSize != -1) {
                left.setTextSize(leftTextSize);
            }
            left.setGravity(Gravity.CENTER);
            LeftView = left;
        }
        if (!TextUtils.isEmpty(rightText)) {
            TextView right = new TextView(getContext());
            right.setText(rightText);
            if (rightTextColor != -1) {
                right.setTextColor(rightTextColor);
            }
            if (rightTextSize != -1) {
                right.setTextSize(rightTextSize);
            }
            right.setGravity(Gravity.CENTER);
            RightView = right;
        }
        if (leftImage != -1) {
            ImageView left = new ImageView(getContext());
            left.setImageResource(leftImage);
            LeftView = left;
        }
        if (rightImage != -1) {
            ImageView right = new ImageView(getContext());
            right.setImageResource(rightImage);
            RightView = right;
        }

        if (left_paddingLeft > 0) {
            if (LeftView != null) {
                LeftView.setPadding((int) left_paddingLeft, 0, 0, 0);
            }
        }

        if (right_paddingRight > 0) {
            if (RightView != null) {
                RightView.setPadding(0, 0, (int) right_paddingRight, 0);
            }
        }
    }

    public void setLeftText(String str){
        Log.e("-s-", "leftImage = " + leftImage);
        Log.e("-s-", "leftText = " + leftText);
        Log.e("-s-", "getLeftView = " + getLeftView());
        if (leftImage == -1 && getLeftView() != null) {
            ((TextView) getLeftView()).setText(str);
        }
    }

    public void setRightText(String str){
        if (rightImage == -1 && getRightView() != null) {
            ((TextView) getRightView()).setText(str);
        }
    }

    public void setTitleText(String str){
        if (getCenterView() != null) {
            ((TextView) getCenterView()).setText(str);
        }
    }
}
