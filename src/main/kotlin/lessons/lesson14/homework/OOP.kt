package lessons.lesson14.homework

fun main() {
    val party = Party("Москва", 15)
    party.details()

    val emotion = Emotion("Гнев", 15)
    emotion.express()

    Moon.showPhase()

    val banana = Product("Banana", 1.99, 10)
    println(banana)

    val concert = Concert("Radiohead", "Luzhniky", 15000.0, 100000)
    concert.buyTicket()
    concert.buyTicket()
    concert.buyTicket()
    concert.buyTicket()
    concert.showInfo()

    val rack = Shelf.Rack(3)
    val shelf = Shelf(20)

    rack.addShelf(shelf)

    shelf.addItem("Книга")
    shelf.addItem("Чашка")

    rack.printContents()

}

//1. Событие: вечеринка. Создайте класс Party, который описывает вечеринку.
// У него должны быть свойства location (String) и attendees (Int).
// Добавьте метод details(), который выводит информацию о месте проведения и количестве гостей.
class Party(
    val location: String,
    var attendees: Int
) {
    fun details() {
        println("Место проведения $location, количество гостей $attendees")
    }
}

//2. Аспект реальности: эмоция. Создайте класс Emotion, который представляет эмоцию.
// У него должно быть свойство type (String) и intensity (Int).
// Добавьте метод express(), который выводит описание эмоции в зависимости от её типа и интенсивности.
class Emotion(
    val type: String,
    var intensity: Int
) {
    fun express() {
        println("Эмоция: $type, интенсивность: $intensity")
    }
}

//3. Природное явление: луна. Создайте объект Moon, который будет представлять Луну.
// Добавьте свойства isVisible (Boolean), чтобы отображать, видна ли Луна в данный момент, и phase (String),
// чтобы отображать текущую фазу Луны (например, "Full Moon", "New Moon").
// Добавьте метод showPhase(), который выводит текущую фазу Луны.
object Moon {
    var isVisible: Boolean = true
    var phase: String = "Полнолуние"

    fun showPhase() {
        if (isVisible) {
            println("Луна видна в фазе $phase")
        } else {
            println("Луну не видно")
        }
    }
}

//4. Покупка: продукт. Создайте дата класс для продукта, который будет представлять продукт в магазине.
// У него должны быть свойства “название”, “цена”, “количество”.
data class Product(
    val name: String,
    val price: Double,
    val quantity: Int
)

//5. Мероприятие: концерт. Создайте класс, который будет представлять концерт.
// У него должны быть свойства “группа”, “место проведения”, “стоимость”, “вместимость зала”.
// Также приватное поле “проданные билеты”. Добавьте метод, который выводит информацию о концерте и метод “купить билет”,
// который увеличивает количество проданных билетов на один.

class Concert(val group: String, val place: String, val price: Double, val capacity: Int) {
    private var soldTickets = 0
    fun buyTicket() {
        if (capacity > soldTickets) {
            soldTickets++
            println(soldTickets)
        } else println("Упс, все билеты проданы")
    }

    fun showInfo() {
        println(
            "На концерт группы $group в $place куплено $soldTickets билетов по цене $price. " +
                    "Вместимость зала: $capacity человек."
        )
    }
}

//======Задание 6. Стеллаж и полки.========
//Цель задания: Создать систему управления складским пространством с использованием классов "Стеллаж" и "Полка стеллажа".

//======Класс Shelf (Полка Стеллажа)=======
//Характеристики:
//Вместимость полки (capacity): максимальная сумма букв всех названий предметов,
// которые могут быть размещены на полке.
//Список предметов (items): хранит названия предметов на полке.

class Shelf(private val capacity: Int) {
    private var items = mutableListOf<String>()

    //Методы:
//Добавление Предмета (addItem):
//Принимает название предмета.
//Проверяет, поместится ли предмет на полку, учитывая длину его названия и текущее заполнение полки.
//Возвращает true и добавляет предмет, если есть место. В противном случае возвращает false.
    fun addItem(item: String): Boolean {
        if (items.sumOf { it.length } + item.length <= capacity) {
            items.add(item)
            return true
        } else return false
    }

    //Удаление Предмета (removeItem):
//Принимает название предмета.
//Удаляет предмет, если он найден на полке.
//Возвращает true, если предмет был удален, и false, если такой предмет не найден.
    fun removeItem(item: String): Boolean {
        return items.remove(item)
    }

