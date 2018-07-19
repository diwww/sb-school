package ru.gcsales.seminar8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment implements LoaderManager.LoaderCallbacks<Integer> {

    private static final int LOADER_ID = 1001;
    private TextView mTextView;
    private Loader<Integer> mLoader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoader = getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        mLoader.forceLoad();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTextView = view.findViewById(R.id.text_view);
    }

    @Override
    public Loader<Integer> onCreateLoader(int id, Bundle args) {
        Loader<Integer> loader = null;
        if (id == LOADER_ID) {
            loader = new FirstFragmentLoader(getActivity(), args);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Integer> loader, Integer data) {
        mTextView.setBackgroundColor(data);
        loader.forceLoad();
    }

    @Override
    public void onLoaderReset(Loader<Integer> loader) {
    }
}
