package com.example.mynotebook.ui.Dictionary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DictionaryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DictionaryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("这是词典界面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}