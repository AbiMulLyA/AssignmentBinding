package com.belajar.assignmentdatabinding.viewmodels;

import android.util.Log;
import android.widget.MultiAutoCompleteTextView;

import androidx.lifecycle.MutableLiveData;

import com.belajar.assignmentdatabinding.models.posts.PostModel;
import com.belajar.assignmentdatabinding.repositories.PostRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsViewModel extends BaseViewModel{
    private MutableLiveData<ArrayList<PostModel>> posts = new MutableLiveData<>();

    public void fetchPosts(){
        isLoading.setValue(true);
        PostRepository postRepository = new PostRepository();
        postRepository.service.getPosts().enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().size() != 0) {
                        posts.setValue(response.body());
                    } else {
                        error.setValue("Data users kosong!");
                    }
                } else {
                    error.setValue(response.message());
                }

                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                t.printStackTrace();
                Log.e("Error get posts", t.getMessage());

                error.setValue(t.getMessage());

                isLoading.setValue(false);
            }
        });
    }
    public MutableLiveData<ArrayList<PostModel>> getPosts() {
        return posts;
    }
}
