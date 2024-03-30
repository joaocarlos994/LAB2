package com.example.lab2.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lab2.data.db.NoteDatabase
import com.example.lab2.data.entities.Note
import com.example.lab2.data.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel {
    class NoteViewModel(application: Application) : AndroidViewModel(application){
        val readAllNotes: LiveData<List<Note>>
        private val repository: NotesRepository

        init {
            val noteDao = NoteDatabase.getDatabase(application).noteDao()
            repository = NotesRepository(noteDao)
            readAllNotes = repository.readAllNotes
        }

        fun  addNote(note: Note){
            viewModelScope.launch(Dispatchers.IO) {
                repository.addNote(note)
            }
        }

        fun updateNote(note: Note) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateNote(note)
            }
        }

        fun  deleteNote(note: Note) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteNote(note)
            }
        }
    }

}