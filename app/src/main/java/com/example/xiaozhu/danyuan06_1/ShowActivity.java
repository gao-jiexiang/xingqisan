package com.example.xiaozhu.danyuan06_1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xts.greendaodemo.db.SqlBeanDao;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private ListView mListItem;
    private SqlBeanDao sqlBeanDao;
    private ArrayList<SqlBean> lists;
    private ShowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        sqlBeanDao = BaseApp.getInstance().getDaoSession().getSqlBeanDao();
        getQuery();
    }

    private void getQuery() {
        List<SqlBean> sqlBeans = sqlBeanDao.loadAll();
        lists.addAll(sqlBeans);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        mListItem = (ListView) findViewById(R.id.list_item);
        lists = new ArrayList<>();
        adapter = new ShowAdapter(this, lists);
        mListItem.setAdapter(adapter);


        mListItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final SqlBean sqlBean = lists.get(position);
                final View viewUpdate = View.inflate(ShowActivity.this, R.layout.update_layout, null);
                new AlertDialog.Builder(ShowActivity.this)
                        .setTitle(sqlBean.getTitle())
                        .setView(viewUpdate)
                        .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText edit_update = viewUpdate.findViewById(R.id.edit_update);
                                String string = edit_update.getText().toString();
                                sqlBean.setFood_str(string);
                                adapter.notifyDataSetChanged();
                                SqlBean sqlBean1 = new SqlBean(sqlBean.getId(), sqlBean.getTitle(), string, sqlBean.getPic());
                                sqlBeanDao.update(sqlBean1);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
            }
        });

        mListItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SqlBean remove = lists.remove(position);
                sqlBeanDao.delete(remove);
                adapter.notifyDataSetChanged();
                Toast.makeText(ShowActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
