package com.tsp.newsdemo.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.tsp.newsdemo.base.BasePager;
import com.tsp.newsdemo.utils.LogUtil;

/**
 * 新闻中心
 */
public class NewsCenterPager extends BasePager {

    public NewsCenterPager(Context context) {
        super(context);
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.i("主页面被初始化了..");
        iv_menu.setVisibility(View.VISIBLE);
        //1.设置标题
        tv_title.setText("新闻中心");
        //2.联网请求获取到的数据记载到Fragment中
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的Fragment布局中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("新闻中心内容");
    }
}
