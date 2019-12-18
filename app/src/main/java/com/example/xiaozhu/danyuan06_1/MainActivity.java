package com.example.xiaozhu.danyuan06_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xts.greendaodemo.db.SqlBeanDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Button mMainBtn;
    private ArrayList<ItemBean.DataBean> lists;
    private MyMainAdapter adapter;
    private SqlBeanDao sqlBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRecyclerView();
        getRetofit();
        sqlBeanDao = BaseApp.getInstance().getDaoSession().getSqlBeanDao();
    }


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mMainBtn = (Button) findViewById(R.id.main_btn);
        mMainBtn.setOnClickListener(this);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        lists = new ArrayList<>();
        adapter = new MyMainAdapter(this, lists);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClick(new MyMainAdapter.OnClick() {
            @Override
            public void onLongClick(int a) {
                ItemBean.DataBean dataBean = lists.get(a);
                int num = dataBean.getNum();
                Long id = Long.valueOf(num);
                String title = dataBean.getTitle();
                String food_str = dataBean.getFood_str();
                String pic = dataBean.getPic();
                sqlBeanDao.insertOrReplace(new SqlBean(id,title,food_str,pic));
                Toast.makeText(MainActivity.this, "<<< 添加数据库成功 >>>", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRetofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyUrl.url).addConverterFactory(GsonConverterFactory.create()).build();
        MyUrl url = retrofit.create(MyUrl.class);
        Call<ItemBean> call = url.itemBean();
        call.enqueue(new Callback<ItemBean>() {
            @Override
            public void onResponse(Call<ItemBean> call, Response<ItemBean> response) {
                List<ItemBean.DataBean> data = response.body().getData();
                lists.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ItemBean> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.main_btn:
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
                break;
        }
    }
}
