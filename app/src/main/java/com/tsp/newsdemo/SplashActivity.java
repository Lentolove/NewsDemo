package com.tsp.newsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tsp.newsdemo.activity.GuideActivity;
import com.tsp.newsdemo.activity.MainActivity;
import com.tsp.newsdemo.utils.CacheUtils;

public class SplashActivity extends AppCompatActivity {

    public static final String START_SPLASH = "start_splash";
    private RelativeLayout rl_splash_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splash_root = (RelativeLayout) findViewById(R.id.rl_splash_root);
        //设置进入页面的旋转动画
        setSplashRotateAnimation();

    }

    /**
     * 设置旋转进入界面的旋转动画
     */
    private void setSplashRotateAnimation() {
        //1.旋转动画 旋转中心，页面的中心，旋转度数：
        RotateAnimation ra = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setFillAfter(true);
        //2.缩放动画 大小从0~1变大,缩放中心：界面中心
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        sa.setFillAfter(true);
        //3.渐变动画化 透明度从0-1
        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setFillAfter(true);
        //4.动画集合
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(ra);
        animationSet.addAnimation(aa);
        animationSet.addAnimation(sa);
        animationSet.setDuration(2000);
        rl_splash_root.startAnimation(animationSet);
        //动画完成的监听事件
        animationSet.setAnimationListener(new MyAnimationListener());

    }

    /**
     * 动画播放完成的监听事件
     * 1.动画开始
     * 2.动画结束
     * 3.动画重复
     */
    class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // 1.判断是否已经进入过引导界面 存储缓冲值来记录
            boolean isStartSplash = CacheUtils.getBoolean(SplashActivity.this, START_SPLASH);
            Intent intent;
            if (isStartSplash){
                //2.如果进入到指导界面，直接进图主界面
                intent = new Intent(SplashActivity.this,MainActivity.class);
            }else {
                //3.没有进入到指导界面
                intent = new Intent(SplashActivity.this,GuideActivity.class);
            }
            startActivity(intent);
            //关闭Splash界面
            finish();

            //Toast.makeText(SplashActivity.this, "动画播放完成了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
