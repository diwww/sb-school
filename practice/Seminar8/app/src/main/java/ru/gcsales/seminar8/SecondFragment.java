package ru.gcsales.seminar8;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class SecondFragment extends Fragment {

    private static final String TAG = "SecondFragment";
    private TextView mTextView;
    private MyAsyncTask asyncTask;
    private boolean taskRunning = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTextView = view.findViewById(R.id.text_view);
    }


    @Override
    public void onResume() {
        super.onResume();
        startTask();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopTask();
    }

    private void startTask() {
        asyncTask = new MyAsyncTask();
        asyncTask.execute();
        taskRunning = true;
    }

    private void stopTask() {
        if (taskRunning) {
            asyncTask.cancel(true);
            taskRunning = false;
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Integer> {

        private Random random = new Random();

        @Override
        protected Integer doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return random.nextInt(100);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            mTextView.setText(integer.toString());
            startTask();
        }
    }
}
