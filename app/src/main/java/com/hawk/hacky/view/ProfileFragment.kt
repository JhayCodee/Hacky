package com.hawk.hacky.view

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.FirebaseStorageKtxRegistrar
import com.hawk.hacky.databinding.FragmentProfileBinding
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() =_binding!!
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    //variable de imagen
    lateinit var ImageUri : Uri




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
        //Boton de seleccionar Imagen
        binding.Btnselecc.setOnClickListener {
            selectImage()
        }
        //Btn de subir Imagen
        binding.Btnsubir.setOnClickListener {
            uploadImage()
        }
        return binding.root
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        //Codigo viejo
        startActivityForResult(intent,100)

        //Codigo nuevo
        //ActivityResultContracts.StartActivityForResult()
    }

    private fun uploadImage(){
        val progressDialog= ProgressDialog(activity)
        progressDialog.setMessage("Subiendo archivo...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        //Patron a seguir para nombre de Imagen
        val formatter = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault())
        //agarramos fecha de hoy
        val now = Date()
        //Nombra
        val filename = formatter.format(now)

        val storageReference = FirebaseStorage.getInstance().getReference("image/$filename")

        storageReference.putFile(ImageUri).addOnSuccessListener {
            binding.ProfilePic.setImageURI(null)
            Toast.makeText(activity,"Se ha subido su imagen :D",Toast.LENGTH_SHORT).show()
            if(progressDialog.isShowing){
                progressDialog.dismiss()
            }
        }.addOnFailureListener{
            Toast.makeText(activity,"No se pudio :C",Toast.LENGTH_SHORT).show()
            if(progressDialog.isShowing){
                progressDialog.dismiss()
            }
        }
    }
    //con esta retiramos el nombre del usuario
    private fun RetUser(){
        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("users")
        usersRef.document().get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    val email = document.getString("email")
                    val pass = document.getString("pass")
                    val user = document.getString("user")
                    Log.d(TAG,"$email/$pass/$user")
                } else {
                    Log.d(TAG, "The document doesn't exist.")
                }
            } else {
                task.exception?.message?.let {
                    Log.d(TAG, it)
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode== 100 && resultCode== RESULT_OK){
            ImageUri = data?.data!!
            with(binding) { ProfilePic.setImageURI(ImageUri) }
        }
    }




}


