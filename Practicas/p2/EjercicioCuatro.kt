fun main() {
    val numeros = IntArray(4)
    
    println("Por favor, ingresa 4 números enteros.")
    
    for (i in numeros.indices) {
        var esValido = false
        while (!esValido) {
            try {
                print("Ingresa el número ${i + 1}: ")
                numeros[i] = readLine()!!.toInt()
                esValido = true
            } catch (e: NumberFormatException) {
                println("Entrada no válida. Por favor, ingresa un número entero.")
            }
        }
    }
    
    val numeroMayor = numeros.maxOrNull() ?: 0
    println("El número mayor es: $numeroMayor")
}
