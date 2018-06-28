package com.dayal.wpapidemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class PostAdapter extends ArrayAdapter<PostItem> implements View.OnClickListener {

    Context mContext;

    public PostAdapter(List<PostItem> data, @NonNull Context context) {
        super(context,0, data);

        this.mContext=context;

    }
    private static class ViewHolder {
        TextView date;
        TextView desc;
        ImageView image;
    }


    @NonNull
    @Override
    public View getView(int position, @NonNull View view, @NonNull ViewGroup viewGroup) {


        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.list_row,viewGroup,false);
            final ViewHolder viewHolder = new ViewHolder();

            viewHolder.image = (ImageView)view.findViewById(R.id.post_imageID);
            viewHolder.date = (TextView)view.findViewById(R.id.date_postID);
            viewHolder.desc = (TextView)view.findViewById(R.id.desc_postID);

            view.setTag(viewHolder);
        }
        final PostItem postItem = getItem(position);
        final ViewHolder viewHolder = (ViewHolder)view.getTag();
        try {
            viewHolder.date.setText(postItem.getDate());
            viewHolder.desc.setText(postItem.getDescription());
            Picasso.with(mContext).load(postItem.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(viewHolder.image);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
         return view;
    }

    @Override
    public void onClick(View view) {

    }
}
