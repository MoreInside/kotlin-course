package lessons.lesson09.homework

fun main() {
    task3()
    task4()
    task5()
    task6()
    task7()
    task8()
    task9()
    task16(setOf("Анна", "Ольга", "Мария", "Вельзевул"), "Ольга")
    task17()

}

//============Работа с массивами Array==============
//1. Создайте массив из 5 целых чисел и инициализируйте его значениями от 1 до 5.
val numbers = arrayOf(1, 2, 3, 4, 5)

//2. Создайте пустой массив строк размером 10 элементов.
val emptyArray = Array(10) { "" }

//3. Создайте массив из 5 элементов типа Double и заполните его значениями, являющимися удвоенным индексом элемента.
fun task3() {
    val doubles = DoubleArray(5)

    for (i in 0..4) {
        doubles[i] = (i * 2).toDouble()
    }
}

//4. Создайте массив из 5 элементов типа Int.
//Используйте цикл, чтобы присвоить каждому элементу значение, равное его индексу, умноженному на 3.
fun task4() {
    val numb: IntArray = IntArray(5)
    for (f in 0..4) {
        numb[f] = f * 3
    }
}

//5. Создайте массив из 3 nullable строк. Инициализируйте его одним null значением и двумя строками.
val ex1 = arrayOf<String?>(null, "Привет", "Пока")

//6. Создайте массив целых чисел и скопируйте его в новый массив в цикле.
fun task5() {
    val originalArray = arrayOf(1, 2, 3, 4, 5) //массив целых чисел
    val newArray = IntArray(5) //создать newArray на 5 элементов
    for (g in 0..4) {
        newArray[g] = originalArray[g]
    }
}

//7. Создайте два массива целых чисел одинаковой длины.
// Создайте третий массив, вычев значения одного из другого. Распечатайте полученные значения.
fun task6() {
    val oneArray = arrayOf(7, 6, 4, 6, 3)
    val secondArray = arrayOf(1, 2, 3, 4, 5)
    val threeArray = IntArray(5)
    for (dt in 0..4) {
        threeArray[dt] = oneArray[dt] - secondArray[dt]
    }
    println(threeArray.contentToString())
}

//8. Создайте массив целых чисел. Найдите индекс элемента со значением 5.
// Если значения 5 нет в массиве, печатаем -1. Реши задачу через цикл while.
fun task7() {
    val secArray: Array<Int> = arrayOf(7, 6, 4, 6, 3)
    var rep = 0
    while (rep < secArray.size) {
        if (secArray[rep] == 5) {
            println(rep)
            break
        }
        rep++
    }
    if (rep == secArray.size) {
        println(-1)
    }
}

//9. Создайте массив целых чисел.
//Используйте цикл для перебора массива и вывода каждого элемента в консоль.
// Напротив каждого элемента должно быть написано “чётное” или “нечётное”.
fun task8() {
    val satArray: Array<Int> = arrayOf(6, 8, 4, 6, 3, 6, 7, 9, 2, 0)
    var mass = 0
    while (mass < satArray.size) {
        if (satArray[mass] % 2 == 0) {
            println("${satArray[mass]} чётное")
        } else {
            println("${satArray[mass]} нечётное")
        }
        mass++
    }
}

//10. Создай функцию, которая принимает массив строк и строку для поиска.
//Функция должна находить в массиве элемент, в котором принятая строка является подстрокой (метод contains()).
//Распечатай найденный элемент.
fun task9(words: Array<String>, searchText: String) {
    for (item in words) {
        if (item.contains(searchText)) {
            println(item)
            return
        }
    }
    println("Не найдено")
}

val words = arrayOf("Green Apple", "Banana", "Orange")
val searchText = "App"
task8(words, searchText)


//==========Работа со списками List==========
//1.Создайте пустой неизменяемый список целых чисел.
val number1List: List<Int> = emptyList()

//2. Создайте неизменяемый список строк, содержащий три элемента (например, "Hello", "World", "Kotlin").
val number2List: List<String> = listOf("Hello", "World", "Kotlin")

//3. Создайте изменяемый список целых чисел и инициализируйте его значениями от 1 до 5.
val number3List: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5)

//4. Имея изменяемый список целых чисел, добавьте в него новые элементы (например, 6, 7, 8).
fun number4List() {
    val number2List: MutableList<Int> = mutableListOf()
    number2List.add(6)
    number2List.add(7)
    number2List.add(8)
    println(number2List)
}

