package ru.gcsales.seminar9.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.gcsales.seminar9.DBHandlerThread;
import ru.gcsales.seminar9.Note;
import ru.gcsales.seminar9.NoteActivity;
import ru.gcsales.seminar9.R;

import static ru.gcsales.seminar9.NoteActivity.NOTE_ID_EXTRA;

public class EditNoteFragment extends Fragment {

    private EditText mTitleEditText;
    private EditText mTextEditText;
    private Button mSaveButton;
    private Note note;
    private DBHandlerThread mHandlerThread = DBHandlerThread.getInstance(getActivity());
    private Handler mUIHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            note = (Note) msg.obj;
            mTextEditText.setText(note.getText());
            mTitleEditText.setText(note.getTitle());

            return true;
        }
    });
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putLong(NOTE_ID_EXTRA, note.getId());
            ViewNoteFragment viewNoteFragment = ViewNoteFragment.newInstance(bundle);
            // Обновляем note
            note = new Note(mTitleEditText.getText().toString(),
                    mTextEditText.getText().toString(), note.getId());
            mHandlerThread.editNote(note);
            // Можно сделать addToBackStack, наверное
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, viewNoteFragment)
                    .commit();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTitleEditText = view.findViewById(R.id.edit_text_title);
        mTextEditText = view.findViewById(R.id.edit_text_note);
        mSaveButton = view.findViewById(R.id.button_save);
        mSaveButton.setOnClickListener(mOnClickListener);

        Bundle bundle = getArguments();
        long id = bundle.getLong(NOTE_ID_EXTRA);
        mHandlerThread.getNoteById(mUIHandler, id);
    }

    public static EditNoteFragment newInstance(Bundle bundle) {
        EditNoteFragment fragment = new EditNoteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
