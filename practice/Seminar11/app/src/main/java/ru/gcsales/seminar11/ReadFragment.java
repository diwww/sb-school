package ru.gcsales.seminar11;


import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ReadFragment extends Fragment {

    private static final String TAG = "ReadFragment";

    private TextView mTextView;
    private OnActivityCallback mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnActivityCallback) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_read, container, false);
        mTextView = root.findViewById(R.id.text_view);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            mTextView.setText(mCallback.getData());
        } catch (RemoteException e) {
            Log.e(TAG, "onViewCreated: ", e);
        }
    }

    public static ReadFragment newInstance(Context context, Bundle args) {
        ReadFragment fragment = new ReadFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
