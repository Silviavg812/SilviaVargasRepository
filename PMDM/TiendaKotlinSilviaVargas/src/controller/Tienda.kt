package controller

import model.Producto
import util.Tipo

class Tienda () {

    var caja: Double = 0.0
    lateinit var almacen: ArrayList<Producto?>

    init {

        almacen = ArrayList()
        almacen.add(Producto(2,999.99,Tipo.Tecnologia,"IPhone17","Nuevo movil de Apple"))
        almacen.add(Producto(12,145.00,Tipo.Muebles,"Mesa madera","Mesa moderna hecha con madera de roble"))
        almacen.add(Producto(70,89.99,Tipo.Ropa,"Sudadera Nike","Sudadera XL negra de la marca Nike"))
        almacen.add(Producto(24,23.2,Tipo.Generica,"Set de pinzas","Pinzas para el pelo"))
    }

    fun mostrarDatosAlmacen () {
        var nulos = 0;
        almacen.forEach {
            it?.mostrarDatos() ?: nulos++ } // puedes decir que ejecute o que hay detras del ?
        if (nulos == almacen.size){
            println("No hay productos en el almacen")
        }
    }

    fun isAvailable () : Boolean{
        var nulos = 0
        almacen.forEach { it?: nulos++}
        if (nulos == almacen.size){
            return false
        }
        return false
    }

    fun addProduct (producto: Producto){
        for (item in 0..almacen.size-1){
            if (almacen[item] == null){
                almacen[item] = producto
                return // para pasar rompe la ejecucion del metodo
            }
        }
        println("El almacen esta completo")
    }


    fun sellProduct (id: Int){
        for (i in 0..almacen.size){
            // no es nulo lo que accedo
            if (almacen[i]!= null && almacen[i]?.id == id){
                caja += almacen[i]!!.precio
                almacen[i] = null
                return
            }
        }
        println("El id indicado no esta en el almacen, lista")
    }


    fun buscarProductosCategoria (categoria: Tipo){
        val filtro: List<Producto?> = almacen.filter {
            return@filter it?.categoria == categoria
        } as ArrayList<Producto?>
        println("El numero de elemnetos resultantes es ${filtro.size}")

    }

    fun buscarProductoID (id: Int){
        almacen.forEach { it }
        almacen.find {
            return@find it?.id == id
        }
        val productoBusqueda: Producto? = almacen.find {
            return@find it?.id == id
        }
    }






}