import controller.Tienda
import model.Cliente
import model.Producto
import util.Tipo

fun main() {

    var opcion: Int = 0;

    var tienda = Tienda()
    var cliente = Cliente(nombre = "Juan", id = 1)



    do {
        print("""------ TIENDA -----
            |1. Seccion cliente
            |2. Area privada tienda
        """.trimMargin())
        print("\nElige una opcion: ")
        opcion = readln().toInt()
        if (opcion == 1){
            do {
                print("""---- AREA PRIVADA DE LA TIENDA ----
                |1. Mostrar almacen 
                |2. Añadir producto 
                |3. Vender producto
                |4. Buscar producto por ID
                |5. Salir al menu general
            """.trimMargin())

                var opcionTienda = readln().toInt()
                when(opcionTienda){
                    1 -> tienda.mostrarDatosAlmacen()
                    2 -> tienda.addProduct(Producto(34,78.99, Tipo.Muebles, "Silla comoda","Solo es comoda"))
                    3 -> tienda.sellProduct(2)
                    4 -> tienda.buscarProductoID(2)
                }
            } while (opcionTienda!=5)
        } else if (opcion == 2){

            do {

                print("""---- AREA CLIENTE DE LA TIENDA ----
                |1. Mostrar carrito 
                |2. Agregar producto al carrito 
                |3. Eliminar producto al carrito 
                |4. Pedir factura
                |5. Salir al menu general 
            """.trimMargin())
                var opcionCliente = readln().toInt()
                when(opcionCliente){
                    1 -> cliente.mostrarCarrito()
                    2 -> cliente.agregarProductoCarrito(Producto(34,78.99, Tipo.Muebles, "Silla comoda","Solo es comoda"))
                    3 -> cliente.eliminarProdcutoCarrito(2)
                    4 -> cliente.pedirFactura()

                }
            } while (opcionCliente!=5)
        } else{
            print("Opcion no valida")
            return
        }
    } while (true)

}