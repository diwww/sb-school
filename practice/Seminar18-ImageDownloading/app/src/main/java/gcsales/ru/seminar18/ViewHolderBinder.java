package gcsales.ru.seminar18;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public abstract class ViewHolderBinder {

    public abstract void bindViewHolder(RecyclerView.ViewHolder viewHolder, Context context);

    public abstract int getType();
}
