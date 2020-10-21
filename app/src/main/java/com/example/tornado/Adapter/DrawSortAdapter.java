package com.example.tornado.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tornado.Model.NameTag;
import com.example.tornado.R;

import java.util.List;

public class DrawSortAdapter extends
        RecyclerView.Adapter<DrawSortAdapter.ViewHolder> {

    private String[] dataSet;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;

        public ViewHolder(View itemView){
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
        }
    }

    private List<NameTag> mNames;

    public DrawSortAdapter(List<NameTag> names){
        mNames = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View nameView = inflater.inflate(R.layout.item_name, parent, false);

        ViewHolder viewHolder = new ViewHolder(nameView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NameTag nameTag = mNames.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(nameTag.getmName());
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }



}
