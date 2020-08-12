package com.belajar.assignmentdatabinding.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.provider.ContactsContract;

import com.belajar.assignmentdatabinding.R;
import com.belajar.assignmentdatabinding.adapters.PostAdapter;
import com.belajar.assignmentdatabinding.databinding.ActivityThirdBinding;
import com.belajar.assignmentdatabinding.viewmodels.PostsViewModel;

public class ThirdActivity extends AppCompatActivity {
    ActivityThirdBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PostAdapter adapter = new PostAdapter();
        StaggeredGridLayoutManager grid = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_third);
        binding.rvPosts.setLayoutManager(grid);
        binding.rvPosts.setAdapter(adapter);

        PostsViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(PostsViewModel.class);
        viewModel.getPosts().observe(this, adapter::setPosts);
        viewModel.fetchPosts();
        viewModel.getIsLoading().observe(this, isLoading -> {
            binding.pbLoadingPosts.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.rvPosts.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        });

        viewModel.getError().observe(this, error -> {
            if (!TextUtils.isEmpty(error)) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
        binding.setViewModel(viewModel);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}