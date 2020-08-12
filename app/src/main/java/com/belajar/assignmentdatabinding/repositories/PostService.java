package com.belajar.assignmentdatabinding.repositories;

import com.belajar.assignmentdatabinding.models.posts.PostModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("posts")
    Call<ArrayList<PostModel>> getPosts();
}
