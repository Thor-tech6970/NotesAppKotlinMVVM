package com.example.notesappkotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() , INotesRVAdapter {

    lateinit var viewModel: NotesViewModel
    lateinit var noteEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteEditText = findViewById(R.id.noteEditText)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = NotesRVAdapter(this , this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {
            adapter.updateList(it)
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
    }

    fun submitNote(view: View) {

        val newNote = noteEditText.text.toString()
        if(newNote.isNotEmpty()){
            viewModel.insertNote(Note(newNote))
            noteEditText.setText("")
        }
    }
}