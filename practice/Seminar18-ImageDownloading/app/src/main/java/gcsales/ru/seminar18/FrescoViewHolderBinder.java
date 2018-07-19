package gcsales.ru.seminar18;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * ViewHolderBinder для отображения картинок через либу Fresco
 */
public class FrescoViewHolderBinder extends ViewHolderBinder {

    private String mImageUrl;

    /**
     * @param imageUrl url картинки для загрузки
     */
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
