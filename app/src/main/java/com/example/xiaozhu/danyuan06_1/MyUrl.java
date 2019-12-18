package com.example.xiaozhu.danyuan06_1;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by xiaozhu on 2019/10/30.
 */

public interface MyUrl {
    String url = "http://www.qubaobei.com/";
    @GET("ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
    Call<ItemBean> itemBean();

}
