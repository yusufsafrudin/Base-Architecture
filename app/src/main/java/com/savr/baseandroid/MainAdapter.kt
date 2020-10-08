package com.savr.baseandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.savr.baseandroid.local.data.NoteEntity
import kotlinx.android.synthetic.main.item_note.view.*

class MainAdapter(private val listener: (NoteEntity, Int) -> Unit) :
    RecyclerView.Adapter<NoteViewHolder>() {

    private var notes = listOf<NoteEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindItem(notes[position], listener)
    }

    fun setNotes(notes: List<NoteEntity>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(note: NoteEntity, listener: (NoteEntity, Int) -> Unit) {
        itemView.text_note.text = note.note

        itemView.setOnClickListener {
            listener(note, layoutPosition)
        }
    }

}