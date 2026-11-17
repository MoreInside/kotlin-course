package lessons.lesson20.homework

import kotlin.math.absoluteValue

fun main() {
    //1.
    val numbers = arrayOf(10, 20, 30)
    println(numbers.getAround()) // (10, 30)

    val emptyArray = emptyArray<Int>()
    println(emptyArray.getAround()) // (null, null)

    val numbere = mutableListOf(5, 2, 8, 1)

    println(numbere.sortByDirection(true))
    println(numbere.sortByDirection(false))
    //3.
    val data = mapOf(
        1 to listOf("Anna", "Maria"),
        2 to listOf("Ivan", "Petr")
    )
    println(data.extract(1))

    println(data.extract(5))

    val empty: Map<Int, List<String>>? = null
    println(empty.extract(1))
    //4.
    println(10.within(12, 2))
    println(10.within(15, 2))
    println(10.5.within(10.0, 0.5))
    println(20L.within(18, 1))
    //5.
    val text = "CAT"
    val encrypted = text.encrypt(2)
    println(encrypted)
    val decrypted = encrypted.decrypt(2)
    println(decrypted)

    //6.
    "суп".twitterable(listOf("Анна", "Иван", "Олег"))
}

//1. Определите функцию-расширение для массива чисел, которая не принимает аргументов и возвращает пару из чисел - первое и последнее.
//Если массив был пуст то вернуть пару из null значений.
fun Array<Int>.getAround(): Pair<Int?, Int?> {
    return Pair(this.firstOrNull(), this.lastOrNull())
}
//2. Создайте функцию-расширение для изменяемого списка элементов:
//a) с дженериком T ограниченным интерфейсом Comparable<T>
//b) которая принимает булево значение
//c) возвращает этот же список только в виде неизменяемого
//d) сам изменяемый список при этом должен стать отсортированным по возрастанию,
//если в функцию передано true и по убыванию, если false (используем функции sort() и sortDescending()

fun <T : Comparable<T>> MutableList<T>.sortByDirection(direction: Boolean): List<T> {
    if (direction) {
        this.sort()
    } else {
        this.sortDescending()
    }
    return this
}

//3. Создайте функцию-расширение для nullable словаря с дженериком:
//a) Ключ является дженериком
//b) Значение является списком из дженериков
//c) Принимает целое число
//d) Возвращает nullable словарь из ключей изначального словаря приведённых к строке через toString()
//f) Значений из nullable дженерика, взятых из изначального ключа-списка по индексу из аргумента,
// если такого индекса нет - значением будет null
fun <K, V> Map<K, List<V>>?.extract(index: Int): Map<String, V?>? {
    if (this != null) {
        val result = mutableMapOf<String, V?>()
        for ((key, value) in this) {
            result.put(key.toString(), value.getOrNull(index))
        }
        return result
    }
    return null
}

//4. Реализуйте метод расширения within для класса Number, который проверяет,
// что текущее число отклоняется от эталонного не более допустимого значения.
//Метод принимает два параметра: other — число для сравнения и deviation — максимально допустимое отклонение.
//Метод должен возвращать true, если разница между текущим числом и числом для сравнения не превышает заданное отклонение.
//Протестируйте функцию на разных типах чисел.
//Для получения отклонения, у разницы чисел нужно вызвать свойство absoluteValue.
fun Number.within(other: Number, deviation: Number): Boolean {
    return (this.toDouble() - other.toDouble()).absoluteValue <= deviation.toDouble()
}


//5. Реализуйте для класса String два метода расширения: Number и decrypt.
//Метод encrypt должен сдвигать каждый символ исходной строки на заданное число позиций вперед по таблице Unicode,
//а метод decrypt — на то же число позиций назад.
//Оба метода принимают один параметр base типа Int, который определяет величину сдвига.
//Протестируйте вашу реализацию, убедившись, что после шифрования и последующей расшифровки строка
//возвращается к исходному состоянию.
//Сдвиг по таблице Unicode реализуется простым прибавлением или вычитанием размера сдвига к char символу.

fun String.encrypt(base: Int): String {
    return this.map { char ->
        (char.code + base).toChar()
    }.joinToString("")
}

fun String.decrypt(base: Int): String {
    return this.map { char ->
        (char.code - base).toChar()
    }.joinToString("")
}


//6. Игра в Твиттере - собирание разных слов из букв через ответы.
//Напиши метод расширения строки, который будет принимать список имён пользователей
//и выводить в консоли исходную строку побуквенно в верхнем регистре в столбик: имя автора и букву под ним.
fun String.twitterable(authors: List<String>) {
    if (authors.isEmpty()) return

    for (index in this.indices) {
        println(authors[index % authors.size])
        println(this[index].uppercase())
        println()
    }
}