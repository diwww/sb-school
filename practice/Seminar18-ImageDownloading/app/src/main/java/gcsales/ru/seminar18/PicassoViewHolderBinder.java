package gcsales.ru.seminar18;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class PicassoViewHolderBinder extends ViewHolderBinder {

    private String mImageUrl;

    public PicassoViewHolderBinder(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, Context context) {
        ImageAdapter.PicassoViewHolder picassoViewHolder = (ImageAdapter.PicassoViewHolder) viewHolder;
        Picasso.get()
                .load(mImageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(picassoViewHolder.mImageView);
    }

    @Override
    public int getType() {
        return ImageAdapter.ImageType.PICASSO.type;
    }
}
