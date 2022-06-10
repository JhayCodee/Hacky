package com.hawk.hacky.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hawk.hacky.R
import com.hawk.hacky.databinding.CursosBinding
import com.hawk.hacky.provider.Cursos

class CursosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding: CursosBinding = CursosBinding.bind(view)

    fun render (curso: Cursos) {

        binding.titulo.text = curso.nombre
        Glide.with(binding.imagenCurso.context).load(curso.img).into(binding.imagenCurso)
    }

}