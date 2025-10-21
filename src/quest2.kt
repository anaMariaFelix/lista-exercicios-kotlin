/*
2) Implemente uma função que retorna o penúltimo elemento de uma lista.
> last(listOf(1, 1, 2, 3, 5, 8))
5
 */

fun penultimo(lista: List<Int>) = lista[lista.size - 2]

fun main() {
    println(penultimo(listOf(1, 1, 2, 3, 5, 8))) // Saída: 5
}