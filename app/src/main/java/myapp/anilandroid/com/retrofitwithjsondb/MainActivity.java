package myapp.anilandroid.com.retrofitwithjsondb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myapp.anilandroid.com.retrofitwithjsondb.adapter.Customadapter;
import myapp.anilandroid.com.retrofitwithjsondb.database.Databasehelper;
import myapp.anilandroid.com.retrofitwithjsondb.model.Resultdata;
import myapp.anilandroid.com.retrofitwithjsondb.service.Apiclient;
import myapp.anilandroid.com.retrofitwithjsondb.service.Apiinterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Resultdata> resultdatalist;
    List<Resultdata> resultdata;
RecyclerView recyclerView;
Databasehelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView=(RecyclerView)findViewById( R.id.recyclerview ) ;
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        resultdata=new ArrayList<>(  );
        databasehelper=new Databasehelper( this );

        Apiinterface apiinterface= Apiclient.getApiService();
        Call<List<Resultdata>> call=apiinterface.getMyJSON();

        call.enqueue( new Callback<List<Resultdata>>() {
            @Override
            public void onResponse(Call<List<Resultdata>> call, Response<List<Resultdata>> response) {
                 resultdatalist=  response.body();
                int db_result_count=databasehelper.getCountData();
                if (db_result_count == 0) {
                    for (int i=0;i<resultdatalist.size();i++){
                        if (databasehelper.insertData( resultdatalist.get( i ).getName(),resultdatalist.get( i ).getUsername(),resultdatalist.get( i ).getEmail(),resultdatalist.get( i ).getAddress().getStreet(),resultdatalist.get( i ) .getAddress().getSuite(),resultdatalist.get( i ).getAddress().getCity(),resultdatalist.get( i ).getAddress().getZipcode(),resultdatalist.get( i ).getAddress().getGeo().getLat(),resultdatalist.get( i ).getAddress().getGeo().getLng(),resultdatalist.get( i ).getPhone(),resultdatalist.get( i ).getWebsite(),resultdatalist.get( i ).getCompany().getName(),resultdatalist.get( i ).getCompany().getCatchPhrase(),resultdatalist.get( i ).getCompany().getBs())){
                            Toast.makeText( getApplicationContext(),"Successfully inserted the data",Toast.LENGTH_LONG ).show();
                        }else {
                            Toast.makeText( getApplicationContext(),"data not found",Toast.LENGTH_SHORT ).show();
                        }
                    }
                }



            }

            @Override
            public void onFailure(Call<List<Resultdata>> call, Throwable t) {
            Log.e( "Error","Data Not Found" );
            }
        } );



        getdatafromdb();
    }
    private void getdatafromdb(){
    resultdata=databasehelper.getAllEmployeeData();
    recyclerView=(RecyclerView) findViewById( R.id.recyclerview );
    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager( getApplicationContext() );
    recyclerView.setLayoutManager( layoutManager );
    Customadapter customadapter=new Customadapter(MainActivity.this,resultdata);
    recyclerView.setAdapter( customadapter );

        //Log.d( "Data:---", String.valueOf( resultdatalist.size()) );

    }
}
