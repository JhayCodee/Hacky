package com.hawk.hacky.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hawk.hacky.R
import com.hawk.hacky.provider.Cursos

class CursosAdapter(private val cursosList:List<Cursos>): RecyclerView.Adapter<CursosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CursosViewHolder(layoutInflater.inflate(R.layout.cursos, parent, false))
    }

    override fun onBindViewHolder(holder: CursosViewHolder, position: Int) {
        val item = cursosList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = cursosList.count()


}