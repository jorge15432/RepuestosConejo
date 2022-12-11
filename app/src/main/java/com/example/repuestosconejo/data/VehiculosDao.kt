package com.example.repuestosconejo.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.repuestosconejo.model.Vehiculos
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase


class VehiculosDao {

    private val coleccion1 = "vehiculosApp"
    private val usuario = Firebase.auth.currentUser?.email.toString()
    private val collection2 = "misVehiculos"

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun saveVehiculos(vehiculos: Vehiculos) {
        val documento: DocumentReference
        if (vehiculos.id.isEmpty()) {
        documento = firestore
            .collection(coleccion1)
            .document(usuario)
            .collection(collection2)
            .document()
        vehiculos.id = documento.id

    } else {
        documento = firestore
            .collection(coleccion1)
            .document(usuario)
            .collection(collection2)
            .document()
        vehiculos.id = documento.id

    }
    documento.set(vehiculos)
    .addOnSuccessListener {
        Log.d("saveVehiculos", "Vehiculos agregado/actualizado")
    }
    .addOnCanceledListener {
        Log.e("saveVehiculos", "vehiculos NO agregado/actualizado")
    }
}




     fun deleteVehiculos(vehiculos: Vehiculos){
         if (vehiculos.id.isNotEmpty()) {
             firestore
                 .collection(coleccion1)
                 .document(usuario)
                 .collection(collection2)
                 .document(vehiculos.id)
                 .delete()
                 .addOnSuccessListener {
                     Log.d("deleteVehiculos", "vehiculos eliminado")
                 }
                 .addOnCanceledListener {
                     Log.e("deleteVehiculos", "vehiculos NO eliminado")
                 }
         }

     }


    fun getVehiculos() : LiveData<List<Vehiculos>>{
        val listaVehiculos = MutableLiveData<List<Vehiculos>>()
        firestore
            .collection(coleccion1)
            .document(usuario)
            .collection(collection2)
            .addSnapshotListener { instantanea, error ->
                if (error != null) {
                    return@addSnapshotListener

                }
                if (instantanea != null) {
                    val lista = ArrayList<Vehiculos>()

                    instantanea.documents.forEach {
                        val vehiculos = it.toObject(Vehiculos::class.java)
                        if (vehiculos != null) {
                            lista.add(vehiculos)


                        }
                    }
                    listaVehiculos.value = lista
                }
            }

        return listaVehiculos
    }

}

