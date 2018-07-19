package gcsales.ru.seminar18;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Фабрика для создания ViewHolder'ов
 */
public interface ViewHolderFactory {

    /**
     * Создать ViewHolder
     * @param parent родительская вью
     * @return новый инстанс ViewHolder'a
     */
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);
}
