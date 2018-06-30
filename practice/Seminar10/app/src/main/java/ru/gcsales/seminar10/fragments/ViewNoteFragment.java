package ru.gcsales.seminar10.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.gcsales.seminar10.DBHandlerThread;
import ru.gcsales.seminar10.NoteActivity;
import ru.gcsales.seminar10.NoteEntity;
import ru.gcsales.seminar10.R;


public class ViewNoteFragment extends Fragment {

    private TextView mTitleTextView;
    private TextView mTextTextView;
    private NoteEntity mNoteEntity;
    private DBHandlerThread mHandlerThread = DBHandlerThread.getInstance(getActivity());
    private Handler mUIHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mNoteEntity = (NoteEntity) msg.obj;
            mTextTextView.setText(mNoteEntity.getText());
            mTitleTextView.setText(mNoteEntity.getTitle());

            return true;
        }
    });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTextTextView = view.findViewById(R.id.text_note);
        mTitleTextView = view.findViewById(R.id.text_title);

        Bundle bundle = getArguments();
        long id = bundle.getLong(NoteActivity.NOTE_ID_EXTRA);
        mHandlerThread.getNoteById(mUIHandler, id);
    }

    public static ViewNoteFragment newInstance(Bundle bundle) {
        ViewNoteFragment fragment = new ViewNoteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
