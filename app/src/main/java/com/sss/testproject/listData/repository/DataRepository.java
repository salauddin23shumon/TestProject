package com.sss.testproject.listData.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sss.testproject.listData.listDataModel.ListResponse;
import com.sss.testproject.networking.ApiInterface;
import com.sss.testproject.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sss.testproject.utility.Constant.LIST_DATA_URL;

public class DataRepository {

    private static DataRepository dataRepository;
    private ApiInterface apiInterface;
    private static final String TAG = "DataRepository";

    public static DataRepository getInstance(){
        if (dataRepository==null){
            dataRepository=new DataRepository();
        }
        return dataRepository;
    }

    public DataRepository(){
        apiInterface= RetrofitClient.getInstance(LIST_DATA_URL).getApiInterface();
    }

    public MutableLiveData<ListResponse>getListData(){
        final MutableLiveData<ListResponse> mutableListData = new MutableLiveData<>();
        apiInterface.getAllData().enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccessful()){
                    mutableListData.setValue(response.body());
                    Log.d(TAG, "onResponse: "+response.body().getResults().getResources().size());
                }else {
                    mutableListData.setValue(null);
                    Log.d(TAG, "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });

        return mutableListData;
    }
}
