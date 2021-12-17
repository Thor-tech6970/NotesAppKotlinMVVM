package com.example.notesappkotlinmvvm

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao: NoteDao) {

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    fun getAllNotes() : LiveData<List<Note>>{
        return noteDao.getNotes()
    }



}