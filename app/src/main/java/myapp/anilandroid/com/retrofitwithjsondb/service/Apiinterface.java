package myapp.anilandroid.com.retrofitwithjsondb.service;

import java.util.List;

import myapp.anilandroid.com.retrofitwithjsondb.model.Resultdata;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Apiinterface {
    @GET("users")
    Call<List<Resultdata>> getMyJSON();
}
