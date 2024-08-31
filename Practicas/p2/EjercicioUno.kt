
fun main() {
    val numeros = Array(3) {0}
    var suma = 0
    
    for (i in numeros.indices) {
        while (true) {
            try {
                print("Ingresa un número entero (${i + 1}/3): ")
                numeros[i] = readLine()!!.toInt()
                break
            } catch (e: NumberFormatException) {
                println("Error: Debes ingresar un número entero válido.")
            }
        }
        suma += numeros[i]
    }
    
    println("La suma de los números ingresados es: $suma")
}