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
    implementation 'com.github.Gaojianan2016:ToolBar:1.0.3'
}
```

------------------------

# 基本使用
Activity

```
package com.gjn.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gjn.toolbarlibrary.TitleBar;
import com.gjn.toolbarlibrary.ToolBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToolBar bar = findViewById(R.id.bar);

        TitleBar bar2 = findViewById(R.id.bar2);

        TextView title = bar2.getCenterView();
        title.setTextSize(20);
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
        app:centerViewId="@layout/header"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.gjn.toolbarlibrary.TitleBar
        android:id="@+id/bar2"
        android:layout_width="0dp"
        android:layout_height="50dp"
        android:layout_marginTop="24dp"
        app:title="@string/app_name"
        app:leftText="返回"
        app:rightText="自定义"
        app:rightImage="@mipmap/ic_launcher"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/bar" />


</android.support.constraint.ConstraintLayout>

```

---------------------

 # 基本属性说明
- **ToolBar**

|属性|使用|说明|
|-|-|-|
|leftViewId|app:leftViewId|设置toolbar左边view|
|centerViewId|app:centerViewId|设置toolbar中间view|
|rightViewId|app:rightViewId|设置toolbar右边view|


- **TitleBar**

|属性|使用|说明|
|-|-|-|
|title|app:title|设置titlebar标题|
|leftImage|app:leftImage|设置titlebar左边图片|
|rightImage|app:rightImage|设置titlebar右边图片|
|leftText|app:leftText|设置titlebar左边文字|
|rightText|app:rightText|设置titlebar右边文字|

**注：文字会被图片覆盖**

