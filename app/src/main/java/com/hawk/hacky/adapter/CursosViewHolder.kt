package com.hawk.hacky.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hawk.hacky.R
import com.hawk.hacky.databinding.CursosBinding
import com.hawk.hacky.provider.Cursos
import com.hawk.hacky.view.DetalleActivity

class CursosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding: CursosBinding = CursosBinding.bind(view)

    fun render (curso: Cursos) {

        binding.titulo.text = curso.nombre
        Glide.with(binding.imagenCurso.context).load(curso.img).into(binding.imagenCurso)

        binding.itemCardView.setOnClickListener {

            val intent = Intent(binding.itemCardView.context, DetalleActivity::class.java)
            intent.putExtra("img", curso.img)
            intent.putExtra("link", curso.link)
            intent.putExtra("nombre", curso.nombre)
            intent.putExtra("desc", curso.descripcion)
            binding.itemCardView.context.startActivity(intent)
        }

    }

}