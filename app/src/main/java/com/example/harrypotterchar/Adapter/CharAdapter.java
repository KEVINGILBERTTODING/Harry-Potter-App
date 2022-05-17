package com.example.harrypotterchar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.gesture.GestureLibraries;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.harrypotterchar.CharacterDetail;
import com.example.harrypotterchar.Model.CharModel;
import com.example.harrypotterchar.R;

import java.util.List;

public class CharAdapter extends RecyclerView.Adapter<CharAdapter.MyViewHolder> {

    Context context;
    List<CharModel> charModels;

    public CharAdapter (Context context, List<CharModel> charModels) {

        this.context    =   context;
        this.charModels =   charModels;

    }


    @NonNull
    @Override
    public CharAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.char_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharAdapter.MyViewHolder holder, int position) {

        holder.nameChar.setText(charModels.get(position).getName());
        holder.actorChar.setText(charModels.get(position).getActor());
        holder.dateChar.setText(charModels.get(position).getDate());

        // Load Image

        Glide.with(context)
                .load(charModels.get(position).getImage())
                .thumbnail(0.5f)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageChar);


    }

    @Override
    public int getItemCount() {
        return charModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameChar, dateChar, actorChar;
        ImageView imageChar;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inisialisasi textView

            nameChar        =   itemView.findViewById(R.id.tv_name);
            dateChar        =   itemView.findViewById(R.id.tv_DateofBirth);
            actorChar       =   itemView.findViewById(R.id.tv_Actor);

            // Inisialisasi imageView

            imageChar       =   itemView.findViewById(R.id.img_char);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            // Send data onclik by position using intent

            Intent intent   =   new Intent(context, CharacterDetail.class);
            intent.putExtra("name", charModels.get(getAdapterPosition()).getName());
            intent.putExtra("actor", charModels.get(getAdapterPosition()).getActor());
            intent.putExtra("date", charModels.get(getAdapterPosition()).getDate());
            intent.putExtra("image", charModels.get(getAdapterPosition()).getImage());
            context.startActivity(intent);



        }
    }
}
