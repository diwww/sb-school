package ru.gcsales.seminar12.resolver.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.gcsales.seminar12.R;
import ru.gcsales.seminar12.resolver.Note;
import ru.gcsales.seminar12.resolver.NoteRepository;
import ru.gcsales.seminar12.resolver.activities.NoteActivity;

public class EditNoteFragment extends Fragment {

    private EditText mTitleEditText;
    private EditText mTextEditText;
    private Button mSaveButton;
    private Note mNote;
    private NoteRepository mNoteRepository;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mNoteRepository = new NoteRepository(context);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putLong(NoteActivity.NOTE_ID_EXTRA, mNote.getId());
            ViewNoteFragment viewNoteFragment = ViewNoteFragment.newInstance(bundle);
            // Обновляем mNoteEntity
            mNote = new Note(mTitleEditText.getText().toString(),
                    mTextEditText.getText().toString(), mNote.getId());
            mNoteRepository.editNote(mNote);

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
        long id = bundle.getLong(NoteActivity.NOTE_ID_EXTRA);
        mNote = mNoteRepository.getNoteById(id);
        mTextEditText.setText(mNote.getText());
        mTitleEditText.setText(mNote.getTitle());
    }

    public static EditNoteFragment newInstance(Bundle bundle) {
        EditNoteFragment fragment = new EditNoteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
