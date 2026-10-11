package lessons.lesson10.homework


fun main() {

//===========Задачи на работу со словарём=============
//Создайте пустой неизменяемый словарь, где ключи и значения - целые числа.
    val emptyMap = mapOf<Int, Int>()

//Создайте словарь, инициализированный несколькими парами "ключ-значение", где ключи - float, а значения - double
    val emptyMap1 = mapOf<Float, Double>(2.4f to 5.5, 3.14f to 3.14)

//Создайте изменяемый словарь, где ключи - целые числа, а значения - строки.
    val emptyMap2 = mutableMapOf<Int, String>()

//Имея изменяемый словарь, добавьте в него новые пары "ключ-значение"
    val emptyMap3 = mutableMapOf<Int, String>()
    emptyMap3[1] = "Анна"
    emptyMap3[2] = "Мария"

//Используя словарь из предыдущего задания, извлеките значение, используя ключ.
// Попробуй получить значение с ключом, которого в словаре нет.
    println(emptyMap3[1])
    println(emptyMap3[2])
    println(emptyMap3[3])

//Удалите определенный элемент из изменяемого словаря по его ключу.
    val emptyMap4 = mutableMapOf(1 to "one", 2 to "two", 3 to "three")
    emptyMap4.remove(2)

//Создайте словарь (ключи Double, значения Int) и выведи в цикле результат деления ключа на значение.
// Не забудь обработать деление на 0 (в этом случае выведи слово “бесконечность”)
    val emptyMap5 = mutableMapOf<Double, Int>(10.0 to 2, 7.5 to 0, 9.0 to 3)
    for ((key, value) in emptyMap5) {
        if (value == 0) {
            println("Бесконечность")
        } else println(key / value)
    }

//Измените значение для существующего ключа в изменяемом словаре.
    val emptyMap6 = mutableMapOf("a" to 1, "b" to 2)
    emptyMap6["a"] = 3


//Создайте два словаря и объедините их в третьем изменяемом словаре через циклы.
    val emptyMap7 = mapOf("a" to 1, "b" to 2)
    val emptyMap8 = mapOf("c" to 4, "d" to 5)
    val emptyMap9 = mutableMapOf<String, Int>()
    for ((key, value) in emptyMap7) {
        emptyMap9[key] = value
    }
    for ((key, value) in emptyMap8) {
        emptyMap9[key] = value
    }
    println(emptyMap9)


//Создайте словарь, где ключами являются строки, а значениями - списки целых чисел.
// Добавьте несколько элементов в этот словарь.
    val emptyMap10 = mutableMapOf<String, List<Int>>()
    emptyMap10["a"] = listOf(3, 5, 7)
    emptyMap10["b"] = listOf(2, 4, 6)
    println(emptyMap10)

//Создай словарь, в котором ключи - это целые числа, а значения - изменяемые множества строк.
// Добавь данные в словарь. Получи значение по ключу (это должно быть множество строк) и добавь в это множество ещё строку.
// Распечатай полученное множество.
    val emptyMap11 = mutableMapOf<Int, MutableSet<String>>()
    emptyMap11[1] = mutableSetOf("Анна", "Ольга", "Евгения")
    emptyMap11[1]?.add("Ксения")
    println(emptyMap11[1])

//Создай словарь, где ключами будут пары чисел.
// Через перебор найди значение у которого пара будет содержать цифру 5 в качестве первого или второго значения.
    val emptyMap12 =
        mapOf<Pair<Int, Int>, String>(Pair(2, 5) to "Пример", Pair(3, 4) to "Второй пример", Pair(5, 7) to "Ещё пример")
    for ((key, value) in emptyMap12) {
        if (key.first == 5 || key.second == 5)
            println(value)
    }
//=========Задачи на подбор оптимального типа для словаря=============
//Словарь библиотека: Ключи - автор книги, значения - список книг
    val libraryMap = mutableMapOf<String, MutableList<String>>(
        "Достоевский" to mutableListOf("Преступление и наказание", "Идиот"),
        "Толстой" to mutableListOf("Война и мир", "Анна Каренина"),
        "Грибоедов" to mutableListOf("Горе от ума")
    )

//Справочник растений: Ключи - типы растений (например, "Цветы", "Деревья"), значения - списки названий растений
    val floraMap = mutableMapOf<String, MutableList<String>>(
        "Цветы" to mutableListOf("Ромашка"),
        "Деревья" to mutableListOf("Сосна")
    )

//Четвертьфинала: Ключи - названия спортивных команд, значения - списки игроков каждой команды
    val teamsMap = mutableMapOf<String, MutableList<String>>(
        "Спартак" to mutableListOf("Александр", "Михаил", "Иван"),
        "Зенит" to mutableListOf("Дмитрий", "Андрей", "Сергей"),
        "Динамо" to mutableListOf("Никита", "Павел", "Артём")
    )

//Курс лечения: Ключи - даты, значения - список препаратов принимаемых в дату
    val emptyMap16 = mutableMapOf<String, MutableList<String>>(
        "10.10.2026" to mutableListOf("Aspirin"),
        "11.10.2026" to mutableListOf("Paracetamol")
    )

}