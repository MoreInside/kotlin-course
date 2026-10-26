package lessons.lesson15.homework

fun main() {

    val container = InsertMapMaterials()

    container.addMaterial("Дерево")
    container.addMaterial("Металл")

    val items = mapOf(
        "A" to "Первое",
        "B" to "Второе",
        "C" to "Третье"
    )

    container.insert(items)
    container.printContainer()

}

//1. Геометрические Фигуры
//Базовый класс: Геометрическая Фигура
//Производные классы: Многоугольник, Круг
//Дополнительное разветвление для Многоугольник: Треугольник, Четырехугольник
abstract class Figure(
    val name: String,
    val area: Double
)

open class Circle(
    name: String,
    area: Double,
    val radius: Double
) : Figure(name, area)

open class Polygon(
    name: String,
    area: Double,
    val sideCount: Int
) : Figure(name, area)

class Square(
    name: String,
    area: Double
) : Polygon(name, area, 4)

class Triangle(
    name: String,
    area: Double
) : Polygon(name, area, 3)

//2. Учебные Заведения
//Базовый класс: Учебное Заведение
//Производные классы: Школа, ВУЗ
//Дополнительное разветвление для Школа: Начальная школа, Средняя школа

abstract class EducationalInstitution(
    val name: String,
    val city: String,
    val studentCount: Int
)

open class University(
    name: String,
    city: String,
    studentCount: Int,
    val facultyCount: Int
) : EducationalInstitution(name, city, studentCount)

open class School(
    name: String,
    city: String,
    studentCount: Int,
    val hasUniform: Boolean
) : EducationalInstitution(name, city, studentCount)

class PrimarySchool(
    name: String,
    city: String,
    studentCount: Int,
    val maxGrade: Int
) : School(name, city, studentCount, true)

class SecondarySchool(
    name: String,
    city: String,
    studentCount: Int,
    val examPreparation: Boolean
) : School(name, city, studentCount, false)

//3. Иерархия Мебели
//Базовый класс: Мебель
//Производные классы: Мебель для хранения/Мебель для сидения
//Дополнительное разветвление для Мебель для сидения: Кресло/Диван

abstract class Furniture(
    val name: String,
    val material: String,
    val weight: Double
)

open class StorageFurniture(
    name: String,
    material: String,
    weight: Double,
    val shelfCount: Int
) : Furniture(name, material, weight)

open class SeatingFurniture(
    name: String,
    material: String,
    weight: Double,
    val maxOccupants: Int
) : Furniture(name, material, weight)

class Sofa(
    name: String,
    material: String,
    weight: Double
) : SeatingFurniture(name, material, weight, 4)

class Armchair(
    name: String,
    material: String,
    weight: Double
) : SeatingFurniture(name, material, weight, 1)

//Это класс контейнера.
// Он абстрактный, это означает, что нельзя создавать экземлпяр этого класса,
// он нужен только для того, чтобы быть основой для подклассов.
//Создай подклассы этого контейнера, которые будут добавлять строку material (или коллекции или словари строк)
// в какое-то определённое место (места).

abstract class Materials {
    private val materials = mutableListOf<String>()
    fun addMaterial(material: String) {
        materials.add(material)
    }

    fun extractMaterials(): List<String> {
        val extracted = materials.toList()
        materials.clear()
        return extracted
    }

    fun printContainer() {
        materials.forEachIndexed { index, layer ->
            println("[$index]: $layer")
        }
    }
}

//4. Первый класс должен вставлять строку на дно контейнера (должно появиться под индексом 0)
class InsertFirstMaterial : Materials() {

    fun insert(item: String) {
        val materials = extractMaterials()
        addMaterial(item)
        materials.forEach {
            addMaterial(it)
        }
    }
}
//5. Второй класс должен получать список строк и вставлять их в начало контейнера, но поочерёдно с теми данными, которые уже есть.
// То есть, наш список должен появиться в контейнере по индексам 0, 2, 4 и так далее.
// Короче: Вставляет новые материалы через один со старыми
class InsertSecondMaterial : Materials() {

    fun insert(items: List<String>) { // принимает список новых материалов
        val materials = extractMaterials()         // забираем старые материалы из контейнера и очищает сам контейнер
        items.forEachIndexed { index, item ->         // перебираем новые материалы по порядку,
            addMaterial(item) // Добавляет новый материал в контейнер
            if (index < materials.size) {             // проверяем, есть ли старый материал с таким же индексом
                addMaterial(materials[index])   // добавляем старый материал после нового
            }
        }
        materials.drop(items.size)
            .forEach {         // берем старые материалы, начиная с индекса items.size, то есть те, для которых не хватило новых материалов
                addMaterial(it) // возвращаем оставшиеся старые материалы в контейнер
            }
    }
}

//6. Третий класс при добавлении строки должен отсортировать весь список метиралов в алфавитном порядке включая добавляемый материал.
class InsertSortedMaterials : Materials() {

    fun insert(item: String) {
        val materials = (extractMaterials() + item).sorted()
        materials.forEach {
            addMaterial(it)
        }
    }
}
//7. Четвёртый класс должен принимать словарь (ключи и значения строки).
// Каждая пара ключа и значения должна попадать в контейнер следующим образом - ключ в начало, значение в конец.
// Таким образом в начале должны быть ключи в обратном порядке, потом существующие материалы, потом все значения.
class InsertMapMaterials: Materials(){
    fun insert (items: Map<String, String>){
        val materials = extractMaterials() //забираем старые материалы и очищаем контейнер
        items.keys.reversed().forEach{     //добавляем ключи в обратном порядке
            addMaterial(it)
        }
        materials.forEach {                //возвращаем старые материалы
            addMaterial(it)
        }
        items.values.forEach {             //добавляем значения словаря
            addMaterial(it)
        }
    }
}