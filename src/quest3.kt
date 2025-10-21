/*
3) Implemente uma função que se uma lista é palíndroma.
> isPalindrome(listOf(1, 2, 3, 2, 1))
true
 */

fun isPalindrome(lista: List<Int>): Boolean {
    return lista == lista.reversed()
}

fun main() {
    println(isPalindrome(listOf(1, 2, 3, 2, 1))) // Saída: true
    println(isPalindrome(listOf(1, 2, 3)))       // Saída: false
}