package com.belajar.assignmentdatabinding.repositories;

import com.belajar.assignmentdatabinding.utils.ClientUtil;

public class UserRepository {
    private static final String BASE_URL = "https://reqres.in/api/";

    public UserService service;

    public UserRepository() {
        service = ClientUtil.client(UserService.class, BASE_URL);
    }
}
