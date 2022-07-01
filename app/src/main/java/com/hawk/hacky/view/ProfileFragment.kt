package com.hawk.hacky.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.hawk.hacky.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() =_binding!!
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater)
        binding.BtnLogout.setOnClickListener {
            auth.signOut()
            //Se accede de manera distinta desde un fragment
            //es algo como de HIJO A PADRE
            val intent = Intent (activity, LoginActivity::class.java)
            activity?.startActivity(intent)
            //llamar a activity desde fragment
        }
        return binding.root
    }





}


