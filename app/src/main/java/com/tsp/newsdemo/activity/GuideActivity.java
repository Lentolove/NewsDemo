package com.tsp.newsdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.solver.Cache;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.tsp.newsdemo.R;
import com.tsp.newsdemo.SplashActivity;
import com.tsp.newsdemo.utils.CacheUtils;
import com.tsp.newsdemo.utils.DensityUtil;

import java.util.ArrayList;




public class GuideActivity extends Activity {

    private static final String TAG = GuideActivity.class.getSimpleName();
    private RelativeLayout rvGuidRoot;
    private LinearLayout ll_point_group;
    private ViewPager viewpager;
    private Button btnStartMain;
    private ImageView iv_red_point;
    private ArrayList<ImageView> imageViews;
    private int leftmax;    //两点间的间距
    private Context mContext;

    private int widthdpi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mContext = this;
        findViewId();
        getDate();

    }

    /**
     * 获取引导界面的三张图片
     */
    private void getDate() {
        int[] rcs = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
        imageViews = new ArrayList<>();

        //6.由于动态添加的点设置的大小是以sp为单位的  在不同的是被上显示会出现适配问题,用工具类实现dp转sp
        widthdpi = DensityUtil.dip2px(this,10);
        for (int i = 0; i < rcs.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(rcs[i]);
            //1.添加到集合中
            imageViews.add(imageView);
            //3.创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if (i != 0){
                //不包括第0个页面
                params.leftMargin  = widthdpi;
            }
            point.setLayoutParams(params);
            //添加到线性布局
            ll_point_group.addView(point);
        }
        //2.为viewpager设置内容适配器
        viewpager.setAdapter(new MyPagerAdapter());

        /**
         * 3.获取三个点之间的内边距  当layout创建的时候计算 实现全局监听layout
         *  根据View的生命周期，当视图执行到onLayout或者onDraw的时候，视图的高和宽，边距都有了
         */
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        //4.得到屏幕的的滑动的百分比
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        //5.设置--马上体验按钮的点击事件
        btnStartMain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //5.1 保存曾经今天入过主界面的记录
                CacheUtils.putBoolean(mContext, SplashActivity.START_SPLASH,true);
                //5.2跳转到主界面
                Intent intent = new Intent(mContext,MainActivity.class);
                startActivity(intent);
                //5.3关闭引导界面
                finish();
            }
        });
        

    }

    private void findViewId(){
        rvGuidRoot = (RelativeLayout) findViewById(R.id.rv_guid_root);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        btnStartMain = (Button) findViewById(R.id.btn_start_main);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
    }


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         *  当页面回调了会回调这个方法
         * @param position 当前滑动页面的位置
         * @param positionOffset    页面滑动的百分比
         * @param positionOffsetPixels  滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点间滑动距离对应的坐标 = 原来的起始位置 +  两点间移动的距离
            // leftmax 已经计算出来了
            int leftmargin = (int)(position*leftmax+positionOffset*leftmax);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);
        }


        /**
         * 当页面选中结束时候执行
         * 跳转到最后一个页面的时候显示--马上体验按钮
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size()-1){
                //最后一个界面
                btnStartMain.setVisibility(View.VISIBLE);
            }else {
                btnStartMain.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 监听layout创建时候的计算边距
     */
    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            //执行的不止一次  我们只需要一次获取到计算宽高即可
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(MyOnGlobalLayoutListener.this);
            // 间距  = 第1个点距离左边的距离 - 第0个点距离左边的距离
            leftmax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
            Log.i(TAG, "leftmax: "+leftmax);
        }
    }


    /**
     * 自定义适配器要实现四个基本方法
     */
    class MyPagerAdapter extends PagerAdapter{

        /**
         * 返回数据的总个数
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 获取到view
         * @param container ViewPager
         * @param position 要创业页面的位置
         * @return 返回和创建当前页面右关系的值
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);
            return imageView;
        }

        /**
         * 判断当前返回的view
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        /**
         * 销毁页面
         * @param container ViewPager
         * @param position 当前销毁页面的位置
         * @param object 要销毁的页面
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            container.removeView((View) object);
        }
    }
}
