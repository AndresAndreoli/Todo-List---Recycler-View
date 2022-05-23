package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        val recyclerViewTodo = findViewById<RecyclerView>(R.id.rvContainerTodoList)
        recyclerViewTodo.adapter = todoAdapter
        recyclerViewTodo.layoutManager = LinearLayoutManager(this)
    }

    fun addItem(v: View){
       val titleTodo = findViewById<EditText>(R.id.etTodoTitle).text.toString()
        if (titleTodo.isNotEmpty()){
            val todo = ItemTodo(titleTodo)
            todoAdapter.addTodo(todo)
        } else {
            Toast.makeText(this, "Add a Todo", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeItems(v: View){
        todoAdapter.deleteTodo()
    }

}