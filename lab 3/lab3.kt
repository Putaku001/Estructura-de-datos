// Función principal que ejecuta un menú interactivo para seleccionar diferentes opciones
fun main() {
    while (true) {
        // Mostrar opciones al usuario
        println("\nSeleccione una opcion:")
        println("1. Ordenar una lista usando Bubble Sort")
        println("2. Ordenar una lista usando Quick Sort") 
        println("3. Calcular el factorial de un numero")
        println("4. Resolver las Torres de Hanoi")
        println("5. Salir")
        print("Opcion: ")

        // Leer la opción seleccionada por el usuario
        when (readLine()?.toIntOrNull()) {
            1 -> bubbleSortOption() // Llamar a la opción de Bubble Sort
            2 -> quickSortOption()  // Llamar a la opción de Quick Sort
            3 -> factorialOption()  // Llamar a la opción de Factorial
            4 -> hanoiOption()      // Llamar a la opción de Torres de Hanoi
            5 -> {
                println("Programa finalizado") // Mensaje de salida
                return // Terminar el programa
            }
            else -> println("Opción invalida") // Mensaje de error para opción inválida
        }
    }
}

// Función para manejar la opción de ordenamiento Bubble Sort
fun bubbleSortOption() {
    print("Ingrese una lista de numeros separados por comas: ")
    val input = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() }

    if (input == null || input.isEmpty()) {
        println("Entrada inválida")
        return
    }

    val array = input.toIntArray()
    println("Lista original: ${array.toList()}")

    // Medir el tiempo de ejecución del algoritmo
    val startTime = System.nanoTime()
    bubbleSort(array)
    val endTime = System.nanoTime()

    // Mostrar resultados
    println("Lista ordenada usando Bubble Sort: ${array.toList()}")
    println("Tiempo de ejecucion: %.9f segundos".format((endTime - startTime) / 1_000_000_000.0)) //tuvo que ser con 9f ya que con 3 solo daba 0.000 segundos
}

// Función que implementa el algoritmo Bubble Sort
fun bubbleSort(arr: IntArray) {
    val n = arr.size
    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

// Función para manejar la opción de ordenamiento Quick Sort
fun quickSortOption() {
    print("Ingrese una lista de numeros separados por comas: ")
    val input = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() }
    if (input == null || input.isEmpty()) {
        println("Entrada inválida")
        return
    }

    val array = input.toIntArray()
    println("Lista original: ${array.toList()}")

    // Medir el tiempo de ejecución
    val startTime = System.nanoTime()
    quickSort(array, 0, array.size - 1)
    val endTime = System.nanoTime()

    // Mostrar resultados
    println("Lista ordenada usando Quick Sort: ${array.toList()}")
    println("Tiempo de ejecucion: %.9f segundos".format((endTime - startTime) / 1_000_000_000.0))
}

// Función que implementa el algoritmo Quick Sort
fun quickSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pi = partition(arr, low, high)
        // Ordenar recursivamente las sublistas
        quickSort(arr, low, pi - 1)
        quickSort(arr, pi + 1, high)
    }
}

// Función para particionar el array para Quick Sort
fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high]
    var i = low - 1

    for (j in low until high) {
        if (arr[j] <= pivot) {
            i++
            // Intercambiar elementos
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    val temp = arr[i + 1]
    arr[i + 1] = arr[high]
    arr[high] = temp

    return i + 1
}

// Función para manejar la opción de cálculo de factorial
fun factorialOption() {
    print("Ingrese un numero: ")
    val num = readLine()?.toIntOrNull()

    if (num == null || num < 0) {
        println("Por favor ingrese un número entero positivo")
        return
    }

    // Calcular y mostrar el factorial
    println("El factorial de $num es: ${factorial(num)}")
}

// Función recursiva para calcular el factorial
fun factorial(n: Int): Long {
    return if (n <= 1) 1 else n * factorial(n - 1)
}

// Función para manejar la opción de resolver las Torres de Hanoi
fun hanoiOption() {
    print("Ingrese el numero de discos: ")
    val discos = readLine()?.toIntOrNull()

    if (discos == null || discos <= 0) {
        println("Por favor ingrese un número válido de discos")
        return
    }

    var paso = 1
    // Resolver el problema de las Torres de Hanoi
    hanoi(discos, 'A', 'C', 'B') { origen, destino ->
        println("Paso ${paso++}: Mover disco de Torre $origen a Torre $destino")
    }
}

// Función recursiva para resolver las Torres de Hanoi
fun hanoi(n: Int, origen: Char, destino: Char, auxiliar: Char, mover: (Char, Char) -> Unit) {
    if (n == 1) {
        mover(origen, destino)
        return
    }

    // Mover n-1 discos de origen a auxiliar
    hanoi(n - 1, origen, auxiliar, destino, mover)
    // Mover el disco restante de origen a destino
    mover(origen, destino)
    // Mover n-1 discos de auxiliar a destino
    hanoi(n - 1, auxiliar, destino, origen, mover)
}
