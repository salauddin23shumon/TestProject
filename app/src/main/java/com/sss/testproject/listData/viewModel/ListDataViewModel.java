package com.sss.testproject.listData.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sss.testproject.listData.listDataModel.ListResponse;
import com.sss.testproject.listData.repository.DataRepository;

public class ListDataViewModel extends ViewModel {

    private MutableLiveData<ListResponse> mutableLiveData;
    private DataRepository dataRepository;

    public void init(){
        if (mutableLiveData!=null){
            return;
        }
        dataRepository=DataRepository.getInstance();
        mutableLiveData=dataRepository.getListData();
    }

    public LiveData<ListResponse>getDataRepository(){
        return mutableLiveData;
    }
}
