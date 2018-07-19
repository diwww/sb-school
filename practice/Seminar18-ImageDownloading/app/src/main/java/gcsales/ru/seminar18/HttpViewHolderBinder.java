package gcsales.ru.seminar18;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ViewHolderBinder для отображения картинок через http соединение
 */
public class HttpViewHolderBinder extends ViewHolderBinder {

    private String mImageUrl;

    /**
     * @param imageUrl url картинки для загрузки
     */
    public HttpViewHolderBinder(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, Context context) {
        final ImageAdapter.HttpViewHolder httpViewHolder = (ImageAdapter.HttpViewHolder) viewHolder;
        // Set image placeholder
        httpViewHolder.mImageView.setImageResource(R.drawable.ic_launcher_background);

        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = getImage(mImageUrl);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpViewHolder.mImageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }

    @Override
    public int getType() {
        return ImageAdapter.ImageType.HTTP.type;
    }

    private Bitmap getImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private Bitmap getImage(String urlString) {
        try {
            URL url = new URL(urlString);
            return getImage(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
