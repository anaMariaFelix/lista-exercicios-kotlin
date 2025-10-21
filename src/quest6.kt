/*
6) Implemente uma função que retorna se um número é primo.
> 7.isPrime()
true
 */

fun Int.isPrime(): Boolean {
    if (this < 2) return false
    for (i in 2..Math.sqrt(this.toDouble()).toInt()) {
        if (this % i == 0) return false
    }
    return true
}

fun main() {
    println(7.isPrime())  // true
    println(10.isPrime()) // false
}