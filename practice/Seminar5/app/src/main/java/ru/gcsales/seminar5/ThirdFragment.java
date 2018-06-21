package ru.gcsales.seminar5;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ThirdFragment extends Fragment {

    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = view.findViewById(R.id.text_view);
        mTextView.setText(getArguments().getString(Config.TEXT_EXTRA));
    }

    public static ThirdFragment newInstance(Bundle bundle) {
        ThirdFragment fragment = new ThirdFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
