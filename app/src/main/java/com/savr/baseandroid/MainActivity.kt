package com.savr.baseandroid

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.savr.baseandroid.local.data.NoteEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter { noteEntity, _ ->
            showAlertMenu(noteEntity)
        }
        recycler.adapter = adapter

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getNotes()?.observe(this, Observer {
            adapter.setNotes(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addMenu -> showAlertDialogAdd()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialogAdd() {
        val alert = AlertDialog.Builder(this)

        val editText = EditText(applicationContext)
        editText.hint = "Enter your text"

        alert.setTitle("New Note")
        alert.setView(editText)

        alert.setPositiveButton("Save") { dialog, _ ->
            mainViewModel.insertNote(
                NoteEntity(note = editText.text.toString())
            )
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.show()
    }


    private fun showAlertMenu(note: NoteEntity) {
        val items = arrayOf("Edit", "Delete")

        val builder = AlertDialog.Builder(this)
        builder.setItems(items) { _, which ->
            // the user clicked on colors[which]
            when (which) {
                0 -> {
                    showAlertDialogEdit(note)
                }
                1 -> {
                    mainViewModel.deleteNote(note)
                }
            }
        }
        builder.show()
    }

    private fun showAlertDialogEdit(note: NoteEntity) {
        val alert = AlertDialog.Builder(this)

        val editText = EditText(applicationContext)
        editText.setText(note.note)

        alert.setTitle("Edit Note")
        alert.setView(editText)

        alert.setPositiveButton("Update") { dialog, _ ->
            note.note = editText.text.toString()
            mainViewModel.updateNote(note)
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.show()
    }
}
