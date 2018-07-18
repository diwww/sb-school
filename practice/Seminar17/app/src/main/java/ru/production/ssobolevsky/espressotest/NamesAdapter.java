package ru.production.ssobolevsky.espressotest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 16.07.2018.
 */

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.MyViewHolder> {

    private List<String> mData;
    private Context mContext;

    public NamesAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fio, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mName.setText(mData.get(position));
        holder.mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("DATA", mData.get(position));
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_rv_fio);
        }
    }

    public void setData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
