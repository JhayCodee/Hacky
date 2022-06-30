package com.hawk.hacky.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.hawk.hacky.R
import com.hawk.hacky.adapter.CursosAdapter
import com.hawk.hacky.databinding.FragmentCursosBinding
import com.hawk.hacky.databinding.FragmentHomeBinding
import com.hawk.hacky.provider.Canales
import com.hawk.hacky.provider.Cursos

class CursosFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCursosBinding.inflate(inflater)
        return binding.root
    }
    // vb
    private var _binding: FragmentCursosBinding? = null
    private val binding: FragmentCursosBinding get() =_binding!!

    // db
    private lateinit var dbref : DatabaseReference
    private lateinit var cursosRecyclerview : RecyclerView
    private lateinit var cursosArrayList : ArrayList<Cursos>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadView()

        binding.btnFrontend.setOnClickListener {
            getCursosPorCondicion("frontend", false)
        }

        binding.btnBackend.setOnClickListener {
            getCursosPorCondicion("backend", false)
        }

        binding.btnCloud.setOnClickListener {
            getCursosPorCondicion("cloud", false)
        }

    }

    fun loadView() {

        cursosRecyclerview = binding.cursosRV
        cursosRecyclerview.layoutManager = LinearLayoutManager(context)
        cursosRecyclerview.setHasFixedSize(true)
        cursosArrayList = arrayListOf<Cursos>()

        getCursosPorCondicion("", true)
    }

    fun getCursosPorCondicion(categoria:String, todos:Boolean) {

        cursosArrayList.clear()

        dbref = FirebaseDatabase.getInstance().getReference("Cursos")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (cursoSnapshot in snapshot.children) {

                        val curso = cursoSnapshot.getValue(Cursos::class.java)

                        if (todos) {
                            cursosArrayList.add(curso!!)
                        }
                        else {
                            if (curso?.categoria == categoria) {
                                cursosArrayList.add(curso)
                            }
                        }
                    }

                    binding.cursosRV.adapter = CursoDetalleAdapter(cursosArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("sin implementar")
            }

        })
    }


}