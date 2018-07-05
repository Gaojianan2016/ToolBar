# ToolBar
自定义ToolBar

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}


dependencies {
    implementation 'com.github.Gaojianan2016:ToolBar:1.0.8'
}
```

------------------------

# 基本使用
Activity

```
package com.gjn.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        bar2.getCenterView().setBackgroundColor(Color.GREEN);
        bar2.getRightView().setBackgroundColor(Color.RED);

    }
}

```

xml

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.gjn.toolbar.MainActivity">

    <com.gjn.toolbarlibrary.ToolBar
        android:id="@+id/bar"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:visibility="gone"
        app:centerViewId="@layout/header"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.gjn.toolbarlibrary.TitleBar
        android:id="@+id/bar2"
        android:layout_width="0dp"
        android:layout_height="75dp"
        android:paddingTop="10dp"
        app:title="@string/app_name"
        app:rightText="自定义"
        app:titleTextSize="10sp"
        app:right_paddingRight="20dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/bar" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:text="ToolBar"
        android:textSize="20sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.105" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginEnd="20dp"
        android:layout_marginTop="8dp"
        android:text="Button"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.083" />


</android.support.constraint.ConstraintLayout>


```

---------------------

 # 基本属性说明
- **ToolBar**

|属性|使用|说明|
|-|-|-|
|leftViewId(res)|app:leftViewId|设置toolbar左边view|
|centerViewId(res)|app:centerViewId|设置toolbar中间view|
|rightViewId(res)|app:rightViewId|设置toolbar右边view|
|isPaddingTopBar(boolean)|app:isPaddingTopBar|为toolbar顶部添加一状态栏高度的padding|
|left_width(dimension)|app:left_width|为leftview添加宽度|
|left_height(dimension)|app:left_height|为leftview添加高度|
|right_width(dimension)|app:right_width|为rightview添加宽度|
|right_height(dimension)|app:right_height|为rightview添加高度|

- **TitleBar**

|属性|使用|说明|
|-|-|-|
|title(string)|app:title|设置titlebar标题|
|leftImage(res)|app:leftImage|设置titlebar左边图片|
|rightImage(res)|app:rightImage|设置titlebar右边图片|
|leftText(string)|app:leftText|设置titlebar左边文字|
|rightText(string)|app:rightText|设置titlebar右边文字|
|titleTextColor(color)|app:titleTextColor|设置title文字颜色|
|leftTextColor(color)|app:leftTextColor|设置left文字颜色|
|rightTextColor(color)|app:rightTextColor|设置right文字颜色|
|titleTextSize(dimension)|app:titleTextSize|设置title文字大小 sp|
|leftTextSize(dimension)|app:leftTextSize|设置left文字大小 sp|
|rightTextSize(dimension)|app:rightTextSize|设置right文字大小 sp|
|left_paddingLeft(dimension)|app:left_paddingLeft|设置leftpadding dp|
|right_paddingRight(dimension)|app:right_paddingRight|设置rightpadding dp|

**注：文字会被图片覆盖**
**当设置isPaddingTopBar之后将无视设置PaddingTop**

