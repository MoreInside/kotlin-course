package lessons.lesson16.homework

import kotlin.math.PI
import kotlin.math.sin

fun main() {
    val animals: List<Animal> = listOf(
        Dog(),
        Cat(),
        Bird()
    )
    for (animal in animals) {
        animal.makeSound()
    }

    val list = listOf(
        Circle(5.7), Square(9.4), Triangle(3.2, 6.3, 8.1)
    )

    for (item in list) {
        println(item.area())
    }
    val text = "Kotlin is a modern programming language. " +
            "It supports object oriented programming and polymorphism. " +
            "Different printers can process the same text in different ways."

    val laserPrinter = LaserPrinter()
    val inkjetPrinter = InkjetPrinter()

    laserPrinter.printPage(text)
    inkjetPrinter.printPage(text)

    val shoppingCart = ShoppingCart()
    shoppingCart.addToCart(678)                   // Один ID
    shoppingCart.addToCart(101, 101)              // ID и количество
    shoppingCart.addToCart(mapOf(202 to 3, 303 to 2)) // Словарь
    shoppingCart.addToCart(listOf(404, 404, 505))
    println(shoppingCart)

    val logger = Logger()
    logger.log("Приложение запущено") // Обычное сообщение INFO
    logger.log("WARNING", "Заканчивается память") //Сообщение с уровнем WARNING
    logger.log("ERROR", "Ошибка авторизации") // Сообщение с уровнем ERROR
    logger.log("DEBUG", "Проверяем соединение") // Сообщение с уровнем DEBUG
    logger.log(
        listOf(
            "Пользователь вошёл", // Список сообщений - все с INFO
            "Загружен профиль",
            "Получен список полисов"
        )
    )
    logger.log(Exception("Нет соединения с сервером")) //Исключение - ERROR
}


//=========Переопределение методов===========
//---------1. Животные и Их Звуки---------
//1. Базовый класс: Animal с методом makeSound().
//2. Классы наследники: Dog, Cat, Bird.
//3. Базовый метод makeSound() в классе Animal выводит "This animal makes no sound."
//4. Переопределите метод makeSound() в каждом классе-наследнике, чтобы Dog выводил "Bark", Cat - "Meow", Bird - "Tweet".
// Обогати вывод звуков цветом.
//5. Создайте список животных List<Animal> и вызовите в цикле метод makeSound()
object Colors {
    const val RESET = "\u001B[0m"
    const val RED = "\u001B[31m"
    const val GREEN = "\u001B[32m"
    const val YELLOW = "\u001B[33m"
}

abstract class Animal {
    open fun makeSound() {
        println("This animal makes no sound.")
    }
}

val dog = Dog()

class Dog : Animal() {
    override fun makeSound() {
        println("${Colors.RED}Bark${Colors.RESET}")
    }
}

class Cat : Animal() {
    override fun makeSound() {
        println("${Colors.GREEN}Meow${Colors.RESET}")
    }
}

class Bird : Animal() {
    override fun makeSound() {
        println("${Colors.YELLOW}Tweet${Colors.RESET}")
    }
}

//---------2. Геометрические Фигуры и Их Площадь---------
//1. Абстрактный (базовый) класс: Shape с open методом area().
//2. Метод area() возвращает 0.0.
//3. Классы наследники: Circle, Square, Triangle.
//4. Переопределите метод area() в каждом классе-наследнике для расчета площади соответствующей фигуры.
//5. Подсказка: каждый класс должен иметь аргументы конструктора, позволяющие вычислить площадь
//   (для круга это радиус, для квадрата это сторона, для треугольника это две стороны и угол между ними)
//6. Создай набор геометрических фигур и выведи в цикле их площадь
abstract class Shape {
    open fun area(): Double {
        return 0.0
    }
}

class Circle(
    val radius: Double
) : Shape() {
    override fun area(): Double {
        return PI * radius * radius
    }
}

class Square(
    val side: Double
) : Shape() {
    override fun area(): Double {
        return side * side
    }
}

class Triangle(
    val sideA: Double,
    val sideB: Double,
    val corner: Double
) : Shape() {
    override fun area(): Double {
        return sideA * sideB * sin(corner) / 2
    }
}
//---------3. Принтер---------
//1. Создай абстрактный принтер, который имеет абстрактный метод печати, принимающий в качестве аргумента строку.
//2. Создай классы наследники: лазерный и струйный
//3. Лазерный принтер должен разбивать фразу на слова по пробелу и выводить каждое слово отдельно чёрными буквами на белом фоне (хорошо работает на тёмной теме)
//4. Струйный принтер должен разбивать фразу на слова по пробелу и выводить каждое слово отдельно цветными буквами на цветном фоне.
// Слова должны быть разноцветны.
// Можно сделать список пар с цветом текста и фона и проходить циклически по этому списку при выводе текста.
// Проверить работу на длинном тексте

