package com.hawk.hacky.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hawk.hacky.databinding.CardCursosBinding
import com.hawk.hacky.provider.Cursos
import com.hawk.hacky.view.DetalleActivity

class CursoDetalleViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding: CardCursosBinding = CardCursosBinding.bind(view)

    fun render (curso: Cursos) {

        binding.txtCurso.text = curso.nombre
        Glide.with(binding.ivPhoto.context).load(curso.img).into(binding.ivPhoto)

        binding.imagePost.setOnClickListener {

            val intent = Intent(binding.imagePost.context, DetalleActivity::class.java)
            intent.putExtra("img", curso.img)
            intent.putExtra("link", curso.link)
            intent.putExtra("nombre", curso.nombre)
            intent.putExtra("desc", curso.descripcion)
            binding.imagePost.context.startActivity(intent)
        }

    }

}
