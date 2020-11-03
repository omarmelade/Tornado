package com.example.tornado.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tornado.Activity.SoundBoxActivity;
import com.example.tornado.R;

import java.util.List;

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> {

    SoundBoxActivity msoundBoxActivity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView soundTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            soundTextView = itemView.findViewById(R.id.sound_btn);
        }
    }

   List<Integer> soundList;
   List<String> nameList;

    public SoundAdapter( List<Integer> soundlist, List<String> namelist, SoundBoxActivity soundBoxActivity) {
        soundList = soundlist;
        nameList = namelist;
        msoundBoxActivity = soundBoxActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View soundView = inflater.inflate(R.layout.item_sound, parent, false);

        return new ViewHolder(soundView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            final TextView textView = holder.soundTextView;
            textView.setText(nameList.get(position));

            textView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    msoundBoxActivity.playSound(soundList.get(position));
                }
            });

    }

    @Override
    public int getItemCount() {
        return soundList.size();
    }




}
