package gcsales.ru.seminar18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ViewHolderBinder> mBinderList;
    private SparseArray<ViewHolderFactory> mFactorySparseArray;

    public ImageAdapter(Context context) {
        mContext = context;
        mBinderList = new ArrayList<>();
        mFactorySparseArray = new SparseArray<>();
        mFactorySparseArray.put(ImageType.PICASSO.type, new PicassoViewHolderFactory());
        mFactorySparseArray.put(ImageType.GLIDE.type, new GlideViewHolderFactory());
        mFactorySparseArray.put(ImageType.FRESCO.type, new FrescoViewHolderFactory());
        mFactorySparseArray.put(ImageType.HTTP.type, new HttpViewHolderFactory());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mFactorySparseArray.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        mBinderList.get(position).bindViewHolder(holder, mContext);
    }

    @Override
    public int getItemCount() {
        return mBinderList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mBinderList.get(position).getType();
    }

    public void setData(List<String> urls) {
        mBinderList.clear();
        for (int i = 0; i < urls.size(); i++) {
            int a = i % 4;
            switch (a) {
                case 0:
                    mBinderList.add(new PicassoViewHolderBinder(urls.get(i)));
                    break;
                case 1:
                    mBinderList.add(new GlideViewHolderBinder(urls.get(i)));
                    break;
                case 2:
                    mBinderList.add(new FrescoViewHolderBinder(urls.get(i)));
                    break;
                case 3:
                    mBinderList.add(new HttpViewHolderBinder(urls.get(i)));
                    break;
                default:
                    break;
            }
        }
    }

    public enum ImageType {
        PICASSO(0), GLIDE(1), FRESCO(2), HTTP(3);

        final int type;

        ImageType(int type) {
            this.type = type;
        }
    }

    public static class PicassoViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public PicassoViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }

    public static class FrescoViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView mImageView;

        public FrescoViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }

    public static class GlideViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public GlideViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }

    public static class HttpViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public HttpViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }
}
