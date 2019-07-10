package com.example.windows.retrofitrecyclerview;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        initViews();
        getUserData();
        Toast.makeText(MainActivity.this,"Created", Toast.LENGTH_SHORT).show();

    }

    private void getUserData() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<UserData>> call = service.getAllUsers();
        call.enqueue(new Callback<List<UserData>>() {

            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                Log.d("Resp:", String.valueOf(response.body().size()));
                progressDialog.hide();
                generateDataList(response.body());
            }

            @Override
            public void onFailure( Call<List<UserData>> call, Throwable t) {
                Log.d("Resp:", t.getMessage());
                progressDialog.hide();
                Toast.makeText(MainActivity.this,"Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/

    private void generateDataList(List<UserData> dataList) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        CustomAdapter adapter = new CustomAdapter(MainActivity.this,dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}