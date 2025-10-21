
fun Int.ehPrimo(): Boolean =
    this > 1 && (2..kotlin.math.sqrt(this.toDouble()).toInt()).all { this % it != 0 }

fun listarPrimos(intervalo: IntRange): List<Int> = intervalo.filter { it.ehPrimo() }

fun main() {
    println("Primos no intervalo: " + listarPrimos(7..31))
}
