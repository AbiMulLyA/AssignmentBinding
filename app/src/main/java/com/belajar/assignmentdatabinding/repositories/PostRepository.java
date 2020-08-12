package com.belajar.assignmentdatabinding.repositories;

import com.belajar.assignmentdatabinding.utils.ClientUtil;

public class PostRepository {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    public PostService service;

    public PostRepository() {
        service = ClientUtil.client(PostService.class, BASE_URL);
    }
}
