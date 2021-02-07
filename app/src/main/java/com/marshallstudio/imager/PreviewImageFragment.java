package com.marshallstudio.imager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;
import com.marshallstudio.imager.databinding.FragmentPreviewImageBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PreviewImageFragment extends Fragment {
    private FragmentPreviewImageBinding mBinding;
    private Hits hit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentPreviewImageBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    private void setOnclickListeners() {
        mBinding.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyPermissionAndDownload();

            }
        });
    }

    private void verifyPermissionAndDownload() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 5 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                download();
            } else {
                Log.e("PERMISSION", "Denied!");
                Snackbar snackbar = Snackbar.make(mBinding.btnDownload, "Cannot Download Without Permission", Snackbar.LENGTH_SHORT);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(Color.RED);
                snackbar.setTextColor(Color.WHITE);
                snackbar.show();
            }
        }
    }

    private void download() {
        if (checkIfImageAlreadyDownloaded()) {
            return;
        }
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
        File dir = new File(dirPath);
        String fileName = "Imager_" + hit.getId() + ".jpg";

        Glide
                .with(requireContext())
                .load(hit.getLargeImageURL())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                        boolean isDirectoryCreated = dir.exists();
                        if (!dir.exists()) {
                            isDirectoryCreated = dir.mkdirs();
                        }
                        if (isDirectoryCreated) {
                            File imageFile = new File(dir, fileName);
                            try {
                                OutputStream fOut = new FileOutputStream(imageFile);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                                fOut.close();
                                Log.e("Save Path", imageFile.getAbsolutePath());
                                mBinding.btnDownload.setText(R.string.downloaded);
                                mBinding.btnDownload.setEnabled(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e("Directory Error", "Could not create directory");
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            PreviewImageFragmentArgs args = PreviewImageFragmentArgs.fromBundle(getArguments());
            hit = args.getImageDetails();

            Glide
                    .with(requireContext())
                    .load(hit.getLargeImageURL())
                    .thumbnail(Glide
                            .with(requireContext())
                            .load(hit.getWebformatURL()))
                    .transition(DrawableTransitionOptions.withCrossFade(200))
                    .into(mBinding.ivImage);
        }
        checkIfImageAlreadyDownloaded();
        setOnclickListeners();
    }

    private boolean checkIfImageAlreadyDownloaded() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/" + "Imager_" + hit.getId() + ".jpg";
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                mBinding.btnDownload.setText(R.string.Already_Downloaded);
                mBinding.btnDownload.setBackgroundColor(Color.YELLOW);
                mBinding.btnDownload.setEnabled(false);
                return true;
            }
        }
        return false;
    }
}