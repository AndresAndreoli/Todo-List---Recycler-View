package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private var todoList: MutableList<ItemTodo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TodoViewHolder(layoutInflater.inflate(
            R.layout.item_todo,
            parent,
            false))
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // Agarra la informacion de cada elemento de la lista y se la asigna a la VIEW
        val item: ItemTodo = todoList[position]

        val title =  holder.itemView.findViewById<TextView>(R.id.tvTodoTitle)
        val checked =  holder.itemView.findViewById<CheckBox>(R.id.cbDone)


        title.text = item.title
        checked.isChecked = item.checked
        toggleStrikeThrough(title, item.checked)
        checked.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(title, isChecked)
            item.checked = !item.checked
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun addTodo(item: ItemTodo){
        todoList.add(item)
        notifyItemInserted(todoList.size-1)
    }

    fun deleteTodo(){
        todoList.removeAll { todo ->
            todo.checked // Me remueve los elementos checked == true
        }
        notifyDataSetChanged()
    }

}