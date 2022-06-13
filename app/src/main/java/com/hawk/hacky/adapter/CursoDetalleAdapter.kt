package com.hawk.hacky.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hawk.hacky.R
import com.hawk.hacky.adapter.CursoDetalleViewHolder
import com.hawk.hacky.adapter.CursosViewHolder
import com.hawk.hacky.provider.Cursos

class CursoDetalleAdapter(private val cursosList:List<Cursos>): RecyclerView.Adapter<CursoDetalleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoDetalleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CursoDetalleViewHolder(layoutInflater.inflate(R.layout.card_cursos, parent, false))
    }

    override fun onBindViewHolder(holder: CursoDetalleViewHolder, position: Int) {
        val item = cursosList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = cursosList.count()

}