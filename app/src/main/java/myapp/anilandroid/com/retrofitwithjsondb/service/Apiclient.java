package myapp.anilandroid.com.retrofitwithjsondb.service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient {

    private static final String ROOT_URL="https://jsonplaceholder.typicode.com/";


    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL  )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
    }


    public static Apiinterface getApiService() {
        return getRetrofitInstance().create( Apiinterface.class );
    }
}
