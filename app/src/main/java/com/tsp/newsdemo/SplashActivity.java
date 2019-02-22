package com.tsp.newsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

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
            Toast.makeText(SplashActivity.this, "动画播放完成了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
