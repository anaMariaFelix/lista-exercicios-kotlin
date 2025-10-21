/*
1) Implemente uma função que retorna o último elemento de uma lista.
> last(listOf(1, 1, 2, 3, 5, 8))
8
 */

fun last(lista: List<Int>) = lista.last()

fun main() {
    println(last(listOf(1, 1, 2, 3, 5, 8))) // Saída: 8
}