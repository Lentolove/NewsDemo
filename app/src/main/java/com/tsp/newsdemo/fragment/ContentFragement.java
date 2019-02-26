package com.tsp.newsdemo.fragment;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tsp.newsdemo.R;
import com.tsp.newsdemo.activity.MainActivity;
import com.tsp.newsdemo.base.BaseFragment;
import com.tsp.newsdemo.base.BasePager;
import com.tsp.newsdemo.pager.GovaffairPager;
import com.tsp.newsdemo.pager.HomePager;
import com.tsp.newsdemo.pager.NewsCenterPager;
import com.tsp.newsdemo.pager.SettingPager;
import com.tsp.newsdemo.pager.SmartServicePager;
import com.tsp.newsdemo.utils.LogUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class ContentFragement extends BaseFragment {

    /**
     * 用xUtils注解寻找id
     */
    @ViewInject(R.id.content_viewpager)
    private ViewPager content_viewpager;
    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    private ArrayList<BasePager> basePagers;

    @Override
    public View initView() {
        LogUtil.i("正文Fragment视图被初始化了");
        View view = View.inflate(context, R.layout.content_fragment,null);

        //使用xUtils3注解就不需要
//        content_viewpager = (ViewPager) view.findViewById(R.id.content_viewpager);
//        rg_main =(RadioGroup) view.findViewById(R.id.rg_main);

        //1.把视图注入到框架中,让contentFragment.this和View关联
        x.view().inject(ContentFragement.this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.i("正文Fragment数据被初始化了");

        /**
         * 1.初始化五个界面,并且放入集合中
         */
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));
        basePagers.add(new NewsCenterPager(context));
        basePagers.add(new SmartServicePager(context));
        basePagers.add(new GovaffairPager(context));
        basePagers.add(new SettingPager(context));

        //2.设置viewpager的内容适配器
        content_viewpager.setAdapter(new MyContentFragmentAdapter());
        //3.设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //4.监听某个页面被选中，初始化对应页面的数据
        content_viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        /**
         * 设置初始化页面为主页
         * 1.除了新闻中心可以滑动
         * 2.默认加载第一个页面
         * 3.同时加载数据
         */
        rg_main.check(R.id.rb_home);
        basePagers.get(0).initData();
        isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
    }

    /**
     * 设置某个页面可以滑动
     * @param touchmodeNone
     */
    private void isEnableSlidingMenu(int touchmodeNone) {
        //MainActivity页面继承自SlidingMenu
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmodeNone);
    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        /**
         * 当某个页面被选中的时候回调这个方法
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            basePagers.get(position).initData();
        }
        @Override
        public void onPageScrollStateChanged(int i) {

        }

    }
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    //false 表示页面切换没有动画效果
                    content_viewpager.setCurrentItem(0,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter:
                    content_viewpager.setCurrentItem(1,false);

                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice:
                    content_viewpager.setCurrentItem(2,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair:
                    content_viewpager.setCurrentItem(3,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting:
                    content_viewpager.setCurrentItem(4,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }
        }

    }

    class MyContentFragmentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return basePagers.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager basePager = basePagers.get(position);
            View rootView = basePager.rootView;
            container.addView(rootView);
            return rootView;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
