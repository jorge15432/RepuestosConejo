package com.example.repuestosconejo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.repuestosconejo.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth


        binding.tvRegistrarse.setOnClickListener { Registro() }
        binding.btnIngresar.setOnClickListener {
            Login()

            val gso = GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
            )
                .requestIdToken(getString(R.string.default_web_client_id_r))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this, gso)
            binding.btGoogle.setOnClickListener { googleSignIn() }
        }
    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 5000)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 5000) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val cuenta = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(cuenta.idToken!!)
            } catch (e: ApiException) {


            }
        }


    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful)
                val user = auth.currentUser
                refresca(user)
            } else {
            refresca(null)

        }

    }

    private fun Login() {

        val email = binding.etCorreo.text.toString()
        val contra = binding.etContra.text.toString()

        Log.d("Autenticandose", "Haciendo llamado de autenticacion")
        //Utilizo el objeto auth para hacer el registro...
        auth.signInWithEmailAndPassword(email, contra)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {//Si se logro... se creo el usuario
                    Log.d("Autenticando", "se autentico")
                    val user = auth.currentUser
                    refresca(user)
                } else {//Si no se logro, hubo un error...
                    Log.e("Autenticando", "Error de Autenticacion")
                    Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                    refresca(null)

                }
            }
        Log.d("Autenticando", "Sale del proceso...")
    }


    private fun Registro() {
        val email = binding.etCorreo.text.toString()
        val contra = binding.etContra.text.toString()

        auth.createUserWithEmailAndPassword(email, contra).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                refresca(user)
            } else {
                Toast.makeText(baseContext, "Error", Toast.LENGTH_LONG).show()
                refresca(null)
            }
        }
    }

    private fun refresca(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }

}