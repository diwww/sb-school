package ru.gcsales.seminar7;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolders {

    public static class FirstViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public CircleImageView mImageView;

        public FirstViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
            mImageView = itemView.findViewById(R.id.circle_image_view);
        }
    }

    public static class SecondViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewFirst;
        public TextView mTextViewSecond;
        public CircleImageView mImageView;

        public SecondViewHolder(View itemView) {
            super(itemView);
            mTextViewFirst = itemView.findViewById(R.id.text_view_first);
            mTextViewSecond = itemView.findViewById(R.id.text_view_second);
            mImageView = itemView.findViewById(R.id.circle_image_view);
        }
    }

    public static class ThirdViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ThirdViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
        }
    }
}
