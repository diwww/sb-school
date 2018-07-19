package gcsales.ru.seminar18;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


/**
 * ViewHolderBinder для отображения картинок через либу Glide
 */
public class GlideViewHolderBinder extends ViewHolderBinder {

    private String mImageUrl;

    /**
     * @param imageUrl url картинки для загрузки
     */
    public GlideViewHolderBinder(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, Context context) {
        ImageAdapter.GlideViewHolder glideViewHolder = (ImageAdapter.GlideViewHolder) viewHolder;
        Glide.with(context)
                .load(mImageUrl)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background))
                .into(glideViewHolder.mImageView);
    }

    @Override
    public int getType() {
        return ImageAdapter.ImageType.GLIDE.type;
    }
}
