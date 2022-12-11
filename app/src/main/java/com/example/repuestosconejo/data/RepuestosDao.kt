package com.example.repuestosconejo.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.repuestosconejo.model.Repuestos
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class RepuestosDao {


    private val coleccion1 = "repuestosApp"
    private val usuario = Firebase.auth.currentUser?.nombre.toString()
    private val collection2 = "misRepuestos"

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

     fun saveRepuestos(repuestos: Repuestos){
         val documento: DocumentReference
         if (repuestos.id.isEmpty()) {
             documento = firestore
                 .collection(coleccion1)
                 .document(usuario)
                 .collection(collection2)
                 .document()
             repuestos.id = documento.id

         } else {
             documento = firestore
                 .collection(coleccion1)
                 .document(usuario)
                 .collection(collection2)
                 .document()
             repuestos.id = documento.id

         }
         documento.set(repuestos)
             .addOnSuccessListener {
                 Log.d("saveRepuestos", "repuestos agregado/actualizado")
             }
             .addOnCanceledListener {
                 Log.e("saveRepuestos", "repuestos NO agregado/actualizado")
             }
     }





     fun deleteRepuestos(repuestos: Repuestos){

if (repuestos.id.isNotEmpty()) {
    firestore
        .collection(coleccion1)
        .document(usuario)
        .collection(collection2)
        .document(repuestos.id)
        .delete()
        .addOnSuccessListener {
            Log.d("deleteRepuestos", "repuestos eliminado")
        }
        .addOnCanceledListener {
            Log.e("deleteRepuestos", "repuestos NO eliminado")
        }
}

}
    fun getRepuestos() : LiveData<List<Repuestos>>{
        val listaRepuestos = MutableLiveData<List<Repuestos>>()
        firestore
            .collection(coleccion1)
            .document(usuario)
            .collection(collection2)
            .addSnapshotListener { instantanea, error ->
                if (error != null) {
                    return@addSnapshotListener

                }
                if (instantanea != null) {
                    val lista = ArrayList<Repuestos>()

                    instantanea.documents.forEach {
                        val repuestos = it.toObject(Repuestos::class.java)
                        if (repuestos != null) {
                            lista.add(repuestos)


                        }
                    }
                    listaRepuestos.value = lista
                }
            }

        return listaRepuestos
}

}