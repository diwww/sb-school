package ru.gcsales.seminar13.recyclerview;

import android.support.v7.widget.RecyclerView;

import ru.gcsales.seminar13.models.BaseItem;

public abstract class ViewHolderBinder {

    public final int viewType;

    public ViewHolderBinder(int viewType) {
        this.viewType = viewType;
    }

    public abstract void bindViewHolder(RecyclerView.ViewHolder viewHolder);

    public abstract BaseItem getItem();
}