object ColorsPrinter {
    const val WHITE_ON_BLACK = "\u001B[37;40m"
    const val RESET = "\u001B[0m"
    const val RED_ON_WHITE = "\u001B[31;47m"
    const val GREEN_ON_BLACK = "\u001B[32;40m"
    const val YELLOW_ON_BLUE = "\u001B[33;44m"
    const val BLUE_ON_WHITE = "\u001B[34;47m"
    const val PURPLE_ON_BLACK = "\u001B[35;40m"
    const val CYAN_ON_BLUE = "\u001B[36;44m"
}

abstract class Printer {
    abstract fun printPage(abstractPrint: String)
}

class LaserPrinter() : Printer() {
    override fun printPage(abstractPrint: String) {
        val words = abstractPrint.split(" ")
        for (item in words) {
            print("${ColorsPrinter.WHITE_ON_BLACK}$item ${ColorsPrinter.RESET}")
        }
        println()
    }
}

class InkjetPrinter() : Printer() {
    val colors = listOf(
        ColorsPrinter.RED_ON_WHITE,
        ColorsPrinter.GREEN_ON_BLACK,
        ColorsPrinter.YELLOW_ON_BLUE,
        ColorsPrinter.BLUE_ON_WHITE,
        ColorsPrinter.PURPLE_ON_BLACK,
        ColorsPrinter.CYAN_ON_BLUE
    )

    override fun printPage(abstractPrint: String) {
        val words = abstractPrint.split(" ")
        for ((index, item) in words.withIndex()) {
            val color = colors[index % colors.size]
            print("${color}$item ${ColorsPrinter.RESET}")
        }
        println()
    }
}
//=========Перегрузка методов=========

//4. Класс “Корзина товаров”: содержит поле словаря из ID товаров и их количества.
//   1. Базовый метод addToCart(itemId) добавляет одну единицу товара к уже существующим в корзине.
//   2. Выполнить перегрузку addToCart который:
//.     а) Принимает два аргумента (itemId и количество amount)
//.     b) Принимает словарь из id и количества и добавляет всё в корзину
//.     c) Принимает список из id (добавляет по одной единице).
//.  3. Учесть, что если товар уже есть в корзине, нужно увеличить его количество, если нет - добавить.
//.  4. Попробуй создать корзину и положить в неё товар разными способами, потом распечатать корзину в консоль.
//.  5. Переопредели у корзины метод toString для красивого форматирования содержимого таблицы,
// включая итоговое количество артикулов и общее количество всего товара в корзине.
class ShoppingCart {
    private val items = mutableMapOf<Int, Int>()


    fun addToCart(itemId: Int) {
        val amount = items[itemId]

        if (amount == null) {
            items[itemId] = 1
        } else {
            items[itemId] = amount + 1
        }
    }

    fun addToCart(itemId: Int, amount: Int) {
        for (i in 1..amount) {
            addToCart(itemId)
        }
    }

    fun addToCart(items: Map<Int, Int>) {
        for (product in items) {
            addToCart(product.key, product.value)
        }
    }

    fun addToCart(items: List<Int>) {
        for (product in items) {
            addToCart(product)
        }
    }

    override fun toString(): String {
        var cartText = "Корзина товаров: \n"
        var cartArticle = "Итоговое количество артикулов: ${items.size}\n"
        var cartSum = "Общее количество всего товара в корзине: ${items.values.sum()}"

        for (product in items) {
            cartText += "${product.key} | ${product.value}\n"
        }
        return cartText + cartArticle + cartSum
    }
}

//5. Класс “Логгер”: представляет из себя инструмент для вывода информации в консоль с разным префиксами уровня логирования
// (INFO, WARNING, ERROR, DEBUG)
//   1. Базовый метод log(message) выводит в консоль сообщение message с префиксом INFO
//   2. Выполнить перегрузку метода log, который:
//      a) Принимает уровень логирования и сообщение. Выводит сообщения типа WARNING жёлтым цветом, а ERROR белым цветом на красном фоне.
//      b) Принимает список сообщений и все их выводит с уровнем INFO
//      c) Принимает объект типа Exception и выводит его поле message с префиксом уровня ERROR
//   3. Попробуй создать логгер и добавить в него сообщения разными способами. Проверь вывод в консоли.
object Prefix {
    const val RESET = "\u001B[0m"
    const val YELLOW = "\u001B[33m"
    const val WHITE_ON_RED = "\u001B[37;41m"
}

class Logger {
    fun log(message: String) {
        println("[INFO] $message")
    }

    fun log(level: String, message: String) {
        when (level) {
            "WARNING" -> {
                println("${Prefix.YELLOW}[WARNING] $message${Prefix.RESET}")
            }

            "ERROR" -> {
                println("${Prefix.WHITE_ON_RED}[ERROR] $message${Prefix.RESET}")
            }

            else -> {
                println("[$level] $message")
            }
        }
    }

    fun log(messages: List<String>) {
        messages.forEach { log(it) }
    }

    fun log(error: Exception) {
        log("ERROR", error.message ?: "Неизвестная ошибка")
    }
}