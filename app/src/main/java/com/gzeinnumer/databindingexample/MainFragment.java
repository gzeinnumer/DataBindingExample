package com.gzeinnumer.databindingexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.gzeinnumer.databindingexample.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    /*
    android {
        untuk android versi di bawah 4
        dataBinding {
            enabled = true
        }

        untuk android versi 4 -> gradel versi 6.1.1 -> android gradle plugin version 4.0.0
        buildFeatures{
            dataBinding = true
        }
    }
    */

    private FragmentMainBinding binding;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main);
        binding.setViewModel(new MyViewModel());
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}