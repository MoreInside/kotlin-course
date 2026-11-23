package lessons.lesson22.homework

//Задание 12. Создай свой тип исключения в отдельном файле,
// наследуемый от RuntimeException и принимающий IndexOutOfBoundsException тип в качестве аргумента.
// Напиши код, который спровоцирует выброс IndexOutOfBoundsException,
// перехвати его с помощью try-catch и в блоке catch выведи сообщение в консоль
// и сделай выброс своего типа исключения.

class IndexExceptions(exception: IndexOutOfBoundsException) : RuntimeException(exception)

fun main() {
    try {
        val list = listOf(1, 2, 3)
        list[5] //вызываем несуществующий индекс
    } catch (e: IndexOutOfBoundsException) {
        println(e.message)
        throw IndexExceptions(e)
    }
}