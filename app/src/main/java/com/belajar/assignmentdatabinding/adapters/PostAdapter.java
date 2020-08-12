package com.belajar.assignmentdatabinding.adapters;

import android.content.ClipData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.belajar.assignmentdatabinding.R;
import com.belajar.assignmentdatabinding.databinding.ItemPostBinding;
import com.belajar.assignmentdatabinding.models.posts.PostModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<PostModel> posts = new ArrayList<>();
    public void setPosts(ArrayList<PostModel> posts){
        Log.d("data", "response" + posts);
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_post,
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.bindData(posts.get(position));
    }

    @Override
    public int getItemCount() {
        if (posts != null){
            return posts.size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemPostBinding binding;

        public ViewHolder(@NonNull ItemPostBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(PostModel postModel){
            binding.setPost(postModel);
        }
    }
}
