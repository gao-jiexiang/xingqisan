package com.example.xiaozhu.danyuan06_1;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiaozhu on 2019/10/30.
 */

@Entity
public class SqlBean {
    @Id
    private Long id;
    private String title;
    private String food_str;
    private String pic;
    @Generated(hash = 148357504)
    public SqlBean(Long id, String title, String food_str, String pic) {
        this.id = id;
        this.title = title;
        this.food_str = food_str;
        this.pic = pic;
    }
    @Generated(hash = 2066760633)
    public SqlBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFood_str() {
        return this.food_str;
    }
    public void setFood_str(String food_str) {
        this.food_str = food_str;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "SqlBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", food_str='" + food_str + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
