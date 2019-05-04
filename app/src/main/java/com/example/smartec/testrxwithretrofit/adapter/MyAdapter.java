package com.example.smartec.testrxwithretrofit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartec.testrxwithretrofit.R;
import com.example.smartec.testrxwithretrofit.model.Result;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    List<Result> myResult;

    public MyAdapter(Context context, List<Result> myResult) {
        this.context = context;
        this.myResult = myResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.headerText.setText(myResult.get(position).getTitle());
        holder.bodyText.setText(myResult.get(position).getOverview());

    }

    @Override
    public int getItemCount() {
        return myResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView headerText, bodyText;


        public ViewHolder(View itemView) {
            super(itemView);
            headerText = itemView.findViewById(R.id.header_text);
            bodyText = itemView.findViewById(R.id.body_text);
        }
    }
}
