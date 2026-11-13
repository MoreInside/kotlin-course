package lessons.lesson19.homework

fun main() {
    println(getMiddleElement(listOf(10, 20, 30)))
    println(getMiddleElement(listOf("А", "Б", "В", "Г", "Д")))
    println(getMiddleElement(listOf(1, 2, 3, 4)))
    println(getMiddleElement(emptyList<Int>()))

    val holder = ListHolder<String>()

    holder.add("Привет")
    holder.add("Kotlin")
    holder.add("Дженерики")

    println(holder.getAll())

    val phrases = listOf("Привет как дела", "Изучаем Kotlin")
    println("$phrases")

    val map = mapOf(
        "Россия" to 1,
        "Германия" to 2
    )
    val result = transposition(map)
    println(result)

    val validator = OddValidator()

    println(validator.validate(4)) // true
    println(validator.validate(7)) // false
}



//1. Напиши функцию getMiddleElement с дженериком, которая будет принимать список и возвращать средний элемент,
//если он существует, иначе возвращать null.
//Например, у списка из четырёх элементов - среднего элемента нет.
fun <T> getMiddleElement(list: List<T>): T? {
    if (list.size % 2 == 0) {
        return null
    }
    return list[list.size / 2]
}

//2. Напиши класс ListHolder, который будет хранить список элементов типа T
//и иметь метод для добавления нового элемента и получения всех элементов в виде неизменяемого списка.
class ListHolder<T> {
    val items = mutableListOf<T>()
    fun add(item: T) {
        items.add(item)
    }

    fun getAll(): List<T> {
        return items.toList()
    }
}

//3. Создай интерфейс Mapper с двумя дженериками, который будет определять методы для преобразования:
//элементов одного типа в элементы другого типа
//списка элементов одного типа в список элементов другого типа
interface Mapper<T, R> {
    fun transform(arg: T): R
    fun transform(item: List<T>): List<R>
}

//4. Создай класс PhrasesToListOfStrings и имплементируй интефрейс Mapper с типами String и List<String>.
//Метод преобразования должен разбивать входящую строку по символу пробела и возвращать список из полученных слов.
//Метод преобразования списков с аналогичной механикой.
class PhrasesToListOfStrings : Mapper<String, List<String>> {

    override fun transform(phrase: String): List<String> {
        return phrase.split(" ")
    }

    override fun transform(phrases: List<String>): List<List<String>> {
        return phrases.map { phrase ->
            phrase.split(" ")
        }
    }
}

//5. Создай функцию transposition с двумя дженериками, которая принимает
//словарь с дженериками и возвращает словарь, в котором ключ и значения поменялись местами.
fun <K, V> transposition(map: Map<K, V>): Map<V, K> {
    return map.entries.associate { element ->
        element.value to element.key
    }
}

//6. Напиши интерфейс Validator с дженериком с функцией валидации,
//которая будет принимать элемент с типом дженерика и возвращать булево значение.
interface Validator<T> {
    fun validate(arg: T): Boolean
}

//7. Создай класс StringValidator и имплементируй интерфейс Validator с типом String?.
//Реализуй проверку, что строка не является null, не пустая и не состоит из одних пробелов.
class StringValidator : Validator<String?> {
    override fun validate(arg: String?): Boolean {
        return arg != null && arg.isNotBlank() // проверяет, что строка не пустая и не состоит только из пробелов.
    }
}

//8. Создай класс OddValidator и имплементируй интерфейс Validator типизированный по Int.
//Реализуй проверку, что число чётное.
class OddValidator : Validator<Int> {
    override fun validate(arg: Int): Boolean {
        return arg % 2 == 0
    }
}

//9. Создай класс ListValidator с дженериком, ограниченным типом Number, имплементируй интерфейс Validator типизированный
//по типу List с nullable типом дженерика класса
//Реализуй проверку:
//Ни один элемент списка не является null
//Ни один элемент приведённый к типу Double не равен 0.0

class ListValidator <T: Number>: Validator <List<T?>>{
    override fun validate(list: List<T?>): Boolean {
        return list.all { element ->
            element != null && element.toDouble() != 0.0
        }
    }
}