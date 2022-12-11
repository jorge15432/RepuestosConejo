package com.example.repuestosconejo.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.repuestosconejo.model.Pedidos
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase


class PedidosDao {


    private val coleccion1 = "pedidosApp"
    private val usuario = Firebase.auth.currentUser?.email.toString()
    private val collection2 = "misPedidos"

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

   fun savePedidos(pedidos: Pedidos){
       val documento: DocumentReference
       if (pedidos.id.isEmpty()) {
           documento = firestore
               .collection(coleccion1)
               .document(usuario)
               .collection(collection2)
               .document()
           pedidos.id = documento.id

       } else {
           documento = firestore
               .collection(coleccion1)
               .document(usuario)
               .collection(collection2)
               .document()
           pedidos.id = documento.id

       }
       documento.set(pedidos)
           .addOnSuccessListener {
               Log.d("savePedidos", "Pedidos agregado/actualizado")
           }
           .addOnCanceledListener {
               Log.e("savePedidos", "pedidos NO agregado/actualizado")
           }
   }




     fun deletePedidos(pedidos: Pedidos){
         if (pedidos.id.isNotEmpty()) {
             firestore
                 .collection(coleccion1)
                 .document(usuario)
                 .collection(collection2)
                 .document(pedidos.id)
                 .delete()
                 .addOnSuccessListener {
                     Log.d("deletePedidos", "pedidos eliminado")
                 }
                 .addOnCanceledListener {
                     Log.e("deletePedidos", "pedidos NO eliminado")
                 }
         }

     }

    fun getPedidos() : LiveData<List<Pedidos>>{
        val listaPedidos = MutableLiveData<List<Pedidos>>()
        firestore
            .collection(coleccion1)
            .document(usuario)
            .collection(collection2)
            .addSnapshotListener { instantanea, error ->
                if (error != null) {
                    return@addSnapshotListener

                }
                if (instantanea != null) {
                    val lista = ArrayList<Pedidos>()

                    instantanea.documents.forEach {
                        val pedidos = it.toObject(Pedidos::class.java)
                        if (pedidos != null) {
                            lista.add(pedidos)


                        }
                    }
                    listaPedidos.value = lista
                }
            }

        return listaPedidos
    }
}

