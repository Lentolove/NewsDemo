package com.tsp.newsdemo.base;


import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsp.newsdemo.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * BasePager作为基类是定义五个界面的公共方法和属性
 * HomePager,NewsCenterPager,
 * SmartServicePager,GovaffairPager
 * SettingPager都继承BasePager
 *
 * 1.初始化视图 intiView() 子类必须要实现
 * 2.初始化数据  联网请求数据等等 intiData()
 */
public class BasePager {
    /**
     * 上下文
     */
    public Context context;
    /**
     * 视图，代表各个不同的页面
     */
    public View rootView;

    @ViewInject(R.id.iv_menu)
    public ImageView iv_menu;
    /**
     * 显示标题
     */
    @ViewInject(R.id.tv_title)
    public TextView tv_title;

    /**
     * 加载各个子页面
     */
    @ViewInject(R.id.fl_content)
    public FrameLayout fl_content;

    public BasePager(Context context){
        this.context = context;
        rootView = initView();
    }

    private View initView(){
        /**
         * 基类的页面 有相同的布局结构 标题+中间的页面
         */
        View view = View.inflate(context, R.layout.base_pager,null);
        x.view().inject(this,view);
        return view;
    }
    /**
     * 初始化数据;当孩子需要初始化数据;或者绑定数据;联网请求数据并且绑定的时候，重写该方法
     */
    public void initData(){

    }
}
