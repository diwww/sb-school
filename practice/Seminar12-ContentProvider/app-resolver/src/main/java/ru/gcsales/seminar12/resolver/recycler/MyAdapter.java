package ru.gcsales.seminar12.resolver.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar12.R;
import ru.gcsales.seminar12.resolver.Note;
import ru.gcsales.seminar12.resolver.activities.NoteActivity;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mContext;
    private List<Note> mNotes;

    public MyAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mNotes = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Note note = mNotes.get(position);
        holder.mTitleTextView.setText(note.getTitle());
        holder.mTextTextView.setText(note.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(NoteActivity.newIntent(mContext, note.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void setData(List<Note> notes) {
        mNotes.clear();
        mNotes.addAll(notes);
        notifyDataSetChanged();
    }

//    public void addNotes(List<NoteEntity> mNotes) {
//        int startPos = mNotes.size();
//        mNotes.addAll(mNotes);
//        notifyItemRangeInserted(startPos, mNotes.size());
//    }
//
//    public void addNote(NoteEntity note) {
//        int pos = mNotes.size();
//        mNotes.add(note);
//        notifyItemInserted(pos);
//    }
}
