package com.tsp.newsdemo.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.tsp.newsdemo.base.BasePager;
import com.tsp.newsdemo.utils.LogUtil;

/**
 * 主页面
 */
public class HomePager extends BasePager {

    public HomePager(Context context) {
        super(context);
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.i("主页面被初始化了..");
        //1.设置标题
        tv_title.setText("主页面");
        //2.联网请求获取到的数据记载到Fragment中
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的Fragment布局中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("主页面内容");
    }
}
