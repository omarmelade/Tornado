package com.example.tornado.Adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
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

    private static final String TAG = "TAG";
    long DURATION = 200;
    private boolean on_attach = true;
    private boolean remove = false;



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

        final View nameView = inflater.inflate(R.layout.item_name, parent, false);
        final ViewHolder viewHolder = new ViewHolder(nameView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final NameTag nameTag = mNames.get(position);
        final TextView textView = holder.nameTextView;
        textView.setText(nameTag.getmName());

        if(!remove){
            setAnimation(holder.itemView, position);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove = true;
                removeItem(holder);
            }
        });
    }

    private void removeItem(ViewHolder holder) {
        int newPosition = holder.getAdapterPosition();
        mNames.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, mNames.size());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.d(TAG, "onScrollStateChanged: Called " + newState);
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimation(View itemView, int i) {
        if(!on_attach){
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }



}
