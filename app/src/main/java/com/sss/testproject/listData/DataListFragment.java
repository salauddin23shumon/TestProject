package com.sss.testproject.listData;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
import com.sss.testproject.listData.viewModel.ListDataViewModel;
import com.sss.testproject.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sss.testproject.utility.Constant.LIST_DATA_URL;


public class DataListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private List<Resource> resources;
    private ListDataViewModel dataViewModel;
    private Context context;
    private static final String TAG = "ListFragment";


    public DataListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        resources = new ArrayList<>();
        adapter = new ListAdapter(resources, context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);

        dataViewModel = new ViewModelProvider(this).get(ListDataViewModel.class);
        dataViewModel.init();
        dataViewModel.getDataRepository().observe(getViewLifecycleOwner(), new Observer<ListResponse>() {
            @Override
            public void onChanged(ListResponse listResponse) {
                resources = listResponse.getResults().getResources();
                adapter.updateList(resources);
            }
        });

        return view;
    }


//    private void fetchData() {
//        Call<ListResponse> call = RetrofitClient.getInstance(LIST_DATA_URL).getApiInterface().getAllData();
//        call.enqueue(new Callback<ListResponse>() {
//            @Override
//            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
//                if (response.isSuccessful()){
//                    Log.d(TAG, "onResponse: "+response.body().getResults().getResources().size());
//                    adapter.updateList(response.body().getResults().getResources());
//                }else {
//                    Log.d(TAG, "onResponse: "+response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListResponse> call, Throwable t) {
//
//            }
//        });
//    }
}