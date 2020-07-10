package com.example.gymapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymapp.Interface.ItemClickListener;
import com.example.gymapp.R;
import com.example.gymapp.ViewExcercise;
import com.example.gymapp.model.excercise;
import com.example.gymapp.testing;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView image;
    public TextView text;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        image=(ImageView)itemView.findViewById(R.id.ex_img);
        text=(TextView)itemView.findViewById(R.id.ex_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
       itemClickListener.onClick(view,getAdapterPosition());
    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<excercise>excerciseList;
    private Context context;

    public RecyclerViewAdapter(List<excercise> excerciseList, Context context) {
        this.excerciseList= excerciseList;
        this.context=context;
    }

   // @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item_exercise,parent,false);

        return new RecyclerViewHolder(itemView);
     //   return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.image.setImageResource(excerciseList.get(position).getImage_id());
        holder.text.setText(excerciseList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
               Toast.makeText(context,"click"+excerciseList.get(position).getName(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,ViewExcercise.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image_id",excerciseList.get(position).getImage_id());
                intent.putExtra("name",excerciseList.get(position).getName());
                context.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return excerciseList.size();
    }
}