//5. Имея изменяемый список строк, удалите из него определенный элемент (например, "World").
fun number55List() {
    val number55List: MutableList<String> = mutableListOf("War", "World", "Peace")
    number55List.remove("World")
}

//6. Создайте список целых чисел и используйте цикл для вывода каждого элемента на экран.
fun number6List() {
    val number6List = listOf(10, 20, 30, 40, 50)
    for (nan in number6List) {
        println(nan)
    }
}

//7. Создайте список строк и получите из него второй элемент, используя его индекс.
fun number7List() {
    val number7List = listOf("War", "World", "Peace")
    val tt = number7List[1]
    println(tt)
}

//8. Имея изменяемый список чисел, измените значение элемента на определенной позиции (например, замените элемент с индексом 2 на новое значение).
fun number5List() {
    val number5List: MutableList<Int> = mutableListOf(3, 2, 4, 2, 3, 1, 4, 3, 2)
    number5List[2] = 7      // переустанавливаем третий элемент
    println("number5List[2] = ${number5List[2]}") // numbers[2] = 7
}

//9. Создайте два списка строк и объедините их в один новый список, содержащий элементы обоих списков.
// Реши задачу с помощью циклов.
fun add() {
    val firstList = listOf("Apple", "Banana", "Orange")
    val secondList = listOf("Potato", "Tomato", "Carrot")
    val newList: MutableList<String> = mutableListOf()
    for (fruct in firstList) {
        newList.add(fruct)
    }
    for (ovosch in secondList) {
        newList.add(ovosch)
    }
    println(newList)
}

//10. Создайте список целых чисел и найдите в нем минимальный и максимальный элементы используя цикл.
fun dfu() {
    val number6List = listOf(22, 70, 36, 44, 90)
    var numbermax = number6List[0]
    var numbermin = number6List[0]
    for (num in number6List) {
        if (num > numbermax) {
            numbermax = num
        }
        if (num < numbermin) {
            numbermin = num
        }
    }
    println(numbermax)
    println(numbermin)
}

///11. Имея список целых чисел, создайте новый список, содержащий только четные числа из исходного списка используя цикл.
fun vtt() {
    val number7List = listOf(22, 70, 36, 44, 91)
    val neweList: MutableList<Int> = mutableListOf()
    for (numf in number7List) {
        if (numf % 2 == 0)
            neweList.add(numf)
    }
    println(neweList)
}

//==========Работа с Множествами Set===========
//1. Создайте пустое неизменяемое множество целых чисел.
val numbers1Set: Set<Int> = setOf()

//2. Создайте неизменяемое множество целых чисел, содержащее три различных элемента (например, 1, 2, 3).
val number2Set: Set<Int> = setOf(1, 2, 3)

//3. Создайте изменяемое множество строк и инициализируйте его несколькими значениями (например, "Kotlin", "Java", "Scala").
var numberes3Set: MutableSet<String> = mutableSetOf("Kotlin", "Java", "Scala")

//4. Имея изменяемое множество строк, добавьте в него новые элементы (например, "Swift", "Go").
var numberes4Set: MutableSet<String> = mutableSetOf()
numberes4Set.add("Swift")
numberes4Set.add("Go")

//5. Имея изменяемое множество целых чисел, удалите из него определенный элемент (например, 2).
val number5Set: MutableSet<Int> = mutableSetOf(1, 2, 3, 4, 5)
number5Set.remove(2)

//6. Создайте множество целых чисел и используйте цикл для вывода каждого элемента на экран.
fun task15() {
    val ext = setOf(1, 2, 3, 6, 2, 7, 9)
    for (element in ext) {
        print("$element \t")
    }
}

//7. Создай функцию, которая принимает множество строк (set) и строку и проверяет, есть ли в множестве указанная строка.
// Нужно распечатать булево значение true если строка есть. Реши задачу через цикл.
fun task16(names: Set<String>, name: String) {
    var found = false
    for (element in names) {
        if (element == name) {
            found = true
        }
    }
    println(found)
}

//8. Создайте неизменяемое множество строк и конвертируйте его в изменяемый список строк с использованием цикла.
fun task17() {
    val numbers55Set: Set<String> = setOf("один", "два", "три")
    val numbers55List: MutableList<String> = mutableListOf()
    for (chislo in numbers55Set) {
        numbers55List.add(chislo)
    }
}


