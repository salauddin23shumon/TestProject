package com.sss.testproject.listData.adapter;

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

public class ListAdapter extends RecyclerView.Adapter {

    private static final String TAG = "RecyclerAdapter";
    List<Resource> listData;
    private static final int HEADER_ITEM = 0;
    //Footer Item Type
    private static final int FOOTER_ITEM = 1;

    private List<RecyclerViewItem> recyclerViewItems;


    public ListAdapter(List<Resource> listData) {
        this.listData = listData;
    }



    @Override
    public int getItemViewType(int position) {

        return 0;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;

        if (viewType == HEADER_ITEM) {
            row = inflater.inflate(R.layout.image_row, parent, false);
            return new ViewHolderOne(row);
        } else if (viewType == FOOTER_ITEM) {
            row = inflater.inflate(R.layout.video_row, parent, false);
            return new ViewHolderTwo(row);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof ViewHolderOne) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            ((ViewHolderOne) holder).textView.setText(listData.get(position).getOwner().getUsername());
        } else if (holder instanceof ViewHolderTwo) {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
//            ((ViewHolderTwo) holder).videoView.set
//            viewHolderTwo.texViewQuote.setText(footer.getQuote());
//            viewHolderTwo.textViewAuthor.setText(footer.getAuthor());
//            Picasso.get().load(footer.getImageUrl()).into(footerHolder.imageViewFooter);

        }
    }

    public void updateList(List<Resource> listData) {
        this.listData=listData;
        notifyDataSetChanged();
        Log.d(TAG, "updateList: "+listData.size());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+listData.size());
        return listData.size();
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView textView, rowCountTextView;
        ImageView imageView;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {

        VideoView videoView;
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.textView);
        }
    }

}
