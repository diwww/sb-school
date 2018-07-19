package ru.gcsales.seminar8;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ThirdFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    private static final int WHAT = 11;
    private static final int LOADER_ID = 1002;

    private Loader<String> mLoader;
    private TextView mTextView;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT) {
                String message = msg.obj.toString();
                mTextView.setText(message);
                return true;
            }
            return false;
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoader = getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        mLoader.forceLoad();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTextView = view.findViewById(R.id.text_view);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<String> loader = null;
        if (id == LOADER_ID) {
            loader = new ThirdFragmentLoader(getActivity());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        // send message
        Message message = new Message();
        message.what = WHAT;
        message.obj = data;
        message.setTarget(mHandler);
        message.sendToTarget();

        loader.forceLoad();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
}
