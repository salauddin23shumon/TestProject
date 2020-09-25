package com.sss.testproject.listData;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sss.testproject.R;
import com.sss.testproject.listData.adapter.ListAdapter;
import com.sss.testproject.listData.listDataModel.ListResponse;
import com.sss.testproject.listData.listDataModel.Resource;
import com.sss.testproject.networking.RetrofitClient;
import com.sss.testproject.userLogin.loginModel.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sss.testproject.utility.Constant.LIST_DATA_URL;
import static com.sss.testproject.utility.Constant.LOGIN_URL;


public class DataListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private List<Resource> resources;
    private Context context;
    private static final String TAG="ListFragment";


    public DataListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        resources=new ArrayList<>();
        adapter=new ListAdapter(resources);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        fetchData();

        return view;
    }

    private void fetchData() {
        Call<ListResponse> call = RetrofitClient.getInstance(LIST_DATA_URL).getApiInterface().getAllData();
        call.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.body().getResults().getResources().size());

                }else {
                    Log.d(TAG, "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {

            }
        });
    }
}