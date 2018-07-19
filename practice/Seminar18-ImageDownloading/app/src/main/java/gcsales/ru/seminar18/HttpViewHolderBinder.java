package gcsales.ru.seminar18;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpViewHolderBinder extends ViewHolderBinder {

    private String mImageUrl;

    public HttpViewHolderBinder(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, Context context) {
        final ImageAdapter.HttpViewHolder httpViewHolder = (ImageAdapter.HttpViewHolder) viewHolder;
        // FIXME:
        new AsyncTask<String, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(String... strings) {
                if (strings.length > 0) {
                    return getImage(strings[0]);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                httpViewHolder.mImageView.setImageBitmap(bitmap);
            }
        }.execute(mImageUrl);
    }

    @Override
    public int getType() {
        return ImageAdapter.ImageType.HTTP.type;
    }


    private static Bitmap getImage(URL url) {
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

    private static Bitmap getImage(String urlString) {
        try {
            URL url = new URL(urlString);
            return getImage(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
