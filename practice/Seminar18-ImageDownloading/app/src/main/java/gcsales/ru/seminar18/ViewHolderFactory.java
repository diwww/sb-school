package gcsales.ru.seminar18;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface ViewHolderFactory {

    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);
}
