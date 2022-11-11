package com.example.repuestosconejo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.repuestosconejo.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth=Firebase.auth


        binding.tvRegistrarse.setOnClickListener{Registro()}
        binding.btnIngresar.setOnClickListener{Login()}
    }

    private fun Login() {

    }

    private fun Registro() {
      val email=binding.etCorreo.text.toString()
        val contra=binding.etContra.text.toString()

        auth.createUserWithEmailAndPassword(email,contra).addOnCompleteListener(this){
            task -> if(task.isSuccessful){val user=auth.currentUser
            refresca(user)
            }else{
                Toast.makeText(baseContext,"Error",Toast.LENGTH_LONG).show()
            refresca(null)
            }
        }
    }

    private fun refresca(user: FirebaseUser?) {
        if(user!=null){
val intent= Intent(this,Home::class.java )
            startActivity(intent)
        }

    }
}