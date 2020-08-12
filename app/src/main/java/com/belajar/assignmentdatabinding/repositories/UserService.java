package com.belajar.assignmentdatabinding.repositories;

import com.belajar.assignmentdatabinding.models.users.UserModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    Call<UserModel> getUsers();
}
