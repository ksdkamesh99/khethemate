package com.example.kheetemate.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeCostumerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeCostumerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Costumers");
    }

    public LiveData<String> getText() {
        return mText;
    }
}