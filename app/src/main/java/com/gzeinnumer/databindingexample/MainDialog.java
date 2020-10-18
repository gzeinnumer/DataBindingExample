package com.gzeinnumer.databindingexample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.gzeinnumer.databindingexample.databinding.FragmentMainDialogBinding;

public class MainDialog extends DialogFragment {

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

    public static final String TAG = "MainDialog";

    FragmentMainDialogBinding binding;

    public MainDialog() {
    }

    public static MainDialog newInstance() {
        return new MainDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawableResource(R.drawable.mygzn_background_dialog);

            //agar tidak bisa diclick luar
            getDialog().setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main_dialog);
        binding.setViewModel(new MyViewModel3());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initOnClick();
    }

    private void initView() {
        binding.title.setText("Disini Pesannya");
    }

    private void initOnClick() {
        binding.btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}