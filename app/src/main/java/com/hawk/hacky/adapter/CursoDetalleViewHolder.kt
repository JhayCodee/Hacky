package com.hawk.hacky.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hawk.hacky.databinding.CardCursosBinding
import com.hawk.hacky.provider.Cursos

class CursoDetalleViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding: CardCursosBinding = CardCursosBinding.bind(view)

    fun render (curso: Cursos) {

        binding.txtCurso.text = curso.nombre
        Glide.with(binding.ivPhoto.context).load(curso.img).into(binding.ivPhoto)
    }

}
