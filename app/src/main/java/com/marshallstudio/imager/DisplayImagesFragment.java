package com.marshallstudio.imager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.marshallstudio.imager.databinding.FragmentDisplayImagesBinding;

public class DisplayImagesFragment extends Fragment {
    FragmentDisplayImagesBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDisplayImagesBinding.inflate(inflater, container, false);

        setRecyclerViewAdapterAndLayoutManager();
        setImageDataChangeObserver();
        setNetworkErrorListener();
        getImagesData(false);

        setOnClickListeners();

        return mBinding.getRoot();
    }

    private void setNetworkErrorListener() {
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getLiveDataIsServerReachable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isServerReachable) {
                if (!isServerReachable) {
                    mBinding.viewFlipper.setDisplayedChild(3);
                }
            }
        });

    }

    private void setOnClickListeners() {
        mBinding.btnSearch.setOnClickListener(view -> {
            SearchPreferences searchPreferences = SearchPreferences.getSearchPreferences();
            searchPreferences.setSearchQuery(mBinding.etSearch.getText().toString());
            getImagesData(true);
            mBinding.viewFlipper.setDisplayedChild(0);
            mBinding.rvImagesDisplay.scrollToPosition(0);
        });

        mBinding.btnOptions.setOnClickListener(view -> {
            mBinding.viewFlipper.setDisplayedChild(4);
        });
    }

    private void setImageDataChangeObserver() {
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getLiveData().observe(getViewLifecycleOwner(), hits -> {
            ImageRecyclerViewAdapter adapter = (ImageRecyclerViewAdapter) mBinding.rvImagesDisplay.getAdapter();
            adapter.updateData(hits);
            if (mBinding.viewFlipper.getDisplayedChild() == 4) {
                return;
            } else if (hits.size() == 0) {
                mBinding.viewFlipper.setDisplayedChild(2);
            } else {
                mBinding.viewFlipper.setDisplayedChild(1);
            }
        });
    }

    private void setRecyclerViewAdapterAndLayoutManager() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mBinding.rvImagesDisplay.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else {
            mBinding.rvImagesDisplay.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        }
        mBinding.rvImagesDisplay.setAdapter(new ImageRecyclerViewAdapter(getContext()));
    }


    public void getImagesData(boolean isNewSearch) {
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getImagesData(isNewSearch);
    }
}