    //Проверка Вместимости (canAccommodate):
//Принимает название предмета.
//Определяет, вместится ли предмет на полку.
    fun canAccommodate(item: String): Boolean {
        return items.sumOf { it.length } + item.length <= capacity
    }

    fun getCapacity(): Int {
        return capacity
    }

    //Проверка наличия предмета (containsItem):
//Принимает название предмета
//Возвращает true если такой предмет есть
    fun containsItem(name: String): Boolean {
        return items.contains(name)
    }

    //Получение списка предметов (getItems):
//Возвращает неизменяемый список предметов
    fun getItems(): List<String> {
        return items.toList()
    }

    //======Класс Rack (Стеллаж)======
//Характеристики:
//Список полок (shelves): хранит полки стеллажа.
//Максимальное количество полок.
    class Rack(val capacity: Int) {
        var shelfList = mutableListOf<Shelf>()

        //Методы:
//Добавление Полки (addShelf):
//Добавляет новую полку в стеллаж.
//Возвращает true, если полка была успешно добавлена или false если стеллаж уже заполнен
// или была попытка добавить полку которая уже установлена.
        fun addShelf(item: Shelf): Boolean {
            if (shelfList.size < capacity &&
                !shelfList.contains(item)
            ) {
                shelfList.add(item)
                return true
            } else return false
        }


        //Удаление Полки (removeShelf):
//Принимает индекс полки для удаления.
//Удаляет полку по указанному индексу.
//Возвращает список предметов полки, если полка была успешно удалена или пустой список если полка не существует.
        fun removeShelf(index: Int): List<String> {
            if (index < 0 || index >= shelfList.size) {
                return emptyList()
            }
            val items = shelfList[index].getItems()
            shelfList.removeAt(index)
            return items
        }

        //Добавление Предмета (addItem):
//Добавляет предмет на первую свободную полку.
//Возвращает true, если предмет успешно добавлен, и false, если на всех полках недостаточно места.
        fun addItem(name: String): Boolean {
            if (shelfList.isEmpty()) {
                return false
            }
            for (shelf in shelfList) {
                if (shelf.canAccommodate(name)) {
                    return shelf.addItem(name)
                }
            }
            return false
        }

        //Удаление Предмета (removeItem):
//Находит и удаляет только один предмет с любой полки.
//Возвращает true, если предмет был удален, и false, если такой предмет не найден.
        fun removeItem(name: String): Boolean {
            for (shelf in shelfList) {
                if (shelf.removeItem(name)) {
                    return true
                }
            }
            return false
        }

        //Проверка наличия предмета на стеллаже (containsItem):
//Возвращает true если предмет есть на одной из полок
        fun containsItem(name: String): Boolean {
            for (shelf in shelfList)
                if (shelf.containsItem(name))
                    return true
            return false
        }

        //Получение списка полок (getShelves):
//Возвращает неизменяемый список полок
        fun getShelves(): List<Shelf> {
            return shelfList.toList()
        }

        //Печать Содержимого (printContents):
//Выводит в консоль информацию о каждой полке: индекс, вместимость,
// оставшуюся вместимость, список предметов. Информацию выводить в наглядном читаемом виде
        fun printContents() {
            if (shelfList.isEmpty()) {
                println("Полки пустые")
            } else {
                for ((index, shelf) in shelfList.withIndex()) {
                    println("Полка ${index + 1}")
                    println("Вместимость: ${shelf.getCapacity()}")
                    println("Оставшаяся вместимость полки: ${
                        shelf.getCapacity()
                                - shelf.getItems().sumOf { it.length }
                    }"
                    )
                    println("Предметы: ${shelf.getItems()}")

                }
            }
        }

        //Сложное удаление полки (advancedRemoveShelf):
//Принимает индекс полки для удаления
//Перераспределяет предметы по имеющимся полкам, начиная с самых длинных.
//Если очередной предмет никуда не вмещается, его нужно пропустить и попробовать разместить следующий.
//Удаляет полку с оставшимися предметами
//Возвращает неизменяемый список предметов, которые не удалось удалить или пустой список если полки с таким индексом нет.
        fun advancedRemoveShelf(index: Int): List<String> {
            if (index !in shelfList.indices) {
                return emptyList()
            }
            val notReplacedItems = mutableListOf<String>()
            val otherShelves = shelfList - shelfList[index]
            for (item in shelfList[index].getItems().sortedByDescending {it.length})
            {
                if (otherShelves.none { it.addItem(item) }) {
                    notReplacedItems.add(item)
                }
            }
            shelfList.removeAt(index)
            return notReplacedItems.toList()
        }
    }
}