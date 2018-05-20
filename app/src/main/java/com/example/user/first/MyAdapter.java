package com.example.user.first;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 명윤 on 2018-05-20.
 */

//http://dudmy.net/android/2017/06/23/consider-of-recyclerview/ 1차 참조.

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DotShowItem> dotList;
    private static final int VIEW_TYPE1 = 0;
    private static final int VIEW_TYPE2 = 1;

    public MyAdapter(ArrayList<DotShowItem> arrayList){
        this.dotList = arrayList;
    }
    @Override
    //여기서 1개를 보여줄것인지, 2개를 보여줄것인지 리턴.
    public int getItemViewType(int position){
        return dotList.get(position).getType();
    }
    @Override
    public int getItemCount(){
        return dotList.size();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE1) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dot_show, parent, false);
            return new dotViewholder1(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dot_show2, parent, false);
            return new dotViewholder2(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof dotViewholder1) {
            ((dotViewholder1) holder).textView.setText(dotList.get(position).getTitle());
            ((dotViewholder1)holder).imageView.setImageDrawable(dotList.get(position).getIcon());
        } else {
            ((dotViewholder2) holder).imageView.setImageDrawable(dotList.get(position).getIcon());
            ((dotViewholder2) holder).imageView2.setImageDrawable(dotList.get(position).getIcon2());
            ((dotViewholder2) holder).textView.setText(dotList.get(position).getTitle());
        }
    }

    static class dotViewholder1 extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        // generate constructor here
        dotViewholder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.Dot_txt);
            imageView = (ImageView) itemView.findViewById(R.id.Dot_img);
        }
    }

    static class dotViewholder2 extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageView imageView2;
        private TextView textView;
        // generate constructor here
        dotViewholder2(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.dot_txt2);
            imageView = (ImageView) itemView.findViewById(R.id.Dot_img2);
            imageView2 = (ImageView) itemView.findViewById(R.id.Dot_img3);
        }
    }
}
