package ru.gcsales.seminar12.resolver.fragments;

import android.content.Context;
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

import ru.gcsales.seminar12.R;
import ru.gcsales.seminar12.resolver.Note;
import ru.gcsales.seminar12.resolver.NoteRepository;
import ru.gcsales.seminar12.resolver.activities.NoteActivity;


public class ViewNoteFragment extends Fragment {

    private TextView mTitleTextView;
    private TextView mTextTextView;
    private Note mNote;
    private NoteRepository mNoteRepository;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mNoteRepository = new NoteRepository(context);
    }

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
        Note note = mNoteRepository.getNoteById(id);

        mTextTextView.setText(note.getText());
        mTitleTextView.setText(note.getTitle());
    }

    public static ViewNoteFragment newInstance(Bundle bundle) {
        ViewNoteFragment fragment = new ViewNoteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
