package com.marshallstudio.imager;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<List<Hits>> liveImagesData;
    private MutableLiveData<Boolean> isServerReachable;

    public ViewModel() {
        liveImagesData = new MutableLiveData<>();
        isServerReachable = new MutableLiveData<>();
        isServerReachable.setValue(true);
    }

    public MutableLiveData<Boolean> getLiveDataIsServerReachable() {
        return isServerReachable;
    }

    public void getImagesData(boolean isNewSearch) {
        if (!isNewSearch && liveImagesData.getValue() != null) {
            return;
        }
        SearchPreferences searchPreferences = SearchPreferences.getSearchPreferences();

        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);

        Call<ImageData> call = apiInterface.getImagesData(searchPreferences.getSearchParamsMap());
        call.enqueue(new Callback<ImageData>() {
            @Override
            public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                isServerReachable.setValue(true);
                liveImagesData.setValue(Arrays.asList(response.body().getHits()));
            }

            @Override
            public void onFailure(Call<ImageData> call, Throwable t) {
                Log.e("Failed:", t.getLocalizedMessage());
                isServerReachable.setValue(false);
            }
        });
    }

    public MutableLiveData<List<Hits>> getLiveData() {
        return liveImagesData;
    }
}
