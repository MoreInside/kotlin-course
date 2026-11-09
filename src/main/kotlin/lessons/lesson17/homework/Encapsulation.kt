package lessons.lesson17.homework

import kotlin.math.min

//========1. Игровой автомат========
//Опиши схематически класс игрового автомата, правильно расставив объявление полей (val или var)
// и методов (аргументы и возвращаемые значения), включая модификаторы доступа (private для приватных)
//--Поля:--
//- цвет
//- модель
//- включен
//- ОС загружена
//- список установленных игр
//- наличие джойстика
//- баланс вырученных средств
//- владелец
//- телефон поддержки.
//- игровой сеанс оплачен
//- стоимость одного игрового сеанса
//- пин-код автомата
//--Методы:--
//- включить
//- выключить
//- загрузить ОС
//- завершить работу ОС
//- показать список игр
//- включить игру
//- оплатить игровой сеанс
//- забрать наличные с помощью пин-кода
//- открыть сейф и выдать наличные

class GameMachine(
    val color: String,
    val model: String,
    val hasGamePad: Boolean,
) {
    private var isOn = false
    private var osIsLoaded = false
    private var owner = ""
    private var balance = 0.0
    private var gameSessionIsCharged = false
    private var sessionCost = 5.0
    private var pin: String = "1234"
    var supportPhone = ""
        private set
    private val games = mutableListOf(
        "MarioBrothers",
        "EpicGames",
        "AmongUs",
        "Minecraft",
    )
    fun turnOn() {
        isOn = true
        loadOs()
    }
    fun turnOff() {
        shutDownOs()
        isOn = false
    }
    private fun loadOs() {
        println("ОС загружена")
    }
    private fun shutDownOs() {
        println("Работа ОС завершена")
    }
    fun showGames() = games.toList()
    fun play(gameName: String) {
        if (!gameSessionIsCharged) {
            println("Оплати игру прежде чем начать")
            return
        }
        if (gameName !in games) {
            println("Игра '$gameName' не найдена")
        } else {
            println("Игра '$gameName' запущена" )
            gameSessionIsCharged = false
        }
    }
    fun pay() {
        balance += sessionCost
        gameSessionIsCharged = true
    }
    fun withdraw(pin: String): Double {
        if (pin != this.pin) {
            return 0.0
        }
        return openSafe()
    }
    private fun openSafe(): Double {
        val withdrawnAmount = balance
        balance = 0.0
        return withdrawnAmount
    }
}
//2. Почемучка
//Нужно скопировать код к себе и постараться ответить на все “почему” в комментариях
//и заданным образом доработать класс ChildrenClass (BaseClass доработке не подлежит)


abstract class BaseClass(
    // 1. privateVal доступно в main() через ChildrenClass,
    // потому что в ChildrenClass объявлено собственное публичное свойство
    // с таким же именем. Приватное свойство BaseClass остаётся недоступным.
    private val privateVal: String,

    // 2. protectedVal недоступно в main(), потому что protected
    // разрешает доступ только внутри самого класса и его наследников.
    protected val protectedVal: String,

    val publicVal: String
) {
    // 3. publicField можно изменять из main(), потому что поле публичное.
    // Перед изменением значения вызывается verifyPublicField().
    var publicField = "3. измени меня из функции main() на Антонио Бандераса и проверь через функцию getAll()" +
                "4. Доработай ChildrenClass таким образом, чтобы это получилось"
        set(value) {
            if (verifyPublicField(value)) {
                field = value
            }
        }

    // 5. protectedField нельзя изменять напрямую из main().
    // Но его можно изменить из класса-наследника.
    protected var protectedField =
        "5. измени меня из функции main() через сеттер в наследнике"

    // 6. privateField нельзя изменить из ChildrenClass или main(),
    // потому что оно приватное и принадлежит BaseClass.
    // Для изменения потребовался бы специальный метод или сеттер в BaseClass.
    // Но по условию BaseClass изменять нельзя.
    private var privateField =
        "6. нельзя изменить это поле из класса наследника"

    fun getAll(): String {
        return mapOf(
            "privateVal" to privateVal,
            "protectedVal" to protectedVal,
            "publicVal" to publicVal,
            "publicField" to publicField,
            "protectedField" to protectedField,
            "privateField" to privateField,
            "generate" to generate()
        ).map { "${it.key}: ${it.value}" }
            .joinToString("\n")
    }

    fun printText() {
        privatePrint()
    }

    // 7. Функция не может быть public, потому что возвращает ProtectedClass,
    // который имеет модификатор protected и недоступен из main().
    protected open fun getProtectedClass() = ProtectedClass()

    protected open fun verifyPublicField(value: String): Boolean {
        return value.length < 3
    }

    // 8. Метод open, поэтому его можно переопределить в ChildrenClass.
    // При вызове getAll() у объекта ChildrenClass будет вызвана
    // переопределённая версия generate().
    open fun generate(): String {
        return "Это генерация из родительского класса"
    }

    private fun privatePrint() {
        println("Печать из класса BaseClass")
    }

    // 9. Функция не может быть public или protected,
    // потому что возвращает PrivateClass, доступный только внутри BaseClass.
    private fun getPrivateClass() = PrivateClass()

    protected class ProtectedClass()

    private class PrivateClass()
}


class ChildrenClass(
    // 1. Это собственное публичное свойство ChildrenClass.
    // Именно оно доступно из main().
    val privateVal: String,

    // protectedVal — только параметр конструктора, не свойство ChildrenClass.
    protectedVal: String,

    // 10. publicVal — тоже параметр, а не свойство ChildrenClass.
    // Но он передаётся в конструктор BaseClass, где становится публичным свойством.
    publicVal: String

) : BaseClass(privateVal, protectedVal, publicVal) {

    // 12. Этот метод не переопределяет privatePrint() родителя.
    // Приватные методы не наследуются, поэтому здесь создаётся
    // самостоятельный метод с таким же именем.
    private fun privatePrint() {
        println("Печать из класса ChildrenClass")
    }

    // 8. Переопределяем метод родительского класса.
    // Теперь при вызове generate() у объекта ChildrenClass
    // будет возвращаться текст из этой реализации.
    override fun generate(): String {
        return "Это генерация из дочернего класса"
    }

    // 4. Переопределяем проверку родителя.
    // Родитель разрешает значения короче 3 символов.
    // Здесь возвращаем true, чтобы разрешить установить любую строку.
    override fun verifyPublicField(value: String): Boolean {
        return true
    }

    // 5. Публичный метод для изменения protectedField.
    // Само поле остаётся недоступным для прямого изменения из main().
    fun updateProtectedField(value: String) {
        protectedField = value
    }
}


fun main() {
    val children = ChildrenClass(
        "privateVal",
        "protectedVal",
        "publicVal"
    )

    // 1. Читаем публичное свойство ChildrenClass.
    println(children.privateVal)

    // 10. Читаем публичное свойство, унаследованное от BaseClass.
    println(children.publicVal)

    // 3 и 4. Изменяем publicField.
    // Сеттер вызывает переопределённую функцию verifyPublicField().
    children.publicField = "Антонио Бандерас"

    // 5. Изменяем protectedField через публичный метод наследника.
    children.updateProtectedField("Новое значение ProtectedField")

    // 8 и 11. Выводим значения полей и результат generate().
    println(children.getAll())

    // 12. Вызывается privatePrint() из BaseClass,
    // а не одноимённый метод ChildrenClass.
    children.printText()

    // 6. children.privateField = "Новое значение" — ошибка компиляции.
    // Поле privateField недоступно из main().
}
