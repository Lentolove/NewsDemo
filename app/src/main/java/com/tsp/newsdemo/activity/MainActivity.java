package com.tsp.newsdemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tsp.newsdemo.R;
import com.tsp.newsdemo.fragment.ContentFragement;
import com.tsp.newsdemo.fragment.LeftmenuFragement;
import com.tsp.newsdemo.utils.DensityUtil;

/**
 * 主页面继承SlidingMenu实现左侧右侧滑动效果
 */
public class MainActivity extends SlidingFragmentActivity {

    public static final String LEFTMENU_TAG = "leftmenu_tag";
    public static final String MAIN_CONTENT_TAG = "main_content_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置没有标题
        super.onCreate(savedInstanceState);
        //初始化SlidingMenu
        initSlidingMenu();
        //初始化Fragment
        initFragment();
    }

    /**
     * 初始化SlidingMenu
     */
    private void initSlidingMenu() {
        //获取SlidingMenu
        SlidingMenu slidingMenu = getSlidingMenu();
        //1.设置主显示页面
        setContentView(R.layout.activity_main);
        //2.设置左侧菜单 --也可以设置右侧菜单  采用左侧+主屏幕为主
        //slidingMenu.setSecondaryMenu(R.layout.activity_rightmenu);

        setBehindContentView(R.layout.activity_leftmenu);

        //3.设置显示的模式：左侧+主页；左侧+主页+右侧；主页+右侧
        slidingMenu.setMode(SlidingMenu.LEFT);
        //4.设置滑动的模式：边缘滑动；全屏滑动；不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //5.设置主页的屏幕占比
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this, 200));
    }

    private void initFragment() {
        //1.得到FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事物
        FragmentTransaction ft = fm.beginTransaction();
        //3.替换fragment
        ft.replace(R.id.fl_main_content,new ContentFragement(), MAIN_CONTENT_TAG);
        ft.replace(R.id.fl_leftmenu,new LeftmenuFragement(), LEFTMENU_TAG);
        ft.commit();
    }
}
