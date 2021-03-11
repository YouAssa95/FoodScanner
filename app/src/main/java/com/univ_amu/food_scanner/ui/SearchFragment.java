package com.univ_amu.food_scanner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.univ_amu.food_scanner.databinding.FragmentScannerBinding;
import com.univ_amu.food_scanner.databinding.FragmentSearchBinding;
import com.univ_amu.food_scanner.viewmodels.ScannerViewModel;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();

    }
}