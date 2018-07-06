package ru.gcsales.seminar12.provider.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.gcsales.seminar12.provider.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView mTitleTextView;
    public TextView mTextTextView;

    private View view;

    public MyViewHolder(View itemView) {
        super(itemView);
        mTitleTextView = itemView.findViewById(R.id.text_title);
        mTextTextView = itemView.findViewById(R.id.text_note);
    }
}
