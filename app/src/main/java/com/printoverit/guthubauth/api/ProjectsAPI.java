package com.printoverit.guthubauth.api;

import com.printoverit.guthubauth.model.Projects;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProjectsAPI {

    @GET("repos")
    Call<List<Projects>> getRepo();

    @GET("starred")
    Call<List<Projects>> getStarredRepo();
}
