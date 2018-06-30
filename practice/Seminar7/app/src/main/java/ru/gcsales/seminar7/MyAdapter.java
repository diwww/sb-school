package ru.gcsales.seminar7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar7.model.BaseModel;
import ru.gcsales.seminar7.model.FirstItemModel;
import ru.gcsales.seminar7.model.SecondItemModel;
import ru.gcsales.seminar7.model.ThirdItemModel;
import ru.gcsales.seminar7.repository.ImageUrlRepository;
import ru.gcsales.seminar7.repository.TextRepository;


public class MyAdapter extends RecyclerView.Adapter {

    private static final String TAG = "MyAdapter";
    private int i = 0;
    private int j = 0;

    private List<BaseModel> mData = new ArrayList<>();
    private Context mContext;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + i++);

        // FIXME: make factory
        if (viewType == Config.Extras.FIRST.getType()) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first, parent, false);
            return new ViewHolders.FirstViewHolder(v);
        }
        if (viewType == Config.Extras.SECOND.getType()) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_second, parent, false);
            return new ViewHolders.SecondViewHolder(v);
        }
        if (viewType == Config.Extras.THIRD.getType()) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_third, parent, false);
            return new ViewHolders.ThirdViewHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + j++);
        // FIXME: make view holder binder
        BaseModel model = mData.get(position);
        int type = model.getType();
        if (type == Config.Extras.FIRST.getType()) {
            ViewHolders.FirstViewHolder viewHolder = ((ViewHolders.FirstViewHolder) holder);
            viewHolder.mTextView.setText(((FirstItemModel) model).getText());
            Glide.with(mContext)
                    .asBitmap()
                    .load(((FirstItemModel) model).getImageUrl())
                    .into(viewHolder.mImageView);
        }

        if (type == Config.Extras.SECOND.getType()) {
            ViewHolders.SecondViewHolder viewHolder = ((ViewHolders.SecondViewHolder) holder);
            viewHolder.mTextViewFirst.setText(((SecondItemModel) model).getTextFirst());
            viewHolder.mTextViewSecond.setText(((SecondItemModel) model).getTextSecond());
            Glide.with(mContext)
                    .asBitmap()
                    .load(((SecondItemModel) model).getImageUrl())
                    .into(viewHolder.mImageView);
        }
        if (type == Config.Extras.THIRD.getType()) {
            ViewHolders.ThirdViewHolder viewHolder = ((ViewHolders.ThirdViewHolder) holder);
            viewHolder.mTextView.setText(((ThirdItemModel) model).getText());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    public void addItem(BaseModel model) {
        mData.add(model);
        this.notifyItemInserted(mData.size() - 1);
        // TODO: diff utils 
    }
}
