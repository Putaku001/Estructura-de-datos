fun main() {
    println("Ingrese el primer numero: ")
    val num1 = readLine()?.toDoubleOrNull()

    println("Ingrese el segundo numero: ")
    val num2 = readLine()?.toDoubleOrNull()

    println("Ingrese la operacion (+, -, *, /): ")
    val operation = readLine()

    if (num1 == null || num2 == null || operation.isNullOrEmpty()) {
        println("Error: Ingrese numeros validos y un operador valido")
        return
    }

    calculate(num1, num2, operation)
}

fun calculate(num1: Double, num2: Double, operation: String) {
    val resultado = when (operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> {
            if (num2 != 0.0) num1 / num2 else {
                println("Error: División por cero")
                return
            }
        }
        else -> {
            println("Error: Operación no valida")
            return
        }
    }
    println("Resultado de la operación $num1 $operation $num2 = $resultado")
}
