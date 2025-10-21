/*
7) Implemente uma função que retorna o MDC (Maior Divisor Comum) de dois inteiros
positivos. Dica: utilize o algoritmo de Euclides
(https://en.wikipedia.org/wiki/Euclidean_algorithm).
> gcd(36, 63)
9
 */

fun gcd(a: Int, b: Int): Int {
    var x = a
    var y = b
    while (y != 0) {
        val resto = x % y
        x = y
        y = resto
    }
    return x
}

fun main() {
    println(gcd(36, 63)) // Saída: 9
}