package lessons.lesson07.homework

fun main() {
    //1. Используя вложенный цикл реализовать таблицу умножения, как на картинке.
    for (v in 1..10) {
        for (j in 1..10) {
            print("${v * j} ")
        }
        println()
    }
}

//2. Напишите функцию, которая суммирует числа от 1 до 'arg' с помощью цикла for. 'arg' - целочисленный аргумент функции.
fun sumNumbers(arg: Int): Int {
    var sum = 0
    for (s in 1..arg) {
        sum = sum + s
    }
    return sum
}

//3. Напишите функцию, которая вычисляет факториал числа 'arg' с использованием цикла while.
fun fact(arg: Int): Int {
    var counter = 1
    var result = 1
    while (counter <= arg) {
        result = result * counter
        counter++
    }
    return result
}

//4. Напишите функцию, которая находит сумму всех четных чисел от 2 до 'arg', используя цикл while.
fun debt(arg: Int): Int {
    var counter = 1
    var result = 0

    while (counter <= arg) {
        if (counter % 2 == 0) {
            result = result + counter
        }
        counter++
    }
    return result
}

//5. Напишите функцию, которая используя вложенные циклы while, выведет заполненный прямоугольник размером 5x3 из символов *.
fun triangle() {
    var i = 0
    while (i < 3) {
        var j = 0
        while (j < 5) {
            print("*")
            j++
        }
        println()
        i++
    }
}

//6. Напишите функцию, которая используя цикл for найдёт суммы чётных и нечётных значений чисел от 1 до arg.
fun summa(arg: Int): Pair<Int, Int> {
    var chot = 0
    var nechot = 0
    for (i in 1..arg) {
        if (i % 2 == 0) {
            chot += i
        } else {
            nechot += i
        }
    }

    return Pair(chot, nechot)
}
