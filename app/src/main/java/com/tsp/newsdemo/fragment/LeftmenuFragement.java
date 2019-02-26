package com.tsp.newsdemo.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.tsp.newsdemo.base.BaseFragment;

public class LeftmenuFragement extends BaseFragment {

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("我是左侧菜单显示");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
