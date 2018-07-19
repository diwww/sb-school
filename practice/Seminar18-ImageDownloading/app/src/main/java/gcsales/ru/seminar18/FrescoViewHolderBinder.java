package gcsales.ru.seminar18;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public class FrescoViewHolderBinder extends ViewHolderBinder {

    private String mImageUrl;

    public FrescoViewHolderBinder(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, Context context) {
        ImageAdapter.FrescoViewHolder frescoViewHolder = (ImageAdapter.FrescoViewHolder) viewHolder;
        frescoViewHolder.mImageView.setImageURI(mImageUrl);
    }

    @Override
    public int getType() {
        return ImageAdapter.ImageType.FRESCO.type;
    }
}
