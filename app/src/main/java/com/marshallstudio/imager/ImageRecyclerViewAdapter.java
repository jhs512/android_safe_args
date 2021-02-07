package com.marshallstudio.imager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.marshallstudio.imager.databinding.GridImageBinding;

import java.util.ArrayList;
import java.util.List;


public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {
    private List<Hits> imageData = new ArrayList<>();
    private Context context;

    public ImageRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<Hits> data) {
        this.imageData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GridImageBinding gridImageBinding = GridImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(gridImageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.gridImageBinding.imageView.getLayoutParams();
        params.height = Integer.parseInt(imageData.get(holder.getAdapterPosition()).getWebformatHeight());
        holder.gridImageBinding.imageView.setLayoutParams(params);

        Glide
                .with(context)
                .load(imageData.get(holder.getAdapterPosition()).getWebformatURL())
                .thumbnail(Glide
                        .with(context)
                        .load(imageData.get(holder.getAdapterPosition()).getPreviewURL()))
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(holder.gridImageBinding.imageView);

        holder.gridImageBinding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(holder.gridImageBinding.getRoot());
                DisplayImagesFragmentDirections.ActionDisplayImagesFragmentToPreviewImageFragment action = DisplayImagesFragmentDirections.actionDisplayImagesFragmentToPreviewImageFragment(imageData.get(holder.getAdapterPosition()));
                navController.navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public GridImageBinding gridImageBinding;

        public ViewHolder(@NonNull GridImageBinding gridImageBinding) {
            super(gridImageBinding.getRoot());
            this.gridImageBinding = gridImageBinding;
        }
    }
}
