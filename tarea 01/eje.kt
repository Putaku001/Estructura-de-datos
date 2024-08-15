interface IBaseNumber {
    val value: Int
    fun printValue()
}

class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun printValue() {
        println("Número primo: $value")
    }
}

class OddNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = findDivisors(value)

    override fun printValue() {
        println("Número impar: $value, Divisores: $divisors")
    }

    private fun findDivisors(n: Int): List<Int> {
        return (1..n).filter { n % it == 0 }
    }
}

class EvenNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = findDivisors(value)

    override fun printValue() {
        println("Número par: $value, Divisores: $divisors")
    }

    private fun findDivisors(n: Int): List<Int> {
        return (1..n).filter { n % it == 0 }
    }
}

enum class NumberType {
    PRIME, EVEN, ODD
}

class EvaluationResult(
    val primes: List<PrimeNumber>,
    val evens: List<EvenNumber>,
    val odds: List<OddNumber>
) {
    fun printResults() {
        println("Primos:")
        primes.forEach { it.printValue() }

        println("Pares:")
        evens.forEach { it.printValue() }

        println("Impares:")
        odds.forEach { it.printValue() }
    }
}

class PrimeNumberProcessor(val range: IntRange) {

    fun process(): EvaluationResult {
        val primes = mutableListOf<PrimeNumber>()
        val evens = mutableListOf<EvenNumber>()
        val odds = mutableListOf<OddNumber>()

        for (number in range) {
            when (validateNumber(number)) {
                NumberType.PRIME -> primes.add(PrimeNumber(number))
                NumberType.EVEN -> evens.add(EvenNumber(number))
                NumberType.ODD -> odds.add(OddNumber(number))
            }
        }

        return EvaluationResult(primes, evens, odds)
    }

    private fun validateNumber(n: Int): NumberType {
        if (isPrime(n)) return NumberType.PRIME
        return if (n % 2 == 0) NumberType.EVEN else NumberType.ODD
    }

    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }
}

fun main() {
    val processor = PrimeNumberProcessor(1..20)
    val result = processor.process()
    result.printResults()
}
