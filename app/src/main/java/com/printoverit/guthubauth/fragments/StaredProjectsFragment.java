package com.printoverit.guthubauth.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.printoverit.guthubauth.R;
import com.printoverit.guthubauth.activities.MainActivity;
import com.printoverit.guthubauth.adapter.ProjectListyAdapter;
import com.printoverit.guthubauth.api.ProjectsAPI;
import com.printoverit.guthubauth.model.Projects;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaredProjectsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaredProjectsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StaredProjectsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaredProjectsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaredProjectsFragment newInstance(String param1, String param2) {
        StaredProjectsFragment fragment = new StaredProjectsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ProgressDialog progressDialog;
    ProjectListyAdapter adapter;
    RecyclerView recyclerView;
    String STARRED_URL="https://api.github.com/users/";
    ArrayList<Projects> NameArray=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_stared_projects, container, false);
        progressDialog=new ProgressDialog(getContext());
        TextView starred_repo_text=view.findViewById(R.id.stared_text);
        progressDialog.setMessage("Loading Repo");
        progressDialog.show();

        MainActivity activity = (MainActivity) getActivity();
        String myDataFromActivity = activity.getMyData()+"/";
        Log.d("TAG",myDataFromActivity);
        recyclerView=view.findViewById(R.id.starred_recycylerview);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(STARRED_URL+myDataFromActivity)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProjectsAPI jsonPlaceHolderApi = retrofit.create(ProjectsAPI.class);
        Call<List<Projects>> call;
        call = jsonPlaceHolderApi.getStarredRepo();
        call.enqueue(new Callback<List<Projects>>() {
            @Override
            public void onResponse(Call<List<Projects>> call, Response<List<Projects>> response) {
                progressDialog.cancel();
                List<Projects> posts = response.body();
                Log.d("video", posts.get(0).getName().toString());

                for (Projects post : posts) {
                    Log.d("video", post.getName());
                    NameArray.add(new Projects(post.getName()));
                }
                if (NameArray.isEmpty()){
                    starred_repo_text.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }else {
                    starred_repo_text.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter=new ProjectListyAdapter(NameArray,true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Projects>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
    }
}