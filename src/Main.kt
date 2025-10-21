// Questão 1: Retorna o último elemento de uma lista
fun <T> ultimo(lista: List<T>): T = lista.last()


// Questão 2: Retorna o penúltimo elemento de uma lista
fun <T> penultimo(lista: List<T>): T = lista[lista.size - 2]


// Questão 3: Verifica se a lista é um palíndromo
fun <T> ehPalindromo(lista: List<T>): Boolean = lista == lista.reversed()


// Questão 4: Codifica uma lista usando run-length encoding
fun <T> codificar(lista: List<T>): List<Pair<Int, T>> {
    if (lista.isEmpty()) return emptyList()
    val resultado = mutableListOf<Pair<Int, T>>()
    var contador = 1
    var anterior = lista.first()
    for (i in 1 until lista.size) {
        val atual = lista[i]
        if (atual == anterior) {
            contador++
        } else {
            resultado.add(contador to anterior)
            anterior = atual
            contador = 1
        }
    }
    resultado.add(contador to anterior)
    return resultado
}

// Questão 5: Decodifica uma lista codificada com run-length encoding
fun <T> decodificar(listaCodificada: List<Pair<Int, T>>): List<T> =
    listaCodificada.flatMap { (contador, valor) -> List(contador) { valor } }


// Questão 6: Verifica se um número é primo
fun Int.ehPrimo(): Boolean =
    this > 1 && (2..kotlin.math.sqrt(this.toDouble()).toInt()).all { this % it != 0 }


// Questão 7: Retorna o maior divisor comum usando o algoritmo de Euclides
fun mdc(a: Int, b: Int): Int {
    var x = a
    var y = b
    while (y != 0) {
        val resto = x % y
        x = y
        y = resto
    }
    return x
}

// Questão 8: Lista os números primos dentro de um intervalo
fun listarPrimos(intervalo: IntRange): List<Int> = intervalo.filter { it.ehPrimo() }


// Questão 9: Insere um valor na árvore binária de busca
sealed interface Arvore<out T>

data class No<out T>(
    val valor: T,
    val esquerda: Arvore<T> = Vazio,
    val direita: Arvore<T> = Vazio
) : Arvore<T> {
    override fun toString(): String {
        val filhos = if (esquerda is Vazio && direita is Vazio) "" else " $esquerda $direita"
        return "T($valor$filhos)"
    }
}

object Vazio : Arvore<Nothing> {
    override fun toString() = "."
}

//Questão 09: Função par aadicionar nós nas árvores
fun <T : Comparable<T>> Arvore<T>.inserir(valor: T): Arvore<T> = when (this) {
    is Vazio -> No(valor)
    is No -> if (valor < this.valor)
        No(this.valor, this.esquerda.inserir(valor), this.direita)
    else
        No(this.valor, this.esquerda, this.direita.inserir(valor))
}


// Questão 10: Conta a quantidade de folhas de uma árvore
fun <T> Arvore<T>.quantidadeFolhas(): Int = when (this) {
    is Vazio -> 0
    is No -> if (esquerda is Vazio && direita is Vazio) 1 else esquerda.quantidadeFolhas() + direita.quantidadeFolhas()
}


// Questão 11: Retorna os valores das folhas de uma árvore
fun <T> Arvore<T>.valoresFolhas(): List<T> = when (this) {
    is Vazio -> emptyList()
    is No -> if (esquerda is Vazio && direita is Vazio)
        listOf(valor)
    else
        esquerda.valoresFolhas() + direita.valoresFolhas()
}

// Questão 12: Converte a árvore para uma string customizada
fun <T> Arvore<T>.paraString(): String = when (this) {
    is Vazio -> "."
    is No -> {
        if (esquerda is Vazio && direita is Vazio)
            "$valor"
        else
            "$valor(${esquerda.paraString()},${direita.paraString()})"
    }
}

// Questão 13: Converte uma string customizada para uma árvore
fun String.paraArvore(): Arvore<String> {
    var indice = 0

    fun parse(): Arvore<String> {
        if (indice >= this.length) return Vazio

        val valor = this[indice++].toString()

        // Caso encontremos um ponto, sabemos que é um nó vazio
        if (valor == ".") return Vazio

        // Verifica se há um '(' para iniciar os filhos
        if (indice >= this.length || this[indice] != '(') {
            return No(valor)
        }

        indice++ // pula '('
        val esquerda = parse()

        if (indice < this.length && this[indice] == ',') {
            indice++ // pula ','
        }

        val direita = parse()

        if (indice < this.length && this[indice] == ')') {
            indice++ // pula ')'
        }

        return No(valor, esquerda, direita)
    }

    return parse()
}

fun main() {
    println("Questões 1 a 8")
    println("Último: " + ultimo(listOf(1, 1, 2, 3, 5, 8)))
    println("Penúltimo: " + penultimo(listOf(1, 1, 2, 3, 5, 8)))
    println("Palíndromo: " + ehPalindromo(listOf(1, 2, 3, 2, 1)))
    println("Codificar: " + codificar("aaaabccaadeeee".toList()))
    println("Decodificar: " + decodificar(listOf(4 to 'a', 1 to 'b', 2 to 'c', 2 to 'a', 1 to 'd', 4 to 'e')))
    println("É primo: " + 7.ehPrimo())
    println("MDC: " + mdc(36, 63))
    println("Primos no intervalo: " + listarPrimos(7..31))

    println("\nQuestões 9 a 13")
    val arvore1 = No("x", No("x"), Vazio)
    println("Quantidade de folhas: " + arvore1.quantidadeFolhas())

    val arvore2 = No("a", No("b"), No("c", No("d"), No("e")))
    println("Valores das folhas: " + arvore2.valoresFolhas())

    val arvore3 = No("a", No("b", No("d"), No("e")), No("c", Vazio, No("f", No("g"), Vazio)))
    val stringArvore = arvore3.paraString()
    println("String da árvore: $stringArvore")

    val arvoreReconstruida = stringArvore.paraArvore()
    println("Árvore reconstruída: $arvoreReconstruida")
    println("Árvores equivalentes? " + (arvore3 == arvoreReconstruida))
}