package com.univ_amu.food_scanner.ui;

        import android.Manifest;
        import android.app.AlertDialog;
        import android.app.Application;
        import android.content.Context;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;


        import androidx.annotation.Nullable;
        import androidx.core.content.ContextCompat;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.ViewModelProviders;
        import androidx.navigation.NavDirections;
        import androidx.navigation.Navigation;

        import com.google.zxing.BarcodeFormat;
        import com.google.zxing.Result;
        import com.univ_amu.food_scanner.R;
        import com.univ_amu.food_scanner.data.Food;
        import com.univ_amu.food_scanner.data.Repository;
        import com.univ_amu.food_scanner.data.database.Dao;
        import com.univ_amu.food_scanner.databinding.FragmentFoodBinding;
        import com.univ_amu.food_scanner.databinding.FragmentScannerBinding;
        import com.univ_amu.food_scanner.viewmodels.FoodViewModel;
        import com.univ_amu.food_scanner.viewmodels.FoodViewModelFactory;
        import com.univ_amu.food_scanner.viewmodels.ScannerViewModel;

        import java.util.Collections;
        import java.util.concurrent.CountedCompleter;


public class ScannerFragment extends Fragment {

    private  FragmentScannerBinding binding;
    private  ScannerViewModel model;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScannerBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        model=ViewModelProviders.of(this).get(ScannerViewModel.class);
        model.downloadedFood.observe(this,this::onDownloadedFood);
        model.error.observe(this,this::onError);
        binding.setModel(model);
        return binding.getRoot();
    }

    private void requestPermission() {
        requestPermissions(new String[] { android.Manifest.permission.CAMERA } ,0);
    }

    private boolean hasNoPermissions() {
        return ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
    }

    private void navigateUp() {

        Navigation.findNavController(binding.getRoot()).navigateUp();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (hasNoPermissions()) { requestPermission(); }
        if (hasNoPermissions()) { navigateUp(); }
    }
    @Override
    public void onResume() {
        super.onResume();
        binding.scanner.startCamera();
        binding.scanner.setFormats(Collections.singletonList(BarcodeFormat.EAN_13));
        binding.scanner.setResultHandler(result -> model.handleBarcode(result.getText()));
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.scanner.stopCamera();
    }
    private void onError(String message){
        new AlertDialog.Builder(getContext())
                .setTitle(message)
                .setPositiveButton(android.R.string.ok,(dialog,which)->navigateUp())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    private void onDownloadedFood(String code ) {

        NavDirections action = ScannerFragmentDirections.actionScannerFragmentToFoodFragment(code);
        Navigation.findNavController(binding.getRoot()).navigate(action);
    }




}