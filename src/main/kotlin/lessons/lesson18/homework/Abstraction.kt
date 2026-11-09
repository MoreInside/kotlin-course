package lessons.lesson18.homework

import java.io.InputStream

//====Интерфейсы для работы===

interface Powerable {
    fun powerOn()
    fun powerOff()
}

interface Openable {
    fun open()
    fun close()
}

interface WaterContainer {
    val capacity: Int
    fun fillWater(amount: Int)
    fun getWater(amount: Int)
}

interface TemperatureRegulatable {
    val maxTemperature: Int
    fun setTemperature(temp: Int)
}

interface WaterConnection {
    fun connectToWaterSupply()
    fun getWater(amount: Int)
}

interface AutomaticShutdown {
    val sensorType: String
    val maxSensoredValue: Int
    fun startMonitoring()
}

interface Drainable {
    fun connectToDrain()
    fun drain()
}

interface Timable {
    fun setTimer(time: Int)
}

interface BatteryOperated {
    fun getCapacity(): Double
    fun replaceBattery()
}

interface Mechanical {
    fun performMechanicalAction()
}

interface LightEmitting {
    fun emitLight()
    fun completeLiteEmission()
}

interface SoundEmitting {
    fun setVolume(volume: Int)
    fun mute()
    fun playSound(stream: InputStream)
}

interface Programmable {
    fun programAction(action: String)
    fun execute()
}

interface Movable {
    fun move(direction: String, distance: Int)
}

interface Cleanable {
    fun clean()
}

interface Rechargeable {
    fun getChargeLevel(): Double
    fun recharge()
}

//Задача 1. Создай абстрактные классы устройств, используя эти интерфейсы. Реализовывать методы не нужно.
//
//Холодильник
//Стиральная машина
//Умная лампа
//Электронные часы
//Робот-пылесос
//Механические часы
//Фонарик
//Кофемашина
//Умная колонка
abstract class AbstractionRefrigerator : Powerable, Openable, TemperatureRegulatable
abstract class AbstractionWashingMachine : Powerable, Openable, WaterConnection, Drainable, Timable, Programmable
abstract class SmartLamp : Powerable, LightEmitting, Programmable
abstract class DigitalClock : Powerable, Timable, SoundEmitting
abstract class RobotVacuumCleaner : Powerable, Movable, Cleanable, Rechargeable, Programmable
abstract class MechanicalClock : Mechanical
abstract class Flashlight : Powerable, LightEmitting, BatteryOperated
abstract class CoffeeMachine : Powerable, WaterConnection, TemperatureRegulatable, Programmable
abstract class SmartSpeaker : Powerable, SoundEmitting, Programmable

//Задача 2. Создай абстрактный класс для включаемого оборудования и имплементируй соответствующий интерфейс
//с реализацией методов (достаточно println с выполняемым действием).
abstract class PoweredEquipment : Powerable {
    var isPowered: Boolean = false
    override fun powerOn() {
        isPowered = true
        println("Устройство включено")
    }

    override fun powerOff() {
        isPowered = false
        println("Устройство выключено")
    }
}

//Задача 3. Создай абстрактный класс для программируемого оборудования (с имплементацией соответствующего интерфейса и реализацией методов)
//и наследуй его от абстрактного класса включаемого оборудования.
abstract class ProgrammableEquipment : PoweredEquipment(), Programmable {
    override fun programAction(action: String) {
        if (isPowered) {
            println("Устройство запрограммировано: $action")
        } else {
            println("Нельзя программировать выключенное устройство")
        }
    }
    override fun execute() {
        if (isPowered) {
            println("Выполняется запрограммированное действие")
        } else {
            println("Нельзя запустить выключенное устройство")
        }
    }
}

