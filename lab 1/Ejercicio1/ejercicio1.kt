interface INumero {
    fun esPrimo(): Boolean
    fun esPar(): Boolean
    fun esImpar(): Boolean
}

open class Numero(val valor: Int) : INumero {

    override fun esPrimo(): Boolean {
        if (valor < 2) return false
        for (i in 2..Math.sqrt(valor.toDouble()).toInt()) {
            if (valor % i == 0) return false
        }
        return true
    }

    override fun esPar(): Boolean {
        return valor % 2 == 0
    }

    override fun esImpar(): Boolean {
        return valor % 2 != 0
    }
}

class NumeroPrimo(valor: Int) : Numero(valor)
class NumeroPar(valor: Int) : Numero(valor)
class NumeroImpar(valor: Int) : Numero(valor)

class EvaluadorNumeros {

    fun evaluarNumeros(N: Int) {
        if (N <= 0) {
            println("Entrada inválida. Numero N debe ser un número entero positivo.")
            return
        }

        var cantidadPrimos = 0
        var cantidadPares = 0
        var cantidadImpares = 0

        for (i in 1..N) {
            val numero = Numero(i)

            if (numero.esPrimo()) cantidadPrimos++
            if (numero.esPar()) cantidadPares++
            if (numero.esImpar()) cantidadImpares++
        }

        println("Cantidad de números primos: $cantidadPrimos")
        println("Cantidad de números pares: $cantidadPares")
        println("Cantidad de números impares: $cantidadImpares")
    }
}

fun main() {
    val evaluador = EvaluadorNumeros()
    print("Ingresa un número N: ")
    val N = readLine()?.toIntOrNull() ?: 0
    evaluador.evaluarNumeros(N)
}
