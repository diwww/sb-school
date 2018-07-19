package ru.gcsales.seminar6.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import ru.gcsales.seminar6.R;
import ru.gcsales.seminar6.services.FourthService;


public class FourthFragment extends Fragment {

    private Timer mTimer;
    private TimerTask mTimerTask;
    private Button mButton;
    private boolean mBound = false;
    private ServiceConnection mServiceConnection = new MyServiceConnection();
    private FourthService mFourthService;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mButton = view.findViewById(R.id.button);

    }

    @Override
    public void onResume() {
        super.onResume();
        bindService();
        startTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        unbindService();
        stopTimer();
    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mFourthService = ((FourthService.LocalBinder) service).getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // The Android system calls this when the connection to the service is
            // unexpectedly lost, such as when the service has crashed or has been killed.
            // This is not called when the client unbinds.
            mBound = false;
            mFourthService = null;
        }
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (mBound) {
                final int angle = mFourthService.getAngle();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ConstraintLayout.LayoutParams params =
                                (ConstraintLayout.LayoutParams) mButton.getLayoutParams();
                        params.circleAngle = angle;
                        mButton.setLayoutParams(params);

                        mButton.setText(angle + "");
                    }
                });
            }
        }
    }

    private void startTimer() {
        mTimer = new Timer();
        mTimer.schedule(new MyTimerTask(), 1000, 1000);
    }

    private void stopTimer() {
        mTimer.cancel();
        mTimer = null;
    }

    private void bindService() {
        getActivity().bindService(FourthService.newIntent(FourthFragment.this.getActivity()), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        if (mBound) {
            getActivity().unbindService(mServiceConnection);
            mBound = false;
        }
    }
}
