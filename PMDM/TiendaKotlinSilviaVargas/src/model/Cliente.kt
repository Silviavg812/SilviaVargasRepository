package model

class Cliente(var id: Int, var nombre:String) {


    lateinit var carrito: ArrayList<Producto>
    var factura: Double = 0.0
    init {
        carrito = ArrayList()
    }

    fun agregarProductoCarrito (producto: Producto){
        carrito.add(producto)
        print("Producto agregado al carrito adecuadamente corrrectamente ")
    }
    fun mostrarCarrito () {
        if (carrito.isEmpty()){
            print("No hay nada en el carrito ")
        }
        carrito.forEach { it.mostrarDatos() }
    }
    fun eliminarProdcutoCarrito (identificador: Int){


        var listaFiltrada = carrito.filter {
            return@filter it.id == id
        } // el find solo te devuleve uno
        print("El numero de resultados es ${listaFiltrada.size}") // aqui ya tienes cuantos tienes porque filter te devuelve varios
        if (listaFiltrada.isEmpty()){
            print("No esta en el carrito.")
        } else if (listaFiltrada.size==1){
            print("Tienes mas que 1 de este producto. ¿Quieres borrar mas de ese uno?")
            carrito.removeFirst() // solo hay uno asi que con esto vale
            carrito.remove(listaFiltrada.first())
        } else {
            // hay varios
            carrito.removeAll(listaFiltrada.toSet()) // toSet ????
        }
        val opcion: String = readln()





    }
    fun pedirFactura (){
        if (carrito.isEmpty()){
            println("No hay productos en carrito")
        } else {
            carrito.forEach {
                factura+=it.precio
            }
            println("Debes un total de $factura")
            carrito.clear()
        }
    }


}