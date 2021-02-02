package com.univ_amu.food_scanner.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.univ_amu.food_scanner.data.Repository;
import com.univ_amu.food_scanner.databinding.FragmentFoodListBinding;

public class FoodListFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView root = new TextView(this.getContext());
        root.setText("FoodListFragment");
        FragmentFoodListBinding binding = FragmentFoodListBinding.inflate(inflater, container, false);

        Repository repository = new Repository();
        FoodListAdapter adapter = new FoodListAdapter();
        adapter.submitList(repository.getFoods());
        binding.foodList.setAdapter(adapter);
        binding.foodList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        return binding.getRoot();
    }
}
