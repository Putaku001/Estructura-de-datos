interface IFizzBuzz {
    fun ejecutarFizzBuzz(rango: IntRange)
}

class FizzBuzz : IFizzBuzz {

    override fun ejecutarFizzBuzz(rango: IntRange) {
        if (rango.first <= 0 || rango.last <= 0) {
            println("El rango debe contener números positivos.")
            return
        }

        var contador = 0

        for (numero in rango) {
            when {
                esDivisiblePorAmbos(numero) -> print("FizzBuzz ")
                esDivisiblePorTres(numero) -> print("Fizz ")
                esDivisiblePorCinco(numero) -> print("Buzz ")
                else -> print("$numero ")
            }

            contador++
            if (contador % 10 == 0) {
                println()  // Salto de línea después de 10 elementos
            }
        }
    }

    private fun esDivisiblePorTres(numero: Int): Boolean {
        return numero % 3 == 0
    }

    private fun esDivisiblePorCinco(numero: Int): Boolean {
        return numero % 5 == 0
    }

    private fun esDivisiblePorAmbos(numero: Int): Boolean {
        return esDivisiblePorTres(numero) && esDivisiblePorCinco(numero)
    }
}

fun main() {
    val fizzBuzz = FizzBuzz()
    println("FizzBuzz del 1 al 100:")
    fizzBuzz.ejecutarFizzBuzz(1..100)
}