//Задача 4. Создай абстрактный класс оборудования с возможностью устанавливать температуру и открываться
//и с наследованием от программируемого оборудования. Также имплементируй интерфейсы.
abstract class TemperatureOpenableEquipment : ProgrammableEquipment(),
    TemperatureRegulatable, Openable {
    override val maxTemperature: Int = 100
    override fun setTemperature(temp: Int) {
        if (isPowered) {
            if (temp in 0..maxTemperature) {
                println("Установлена температура: $temp градусов")
            } else {
                println("Недопустимая температура")
            }
        } else {
            println("Нельзя установить температуру: устройство выключено")
        }
    }
    override fun open() {
        println("Устройство открыто")
    }
    override fun close() {
        println("Устройство закрыто")
    }
}

//Задача 5. Создай не абстрактные классы устройств с наследованием от абстрактного класса с возможностью
//устанавливать температуру и открываться. Пусть это будет холодильник, стиральная машина, чайник, духовка.
//Добавь недостающие интерфейсы и реализуй необходимые методы (достаточно вывод в консоль производимого действия). Обрати внимание на то, что установка температуры и программирование устройства (считай - задание режима работы) не имеет смысла при выключенном питании - добавь эту проверку.
// Холодильник
class Refrigerator : TemperatureOpenableEquipment()
// Стиральная машина
class WashingMachine : TemperatureOpenableEquipment(),
    WaterConnection, Drainable, Timable {
    override fun connectToWaterSupply() {
        println("Стиральная машина подключена к водоснабжению")
    }
    override fun getWater(amount: Int) {
        println("Стиральная машина набирает $amount литров воды")
    }
    override fun connectToDrain() {
        println("Стиральная машина подключена к сливу")
    }
    override fun drain() {
        println("Стиральная машина сливает воду")
    }
    override fun setTimer(time: Int) {
        println("Установлен таймер стирки: $time минут")
    }
}
// Чайник
class Kettle : TemperatureOpenableEquipment(),
    WaterContainer, WaterConnection, AutomaticShutdown {
    override val capacity: Int = 2
    override val sensorType: String = "Датчик температуры"
    override val maxSensoredValue: Int = 100
    override fun fillWater(amount: Int) {
        println("В чайник налито $amount литров воды")
    }
    override fun getWater(amount: Int) {
        println("Из чайника вылито $amount литров воды")
    }
    override fun connectToWaterSupply() {
        println("Чайник подключен к водоснабжению")
    }
    override fun startMonitoring() {
        println("Запущен контроль температуры")
    }
}
// Духовка
class Oven : TemperatureOpenableEquipment(), Timable {
    override fun setTimer(time: Int) {
        println("Установлен таймер духовки: $time минут")
    }
}
//Создай объекты этих устройств и позапускай их с различными методами.
fun main() {
    // Холодильник
    val refrigerator = Refrigerator()
    println("=== ХОЛОДИЛЬНИК ===")
    refrigerator.powerOn()
    refrigerator.setTemperature(5)
    refrigerator.open()
    refrigerator.close()
    refrigerator.programAction("Режим охлаждения")
    refrigerator.execute()
    refrigerator.powerOff()
    refrigerator.setTemperature(5)

    // Стиральная машина
    val washingMachine = WashingMachine()
    println("\n=== СТИРАЛЬНАЯ МАШИНА ===")
    washingMachine.powerOn()
    washingMachine.connectToWaterSupply()
    washingMachine.getWater(40)
    washingMachine.connectToDrain()
    washingMachine.setTemperature(60)
    washingMachine.setTimer(90)
    washingMachine.programAction("Режим хлопок")
    washingMachine.execute()
    washingMachine.drain()
    washingMachine.powerOff()
    washingMachine.programAction("Быстрая стирка")


    // Чайник электрический
    val kettle = Kettle()
    println("\n=== ЧАЙНИК ===")
    kettle.powerOn()
    kettle.fillWater(2)
    kettle.setTemperature(100)
    kettle.startMonitoring()
    kettle.programAction("Кипячение воды")
    kettle.execute()
    kettle.getWater(1)
    kettle.powerOff()

    // Духовка
    val oven = Oven()
    println("\n=== ДУХОВКА ===")
    oven.powerOn()
    oven.open()
    oven.setTemperature(180)
    oven.close()
    oven.setTimer(60)
    oven.programAction("Выпечка")
    oven.execute()
    oven.powerOff()
    oven.setTemperature(200)
}