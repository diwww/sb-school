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
import android.widget.Button;
import android.widget.EditText;


public class WriteFragment extends Fragment {

    private static final String TAG = "WriteFragment";
    private EditText mEditText;
    private Button mButton;
    private OnActivityCallback mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnActivityCallback) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_write, container, false);
        mEditText = root.findViewById(R.id.edit_text);
        mButton = root.findViewById(R.id.button_save);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mCallback.setData(mEditText.getText().toString());
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_slot, ReadFragment.newInstance(getActivity(), null))
                            .commit();
                } catch (RemoteException e) {
                    Log.e(TAG, "onClick: ", e);
                }
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            mEditText.setText(mCallback.getData());
        } catch (RemoteException e) {
            Log.e(TAG, "onViewCreated: ", e);
        }
    }

    public static WriteFragment newInstance(Context context, Bundle args) {
        WriteFragment fragment = new WriteFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
