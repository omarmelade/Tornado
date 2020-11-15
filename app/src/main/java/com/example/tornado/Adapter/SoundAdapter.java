package com.example.tornado.Adapter;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tornado.R;
import com.example.tornado.Util.SampleActivity;

import java.util.List;

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> {

    SampleActivity msoundBoxActivity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView soundTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            soundTextView = itemView.findViewById(R.id.sound_btn);
        }
    }

   List<Integer> soundList;
   List<String> nameList;

    public SoundAdapter( List<Integer> soundlist, List<String> namelist, SampleActivity sampleActivity) {
        soundList = soundlist;
        nameList = namelist;
        msoundBoxActivity = sampleActivity;
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
                    textView.animate().setDuration(200).translationZ(0);
                    // cree un handler basé sur la boucle de temps
                    Handler h = new Handler(Looper.getMainLooper());
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView.animate().setDuration(100).translationZ(20);
                        }
                    }, 500);
                    msoundBoxActivity.playSound(soundList.get(position));
                }
            });



    }

    @Override
    public int getItemCount() {
        return soundList.size();
    }




}
