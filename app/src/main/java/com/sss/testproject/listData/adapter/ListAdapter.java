package com.sss.testproject.listData.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sss.testproject.R;
import com.sss.testproject.listData.listDataModel.Resource;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.DataViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private List<Resource> resources;
    private Context context;


    public ListAdapter(List<Resource> resources, Context context) {
        this.resources = resources;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.single_row_item, parent, false);
        return new DataViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        holder.nameTv.setText(resources.get(position).getOwner().getFullname());
        holder.teamTv.setText(resources.get(position).getOwner().getPrimaryTeam());
        holder.eventTv.setText(resources.get(position).getEvent().getTitle());
        holder.captionTv.setText(resources.get(position).getCaption());
        Picasso.get().load(resources.get(position).getOwner().getAvatar()).into(holder.avatarIV);


        if (resources.get(position).getPhoto()!=null && !(resources.get(position).getPhoto().equals(""))) {
            Picasso.get().load(resources.get(position).getPhoto()).into(holder.eventIV);
        }else {
            Picasso.get().load(R.drawable.no_img_found).into(holder.eventIV);
        }

        if (!(resources.get(position).getVideo().equals(""))){
            holder.videoView.setVideoPath(resources.get(position).getVideo());
        }else {
            Log.d(TAG, "onBindViewHolder: no video found");
            holder.videoView.setVisibility(View.GONE);
        }
    }

    public void updateList(List<Resource> listData) {
        this.resources =listData;
        notifyDataSetChanged();
        Log.d(TAG, "updateList: "+listData.size());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ resources.size());
        return resources.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv, teamTv, eventTv, captionTv;
        ImageView avatarIV, eventIV;
        VideoView videoView;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nameTv);
            teamTv = itemView.findViewById(R.id.teamTv);
            eventTv = itemView.findViewById(R.id.eventTv);
            captionTv = itemView.findViewById(R.id.captionTv);
            avatarIV = itemView.findViewById(R.id.avatarIV);
            eventIV = itemView.findViewById(R.id.imageView);
            videoView = itemView.findViewById(R.id.videoView);

        }
    }
}
