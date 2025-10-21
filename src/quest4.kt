/*
4) Implemente uma função que recebe uma lista com repetições e retorna uma lista com codificação run-length.
> encode("aaaabccaadeeee".toList())
[(4, a), (1, b), (2, c), (2, a), (1, d), (4, e)]
*/

fun encode(lista: List<Char>): List<Pair<Int, Char>> {
    if (lista.isEmpty()) return emptyList()

    val resultado = mutableListOf<Pair<Int, Char>>()
    var contador = 1

    for (i in 1 until lista.size) {
        if (lista[i] == lista[i - 1]) {
            contador++
        } else {
            resultado.add(Pair(contador, lista[i - 1]))
            contador = 1
        }
    }

    // adiciona o último grupo
    resultado.add(Pair(contador, lista.last()))

    return resultado
}

fun main() {
    val resultado = encode("aaaabccaadeeee".toList())
    println(resultado) // [(4, a), (1, b), (2, c), (2, a), (1, d), (4, e)]
}