package lessons.lesson03.homework

val name: String = "Name"
val age: Int = 38 //mutable

//lateinit car only
lateinit var phoneNumber: String

const val PI: Double = 3.14 //only primitive

//lazy initialization
val lazyValue: String by lazy{
    Thread.sleep(2000)
    "this is very lazy String"
}

//getters & setters
var count: Int= 0
    get() {
        return if (field > 100) field else 0
    }
    set(value) {
        if (value >=0) field = value
    }
fun test() {
    println(name)
    println(age)
    //age = 31
    println(age)
}

//car
val vin: String = "VIN-001"
var color: String = "White"
var distance: Double = 0.0
lateinit var owner: String
const val wheels: Int = 4
val deadReport: String by lazy{
    "Hehehe"
}