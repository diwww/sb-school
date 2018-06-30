package ru.gcsales.seminar7;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Random;

import ru.gcsales.seminar7.model.FirstItemModel;
import ru.gcsales.seminar7.model.SecondItemModel;
import ru.gcsales.seminar7.model.ThirdItemModel;
import ru.gcsales.seminar7.repository.ImageUrlRepository;
import ru.gcsales.seminar7.repository.TextRepository;

public class MyService extends Service {

    // Random data type chooser
    private Random random = new Random();
    private Thread thread;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            while (true) {
                Intent intent = new Intent(Config.ACTION);

                // Get random data set type
                Config.Extras[] extras = Config.Extras.values();
                Config.Extras value = extras[random.nextInt(extras.length)];

                switch (value) {
                    case FIRST:
                        intent.putExtra(value.toString(), new FirstItemModel(ImageUrlRepository.getImageUrl(), TextRepository.getText()));
                        break;
                    case SECOND:
                        intent.putExtra(value.toString(), new SecondItemModel(ImageUrlRepository.getImageUrl(), TextRepository.getText(), TextRepository.getText()));
                        break;
                    case THIRD:
                        intent.putExtra(value.toString(), new ThirdItemModel(TextRepository.getText()));
                        break;
                    default:
                        break;
                }

                LocalBroadcastManager.getInstance(MyService.this).sendBroadcast(intent);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // Return from method and stop thread execution
                    return;
                }
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        thread = new Thread(mRunnable);
        thread.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Interrupt execution thread
        // when stopService() is called
        thread.interrupt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MyService.class);
    }
}
