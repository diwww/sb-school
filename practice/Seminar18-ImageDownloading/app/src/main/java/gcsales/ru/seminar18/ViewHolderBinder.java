package gcsales.ru.seminar18;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Абстрактный ViewHolderBinder
 */
public abstract class ViewHolderBinder {

    /**
     * Сбиндить ViewHolder
     * @param viewHolder ViewHolder, который нужно сбиндить
     * @param context контекст
     */
    public abstract void bindViewHolder(RecyclerView.ViewHolder viewHolder, Context context);

    /**
     * Метод для получения типа ViewHolder'a
     * @return тип ViewHolder'a
     */
    public abstract int getType();
}
