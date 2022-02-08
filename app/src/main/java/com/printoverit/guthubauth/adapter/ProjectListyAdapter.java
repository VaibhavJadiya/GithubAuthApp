package com.printoverit.guthubauth.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.printoverit.guthubauth.R;
import com.printoverit.guthubauth.model.Projects;

import java.util.ArrayList;

public class ProjectListyAdapter extends RecyclerView.Adapter<ProjectListyAdapter.MyViewHolder> {
    private ArrayList<Projects> nameArray;
    private Boolean isStarred;

    public ProjectListyAdapter(@NonNull ArrayList<Projects> nameArray, Boolean isStarred){
        this.nameArray = nameArray;
        this.isStarred=isStarred;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item,parent,false);
        return new ProjectListyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String videoName=nameArray.get(position).getName();
        holder.videoTitle.setText(videoName);
        if (isStarred){
            holder.starIcon.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.yellow));
        }

    }

    @Override
    public int getItemCount() {
        return nameArray.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitle;
        ImageView starIcon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTitle=itemView.findViewById(R.id.prokjectr_titiel);
            starIcon=itemView.findViewById(R.id.starIcon);
        }
    }
}
