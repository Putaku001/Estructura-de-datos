import java.util.*

data class PupusaOrder(val type: String, val quantity: Int)

data class Order(val customerName: String, val pupusas: List<PupusaOrder>)

interface OrderSystem {
    fun registerNewOrder()
    fun viewPendingOrders()
    fun dispatchOrder()
    fun viewDispatchedOrders()
}

class PupuseriaSystem : OrderSystem {
    private val pendingOrders: Queue<Order> = LinkedList()
    private val dispatchedOrders: MutableList<Order> = mutableListOf()

    override fun registerNewOrder() {
        val customerName = readCustomerName() ?: return
        val pupusaOrders = readPupusaOrders()
        
        if (pupusaOrders.isEmpty()) {
            println("No se pudo registrar la orden. No se ingresaron pupusas válidas.")
            return
        }
        
        val order = Order(customerName, pupusaOrders)
        pendingOrders.offer(order)
        
        println("Orden registrada para $customerName: ${order.formatPupusas()}.")
    }

    private fun readCustomerName(): String? {
        println("Ingrese el nombre del cliente:")
        return readLine()?.takeIf { it.isNotBlank() }
    }

    private fun readPupusaOrders(): List<PupusaOrder> {
        println("Cuantos tipos de pupusas desea ordenar?:")
        val typesCount = readLine()?.toIntOrNull() ?: return emptyList()
        
        return (1..typesCount).mapNotNull { i ->
            println("Ingrese el tipo de pupusa #$i:")
            val type = readLine()?.takeIf { it.isNotBlank() } ?: return@mapNotNull null
            
            println("Ingrese la cantidad de pupusas de $type:")
            val quantity = readLine()?.toIntOrNull()?.takeIf { it > 0 } ?: return@mapNotNull null
            
            PupusaOrder(type, quantity)
        }
    }

    override fun viewPendingOrders() {
        displayOrders("Ordenes pendientes:", pendingOrders)
    }

    override fun dispatchOrder() {
        val order = pendingOrders.poll()
        if (order != null) {
            dispatchedOrders.add(order)
            println("Despachando la orden de: ${order.customerName}: ${order.formatPupusas()}.")
        } else {
            println("No hay órdenes pendientes para despachar.")
        }
    }

    override fun viewDispatchedOrders() {
        displayOrders("Ordenes despachadas:", dispatchedOrders)
    }

    private fun displayOrders(title: String, orders: Collection<Order>) {
        if (orders.isEmpty()) {
            println("No hay $title.")
            return
        }
        
        println(title)
        orders.forEachIndexed { index, order ->
            println("${index + 1}. ${order.customerName}: ${order.formatPupusas()}")
        }
    }
}

fun Order.formatPupusas(): String {
    return pupusas.joinToString(", ") { "${it.quantity} pupusas de ${it.type}" }
}

fun main() {
    val system: OrderSystem = PupuseriaSystem()
    
    while (true) {
        println("\nBienvenido a la Pupuseria \"El Comalito\"")
        println("Seleccione una opcion:")
        println("1. Registrar una nueva orden")
        println("2. Ver ordenes pendientes")
        println("3. Despachar orden")
        println("4. Ver ordenes despachadas")
        println("5. Salir")
        
        when (readLine()?.toIntOrNull()) {
            1 -> system.registerNewOrder()
            2 -> system.viewPendingOrders()
            3 -> system.dispatchOrder()
            4 -> system.viewDispatchedOrders()
            5 -> {
                println("Hasta luego")
                return
            }
            else -> println("Opción no válida. Por favor, intente de nuevo.")
        }
    }
}
