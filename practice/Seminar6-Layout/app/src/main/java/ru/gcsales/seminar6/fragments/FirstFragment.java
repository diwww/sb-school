package ru.gcsales.seminar6.fragments;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

import ru.gcsales.seminar6.Config;
import ru.gcsales.seminar6.R;
import ru.gcsales.seminar6.ViewCallback;
import ru.gcsales.seminar6.receivers.TextAndColorBroadcastReceiver;
import ru.gcsales.seminar6.services.FirstService;

public class FirstFragment extends Fragment {

    public static final int NUM_BUTTONS = 3;
    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;
    private Button[] mButtons;
    private Random mRandom = new Random();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mButtons = new Button[NUM_BUTTONS];
        mButtons[0] = view.findViewById(R.id.button_1);
        mButtons[1] = view.findViewById(R.id.button_2);
        mButtons[2] = view.findViewById(R.id.button_3);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReceiver, mFilter);
        getActivity().startService(FirstService.newIntent(getActivity()));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mReceiver);
        getActivity().stopService(FirstService.newIntent(getActivity()));
    }

    private void init() {
        mReceiver = new TextAndColorBroadcastReceiver(new ViewCallbackImpl());
        mFilter = new IntentFilter(Config.FILTER_1);
    }

    private class ViewCallbackImpl implements ViewCallback {

        @Override
        public void setText(String text) {
            int i = mRandom.nextInt(NUM_BUTTONS);
            mButtons[i].setText(text);
        }

        @Override
        public void setColor(int color) {
            int i = mRandom.nextInt(NUM_BUTTONS);
            mButtons[i].setTextColor(color);
        }
    }
}
