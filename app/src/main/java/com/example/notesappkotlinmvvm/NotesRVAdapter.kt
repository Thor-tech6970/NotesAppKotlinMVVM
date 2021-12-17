package com.example.notesappkotlinmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList


class NotesRVAdapter(private val context: Context, private val listener : INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    val allNotes =  ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val holder: ViewHolder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentNote = allNotes[position]
        holder.note.text = currentNote.text
        holder.delete.setOnClickListener(View.OnClickListener {
            listener.onItemClicked(allNotes[position])
        })
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var note : TextView
        var delete : ImageView

        init {

            note = itemView.findViewById(R.id.textView)
            delete = itemView.findViewById(R.id.delete)

        }
    }
}

interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}

