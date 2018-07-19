package ru.gcsales.seminar9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

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

//    public void addNotes(List<Note> mNotes) {
//        int startPos = mNotes.size();
//        mNotes.addAll(mNotes);
//        notifyItemRangeInserted(startPos, mNotes.size());
//    }
//
//    public void addNote(Note note) {
//        int pos = mNotes.size();
//        mNotes.add(note);
//        notifyItemInserted(pos);
//    }
}
