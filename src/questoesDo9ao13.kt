/*
questões 9, 10, 11, 12 e 13
*/

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

//Questão 09: Função par adicionar nós nas árvores
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