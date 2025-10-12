package model

import util.Tipo

class Producto(var id : Int = 0,
               var precio : Double = 10.3,
               var categoria: Tipo = Tipo.Generica,
               var nombre: String?,
               var descripcion: String?) {


    fun mostrarDatos (){
        println("$id," +
                "$precio," +
                "${nombre?:"SIN DEFINIR"}," +
                "${descripcion?:"SIN DESCRIPCION"}")
    }
}