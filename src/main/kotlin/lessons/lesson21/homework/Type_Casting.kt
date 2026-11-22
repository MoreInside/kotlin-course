package lessons.lesson21.homework

fun main() {
    //1.
    analyzeDataType(3.14)
    analyzeDataType(listOf("A", "B", "C"))
    analyzeDataType(true)

    safeCastToList(listOf(10, 20, 30))

    //4.
    4.toSquare()
    "4.2".toSquare()

    //5.
    val list: List<Any> = listOf(5, 2.5, "Привет", 10, true, 1.2)
    println(sumIntOrDoubleValues(list))

    //6.
    tryCastToListAndPrint(listOf("Привет", "Kotlin", "Мир"))
    println("-----")

    tryCastToListAndPrint(listOf("Привет", 25, true, 3.14, "Kotlin"))
    println("-----")

    tryCastToListAndPrint(123)
}

//====Задача 1====
//Напишите функцию analyzeDataType, принимающую параметр типа Any.
//Функция должна определить тип аргумента и вывести соответствующее сообщение:
//a) Для строки: "Это строка: [значение]".
//b) Для любого числа: "Это число: [значение]".
//c) Для списка: "Это список, количество элементов: [количество]".
//d) Для словаря: "Это словарь, количество пар: [количество]".
//e) Для остальных типов: "Неизвестный тип данных".
//Используйте оператор is для проверки типов.
fun analyzeDataType(data: Any) {
    val result = when (data) {
        is String -> println("Это строка: $data")
        is Number -> println("Это число: $data")
        is List<*> -> println("Это список, количество элементов: ${data.size}")
        is Map<*, *> -> println("Это словарь, количество пар: ${data.size}")
        else -> println("Неизвестный тип данных")
    }
    println(result)
}

//====Задача 2====
//Создайте функцию safeCastToList, принимающую параметр типа Any и возвращающую размер списка,
//если аргумент можно безопасно привести к типу List.
//В случае неудачного приведения функция должна возвращать -1. Используйте as? для безопасного приведения типа.
fun safeCastToList(arg: Any): Int {
    val list = arg as? List<*>
    if (list != null) {
        return list.size
    }
    return -1
}

//====Задача 3====
//Создайте функцию getStringLengthOrZero, которая принимает параметр типа Any?
// и возвращает длину строки, если аргумент можно привести к типу String.
// В случае, если аргумент равен null или не является строкой, функция должна возвращать 0.
fun getStringLengthOrZero(arg: Any?): Int {
    val lister = arg as? String
    if (lister != null) {
        return lister.length
    }
    return 0
}

//====Задача 4====
//Создайте функцию-расширение класса Any toSquare.
// Функция гарантированно вызывается у числа (в виде числа или строки, например 4 или 4.2 или “4.2”)
// и должна вернуть квадрат этого числа.
// Если придёт число, его нужно возвести в квадрат, если придёт строка,
// то его нужно преобразовать в число через функцию toDouble() и возвести в квадрат.
fun Any.toSquare(): Double {
    val number = when (this) {
        is Number -> this.toDouble()
        is String -> this.toDouble()
        else -> 0.0
    }
    return number * number
}

//====Задача 5====
//Напишите функцию sumIntOrDoubleValues, которая принимает список элементов типа Any
// и возвращает сумму всех целочисленных (Int) и вещественных (Double) значений в списке.
// Все остальные типы должны быть проигнорированы.
fun sumIntOrDoubleValues(list: List<Any>): Double {
    var sum = 0.0

    for (element in list) {
        when (element) {
            is Int -> sum += element
            is Double -> sum += element
        }
    }

    return sum
}

//====Задача 6====
//Создайте функцию tryCastToListAndPrint, которая принимает параметр типа Any и пытается привести его к типу List<*>.
//Если приведение успешно, функция должна напечатать все строки из списка,
//если элемент не является строкой то вывести предупреждение об этом.
//Если приведение неудачно, должно быть выведено сообщение об ошибке, не прерывая выполнение программы.
fun tryCastToListAndPrint(arg: Any) {
    val list = arg as? List<*>

    if (list != null) {
        for (element in list) {
            if (element is String) {
                println(element)
            } else {
                println("Элемент не является строкой")
            }
        }
    } else {
        println("Не удалось привести к списку")
    }
}