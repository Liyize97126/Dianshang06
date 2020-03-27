package com.bawei.dianshang06.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshang06.R;
import com.bawei.dianshang06.adapter.MyListAdapter;
import com.bawei.dianshang06.base.BaseActivity;
import com.bawei.dianshang06.bean.DataBean;
import com.bawei.dianshang06.presenter.CirclePresenter;
import com.bawei.dianshang06.utils.DataCall;

import java.util.Random;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {
    //定义
    private CirclePresenter circlePresenter;
    private TextView page;
    private RecyclerView recyv;
    private MyListAdapter myListAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        //初始化
        recyv = findViewById(R.id.recyv);
        page = findViewById(R.id.page);
        //RecyclerView管理器
        //1.纵向滚动布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        //2.横向滚动布局管理器
        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);*/
        //3.网格布局管理器
        /*GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);*/
        //4.瀑布流布局管理器
        /*StaggeredGridLayoutManager staggeredGridLayoutManager;
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);*/
        //RecyclerView使用时必须设置布局管理器，不设置无法显示任何东西
        recyv.setLayoutManager(linearLayoutManager);
        //设置适配器
        myListAdapter = new MyListAdapter();
        recyv.setAdapter(myListAdapter);
        //初始化Presenter
        circlePresenter = new CirclePresenter(new DataCall() {
            @Override
            public void success(DataBean data) {
                //判断
                if(data.getStatus().equals("0000")){
                    Toast.makeText(MainActivity.this,data.getMessage(),Toast.LENGTH_LONG).show();
                    myListAdapter.getList().addAll(data.getResult());
                    myListAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void fail(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
        //发送get请求
        int i = new Random().nextInt(100);
        page.setText("当前第 " + (i + 1) + " 页");
        circlePresenter.request(String.valueOf(i));
        //circlePresenter.request(String.valueOf(i),String.valueOf(25));
    }
}
