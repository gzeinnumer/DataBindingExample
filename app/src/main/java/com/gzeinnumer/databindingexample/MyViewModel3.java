package com.gzeinnumer.databindingexample;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel3 extends ViewModel {

    public MutableLiveData<String> str = new MutableLiveData<>();

    public MyViewModel3() {
    }
}
