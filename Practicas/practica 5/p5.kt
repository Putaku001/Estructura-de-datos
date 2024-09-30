class Node<T>(val value: T, var next: Node<T>? = null)

class Queue<T> {
    private var front: Node<T>? = null
    private var rear: Node<T>? = null
    private var size = 0

    fun enqueue(value: T) {
        val newNode = Node(value)
        if (isEmpty()) {
            front = newNode
            rear = newNode
        } else {
            rear?.next = newNode
            rear = newNode
        }
        size++
    }
        
    fun dequeue(): T? {
        if (isEmpty()) {
            println("Queue is empty.")
            return null
        }
        val dequeuedValue = front?.value
        front = front?.next
        if (front == null) {
            rear = null
        }
        size--
        return dequeuedValue
    }

    fun peek(): T? {
        return front?.value
    }

    fun isEmpty(): Boolean {
        return size == 0
    }
    
    fun size(): Int {
        return size
    }
    
    override fun toString(): String {
        if (isEmpty()) return "[]"
        val result = StringBuilder("[")
        var current = front
        while (current != null) {
            result.append(current.value)
            if (current.next != null) {
                result.append(", ")
            }
            current = current.next
        }
        result.append("]")
        return result.toString()
    }
}
fun main() {
    // Ejercicio 1
    // Simulación de sistema de turnos en un banco
    println("*** Ejercicio 1 ***")
    val bancoQueue = Queue<Int>()
    
    for (i in 1..10) {
        bancoQueue.enqueue(i)
    }
    
    println("Turnos de clientes inicial: $bancoQueue")
    
    for (i in 1..5) {
        println("Atendiendo al cliente: ${bancoQueue.dequeue()}")
    }
    
    println("Clientes restantes a atender: ${bancoQueue.size()}")
    
    // Ejercicio 2
    // Gestión de tareas en cola
    println("\n*** Ejercicio 2 ***")
    val tareasQueue = Queue<String>()
    
    tareasQueue.enqueue("Limpiar")
    tareasQueue.enqueue("Cocinar")
    tareasQueue.enqueue("Lavar")
    tareasQueue.enqueue("Planchar")
    tareasQueue.enqueue("Barrer")
    
    println("Tareas pendientes: $tareasQueue")
    
    while (!tareasQueue.isEmpty()) {
        println("Se está realizando la tarea de: ${tareasQueue.dequeue()}")
    }
}

