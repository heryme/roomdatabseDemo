package com.example.pavneet_singh.roomdemo

import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView

import com.artproficiencyapp.restapi.ApiResponseInterface
import com.artproficiencyapp.restapi.ApiResponseManager
import com.example.pavneet_singh.roomdemo.adapter.NotesAdapter
import com.example.pavneet_singh.roomdemo.notedb.NoteDatabase
import com.example.pavneet_singh.roomdemo.notedb.model.Note

import java.lang.ref.WeakReference
import java.util.ArrayList

class NoteListActivity : AppCompatActivity(), NotesAdapter.OnNoteItemClick, ApiResponseInterface {

    private var textViewMsg: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var noteDatabase: NoteDatabase? = null
    private var notes: MutableList<Note>? = null
    private var notesAdapter: NotesAdapter? = null
    private var pos: Int = 0

    private val listener = View.OnClickListener {
        //startActivityForResult(new Intent(NoteListActivity.this,AddNoteActivity.class),100);
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeVies()
        displayList()
    }

    private fun displayList() {
        noteDatabase = NoteDatabase.getInstance(this@NoteListActivity)
        RetrieveTask(this).execute()
    }

    override fun getApiResponse(apiResponseManager: ApiResponseManager<*>) {

    }

    private class RetrieveTask// only retain a weak reference to the activity
    internal constructor(context: NoteListActivity) : AsyncTask<Void, Void, List<Note>>() {

        private val activityReference: WeakReference<NoteListActivity>

        init {
            activityReference = WeakReference(context)
        }

        override fun doInBackground(vararg voids: Void): List<Note>? {
            return if (activityReference.get() != null)
                activityReference.get()!!.noteDatabase!!.noteDao.notes

            else
                null
        }

        override fun onPostExecute(notes: List<Note>?) {
            if (notes != null && notes.size > 0) {
                activityReference.get()?.notes!!.clear()
                activityReference.get()?.notes!!.addAll(notes)
                // hides empty text view
                activityReference.get()?.textViewMsg!!.visibility = View.GONE
                activityReference.get()?.notesAdapter!!.notifyDataSetChanged()
            }
        }
    }

    private fun initializeVies() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        textViewMsg = findViewById<View>(R.id.tv__empty) as TextView
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(listener)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView!!.layoutManager = LinearLayoutManager(this@NoteListActivity)
        notes = ArrayList()
        notesAdapter = NotesAdapter(notes, this@NoteListActivity)
        recyclerView!!.adapter = notesAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 100 && resultCode > 0) {
            if (resultCode == 1) {
                notes!!.add(data.getSerializableExtra("note") as Note)
            } else if (resultCode == 2) {
                notes!![pos] = data.getSerializableExtra("note") as Note
            }
            listVisibility()
        }
    }

    override fun onNoteClick(pos: Int) {
        AlertDialog.Builder(this@NoteListActivity)
                .setTitle("Select Options")
                .setItems(arrayOf("Delete", "Update")) { dialogInterface, i ->
                    when (i) {
                        0 -> {
                            noteDatabase!!.noteDao.deleteNote(notes!![pos])
                            notes!!.removeAt(pos)
                            listVisibility()
                        }
                        1 -> {
                            this@NoteListActivity.pos = pos
                            startActivityForResult(
                                    Intent(this@NoteListActivity,
                                            AddNoteActivity::class.java).putExtra("note", notes!![pos]),
                                    100)
                        }
                    }
                }.show()

    }

    private fun listVisibility() {
        var emptyMsgVisibility = View.GONE
        if (notes!!.size == 0) { // no item to display
            if (textViewMsg!!.visibility == View.GONE)
                emptyMsgVisibility = View.VISIBLE
        }
        textViewMsg!!.visibility = emptyMsgVisibility
        notesAdapter!!.notifyDataSetChanged()
    }

    override fun onDestroy() {
        noteDatabase!!.cleanUp()
        super.onDestroy()
    }
}
