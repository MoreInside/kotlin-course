package lessons.lesson22.homework

//Задание 11. Создай свой тип исключения в отдельном файле, наследуемый от AssertionError и принимающий текст.
//Выброси это исключение в main
class AssertionExceptions(message: String) : AssertionError(message) //class ИмяКласса(параметр: Тип) : РодительскийКласс(параметр)

fun main() {
    throw AssertionExceptions("Упс, что-то пошло не так")
}

