package com.example.pavneet_singh.roomdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pavneet_singh.roomdemo.R;
import com.example.pavneet_singh.roomdemo.model.VenuesItem;

import java.util.List;

/**
 * Created by Pavneet_Singh on 12/20/17.
 */

public class DataAdapterAdapter extends RecyclerView.Adapter<DataAdapterAdapter.BeanHolder> {

    private List<VenuesItem> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnNoteItemClick onNoteItemClick;

    public DataAdapterAdapter(List<VenuesItem> list, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        //this.onNoteItemClick = (OnNoteItemClick) context;
    }


    @Override
    public BeanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item, parent, false);
        return new BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(BeanHolder holder, int position) {
        Log.e("bind", "onBindViewHolder: " + list.get(position));

        holder.textViewTitle.setText(list.get(position).getName());

        if (list.get(position).getCategories().size() > 0) {
            holder.textViewContent.setText(list.get(position).getCategories().get(0).getName());
            Glide.with(context).load(list.get(position).getCategories().get(0).getIcon().getPrefix() + "44.png")
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ivImage);
        }else  {
            holder.textViewContent.setText("Note");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewContent;
        TextView textViewTitle;
        ImageView ivImage;

        public BeanHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewContent = itemView.findViewById(R.id.item_text);
            textViewTitle = itemView.findViewById(R.id.tv_title);
            ivImage = itemView.findViewById(R.id.ivImage);
        }

        @Override
        public void onClick(View view) {
            //onNoteItemClick.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteItemClick {
        void onNoteClick(int pos);
    }
}