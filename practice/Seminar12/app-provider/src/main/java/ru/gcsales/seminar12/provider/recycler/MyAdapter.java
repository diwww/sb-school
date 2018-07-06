package ru.gcsales.seminar12.provider.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.gcsales.seminar12.provider.R;
import ru.gcsales.seminar12.provider.activities.NoteActivity;
import ru.gcsales.seminar12.provider.db.NoteEntity;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context mContext;
    private List<NoteEntity> mNoteEntities;

    public MyAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mNoteEntities = new ArrayList<>();
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
        final NoteEntity noteEntity = mNoteEntities.get(position);
        holder.mTitleTextView.setText(noteEntity.getTitle());
        holder.mTextTextView.setText(noteEntity.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(NoteActivity.newIntent(mContext, noteEntity.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteEntities.size();
    }

    public void setData(List<NoteEntity> noteEntities) {
        mNoteEntities.clear();
        mNoteEntities.addAll(noteEntities);
        notifyDataSetChanged();
    }

//    public void addNotes(List<NoteEntity> mNoteEntities) {
//        int startPos = mNoteEntities.size();
//        mNoteEntities.addAll(mNoteEntities);
//        notifyItemRangeInserted(startPos, mNoteEntities.size());
//    }
//
//    public void addNote(NoteEntity note) {
//        int pos = mNoteEntities.size();
//        mNoteEntities.add(note);
//        notifyItemInserted(pos);
//    }
}
