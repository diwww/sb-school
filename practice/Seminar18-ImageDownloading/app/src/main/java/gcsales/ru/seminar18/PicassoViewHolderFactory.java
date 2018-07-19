package gcsales.ru.seminar18;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Фабрика для создания ViewHolder'ов для картинок, загруженных через Picasso
 */
public class PicassoViewHolderFactory implements ViewHolderFactory {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_picasso, parent, false);
        return new ImageAdapter.PicassoViewHolder(view);
    }
}
