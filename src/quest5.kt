/*
5) Implemente uma função que decodifica run-length.
> decode(List((4, a), (1, b), (2, c), (2, a), (1, d), (4, e)))
[a, a, a, a, b, c, c, a, a, d, e, e, e, e]
*/

fun decode(lista: List<Pair<Int, Char>>): List<Char> {
    val resultado = mutableListOf<Char>()
    for ((count, char) in lista) {
        repeat(count) {
            resultado.add(char)
        }
    }
    return resultado
}

fun main() {
    val entrada = listOf(
        Pair(4, 'a'),
        Pair(1, 'b'),
        Pair(2, 'c'),
        Pair(2, 'a'),
        Pair(1, 'd'),
        Pair(4, 'e')
    )

    println(decode(entrada))
    // Saída: [a, a, a, a, b, c, c, a, a, d, e, e, e, e]
}