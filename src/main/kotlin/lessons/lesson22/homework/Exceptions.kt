package lessons.lesson22.homework

fun main() {
    // handleExceptions(null)
    // handleExceptions("text")
    // handleExceptions(listOf(1, 2))
    // handleExceptions(listOf(1, 2, 3))
    // handleExceptions(listOf(1, 2, "text"))
    handleExceptions(listOf(1, 2, "4"))
    //handleExceptions(listOf(1, 2, "3"))

//Задание 1. Воспроизвести фрагменты кода, которые могут привести к возникновению исключений:
//Задание 9. Оберни все вызовы из предыдущего задания в блок try-catch
// и создай для каждого типа ошибки свой catch блок

    //NullPointerException - пытаемся обратиться к свойству или методу объекта, значение которого равно null
    try {
        null!! //принудительно требуем, чтобы значение было не null, но его и так нету
    } catch (e: NullPointerException) {
        println("It is NullPointerException❗")
    }

    //IndexOutOfBoundsException - когда пытаемся обратиться к элементу коллекции, списка, массива или строки по несуществующему индексу
    try {
        val list = listOf(1, 2, 3)
        list[5] //вызываем несуществующий индекс
    } catch (e: IndexOutOfBoundsException) {
        println("It is IndexOutOfBoundsException❗")
    }

//ClassCastException - пытаемся привести объект к типу, экземпляром которого он не является
    try {
        val rawInput: Any = "user-1234" //строка
        val wrongCast = rawInput as Int //приводим её к Int
    } catch (e: ClassCastException) {
        println("It is ClassCastException❗")
    }

//IllegalArgumentException - некорректный аргумент
    try {
        require(false) //проверяет условие, которое заведомо ложно
    } catch (e: IllegalArgumentException) {
        println("It is IllegalArgumentException❗")
    }

//NumberFormatException - когда строка не подходит под формат числового типа при попытке преобразовать её в число
    try {
        println("oops".toInt())
    } catch (e: NumberFormatException) {
        println("It is NumberFormatException❗")
    }

//IllegalStateException - исключение, которое возникает, когда метод или операция вызываются в неподходящий момент,
//когда объект или программа находятся в некорректном состоянии
    try {
        check(false)    //Проверяет корректность состояния объекта или переменной
    } catch (e: IllegalStateException) {
        println("It is IllegalStateException❗")
    }

//OutOfMemoryError - переполнение памяти
    try {
        //throw OutOfMemoryError("")
        val hugeArray = IntArray(Int.MAX_VALUE)
    } catch (e: OutOfMemoryError) {
        println("It is OutOfMemoryError❗")
    }

//StackOverflowError - запустить бесконечную рекурсию без условия выхода, чтобы переполнить стек вызовов потока
    try {
        //throw StackOverflowError()
        crash()
    } catch (e: StackOverflowError) {
        println("It is StackOverflowError❗")
    }
}

fun crash() {
    crash()
}


//Задание 10. Напиши функцию, которая принимает nullable аргумент типа Any
// и в блоке try/catch выполни набор действий с этим аргументом которые могут потенциально вызвать
// одно из исключений в этом же порядке:
//NullPointerException
//IndexOutOfBoundsException
//ClassCastException
//IllegalArgumentException
//NumberFormatException
//IllegalStateException

//В блоке catch перехватывай исключение с общим типом Throwable,
//а внутри через when определи тип исключения и выведи сообщение с названием исключения.
//Сразу после when выброси перехваченное исключение через throw, чтобы прекратить работу программы.
//Если аргумент функции не вызвал исключение, то выведи текст, сообщающий что переданный аргумент фантастически хорош!
fun handleExceptions(elem: Any?) {
    try {
        elem!!
        val list = elem as List<*>
        list[2]
        require(list[2] is String)
        check((list[2] as String).toInt() == 3)

    } catch (e: Throwable) {
        val result = when (e) {
            is NullPointerException -> "It is NullPointerException"
            is IndexOutOfBoundsException -> "It is IndexOutOfBoundsException"
            is ClassCastException -> "It is ClassCastException"
            is NumberFormatException -> "It is NumberFormatException"
            is IllegalArgumentException -> "It is IllegalArgumentException"
            is IllegalStateException -> "It is IllegalStateException"
            else -> "It is unknown exception"
        }

        println(result)
        throw e
    }

    println("The passed argument is brilliant!")
}
