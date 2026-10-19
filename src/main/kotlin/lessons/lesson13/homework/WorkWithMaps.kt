package lessons.lesson13.homework


val vocabulary =
    mapOf("task" to 2.99, "task1" to 1.54, "task2" to 3.99, "task3" to 7.99, "task4" to 1.33, "task5" to 4.21)
val testMetadata = mapOf("testLogin" to "авторизация", "testPayment" to "оплата", "testLogout" to "выход")
val testMetadataAuto =
    mutableMapOf("testLogin" to "Fail", "testPayment" to "Passed", "testLogout" to "Skipped", "testBuy" to "Passed")
val testBug =
    mutableMapOf("Ошибка авторизации" to "fixed", "Не открывается экран" to "open", "Неверная сумма" to "fixed")
val testStatus = mapOf("https://example.com" to 200, "https://example.com/login" to 404)
val testResponseTime = mapOf("Авторизация" to 1.2, "Профиль" to 2.5, "Полисы" to 3.8, "Документы" to 0.9)
val testApiResults =
    mapOf("/api/login" to "200 OK", "/api/profile" to "200 OK", "/api/policies" to "500 Internal Server Error")
val testEnvironment = mapOf(
    "browserType" to "Chrome", "browserVersion" to "140", "browserLanguage" to "ru",
    "screenResolution" to "1920x1080", "platform" to "Windows"
)
val testVersions = mapOf("Android" to "15", "iOS" to "18")
val testDevices = mapOf("Samsung S24" to "Android 15, API 35", "Poco X6 Pro" to "Android 15, API 35")
val testErrors = mapOf("200" to "OK", "500" to "Internal Server Error", "404" to "Not Found")
val testResults =
    mapOf("Test01_V1" to "Passed", "Test02_V2" to "Failed", "Test03_V1" to "Skipped", "Test04_V2" to "Passed")
val mutableTestEnvironment = mutableMapOf("browserType" to "Chrome", "browserVersion" to "140")
val testBugArchive = mapOf("Ошибка загрузки" to "fixed", "Ошибка авторизации" to "open")
val testRunResults = mutableMapOf(
    "testLogin" to "Fail", "testPayment" to "Passed",
    "testLogout" to "Skipped", "testBuy" to "Passed"
)
val testExecutionTime = mapOf(101 to 1.2, 102 to 2.5, 103 to 3.8)
val testAppVersionResponseTime = mapOf("1.0" to 1.2, "1.1" to 2.5, "1.2" to 3.8)
val testAutomationResults = mapOf(
    "testLogin" to "Fail", "testPayment" to "Passed",
    "testLogout" to "Passed", "testBuy" to "Passed"
)
val testServiceResults = mapOf(
    "optionalLogin" to "Failed", "optionalPayment" to "Passed",
    "profile" to "Failed", "optionalLogout" to "Skipped", "optionalRegistration" to "Failed"
)

fun main() {

//1. Дан словарь с именем и временем выполнения каждого автоматизированного теста в секундах.
// Определите среднее время выполнения теста.
    println(vocabulary.values.average())

//2. Имеется словарь с метаданными автоматизированных тестов, где ключи — это имена тестовых методов а значения - строка с метаданными.
// Выведите список всех тестовых методов.
    println(testMetadata.keys)

//3. В изменяемый словарь с данными о прохождении тестов добавьте новый тест и его результат.
    testMetadataAuto.put("banana", "Blocked")
    println(testMetadataAuto)

//4. Посчитайте количество успешных тестов в словаре с результатами (ключ - название, значение - результат из passed, failed, skipped).
    println(
        testMetadataAuto.values.count { it == "Passed" }
    )

//5. Удалите из изменяемого словаря с баг-трекингом запись о баге, который был исправлен (ключ - название, значение - статус исправления).
    val fixedBugs = testBug.filterValues { it == "fixed" }.keys
    for (key in fixedBugs) {
        testBug.remove(key)
    }
    println(testBug)

//6. Для словаря с результатами тестирования веб-страниц (ключ — URL страницы, значение — статус ответа),
// выведите сообщение о странице и статусе её проверки.
    testStatus.forEach { println("Страница ${it.key} отдает ${it.value}") }

//7. Найдите в словаре с названием и временем ответа сервисов только те, время ответа которых превышает заданный порог.
    println(
        testResponseTime.filterValues { it > 2.0 }
    )

//8. В словаре хранятся результаты тестирования API (ключ — endpoint, значение — статус ответа в виде строки).
// Для указанного endpoint найдите статус ответа, если endpoint отсутствует, предположите, что он не был протестирован.
    println(
        testApiResults.getOrElse("/api/documents") { "не тестировался" }
    )

//9. Из словаря, содержащего конфигурации тестового окружения (ключ — название параметра конфигурации, значение - сама конфигурация),
// получите значение для "browserType". Ответ не может быть null.
    println(
        testEnvironment.getValue("browserType")
    )

//10. Создайте копию неизменяемого словаря с данными о версиях тестируемого ПО, добавив новую версию.
    println(
        testVersions + mapOf("Chrome" to "140.0", "Backend API" to "2.1.0")
    )

//11. Используя словарь с настройками тестирования для различных мобильных устройств (ключ — модель устройства, значение - строка с настройками),
// получите настройки для конкретной модели или верните настройки по умолчанию.
    println(testDevices.getOrElse(
        "Samsung S23",
        defaultValue = { "Стандартные настройки" }
    ))

//12. Проверьте, содержит ли словарь с ошибками тестирования (ключ - код ошибки, значение - описание ошибки) определенный код ошибки.
    println(
        testErrors.containsKey("404")
    )

//13. Дан неизменяемый словарь, где ключи — это идентификаторы тестовых сценариев в формате "TestID_Version", а значения — статусы выполнения этих тестов ("Passed", "Failed", "Skipped").
// Отфильтруйте словарь, оставив только те сценарии, идентификаторы которых соответствуют определённой версии тестов, то-есть в ключе содержится требуемая версия.
    println(
        testResults.filter { (key, value) ->
            key.contains("V1")
        })

//14. У вас есть словарь, где ключи — это названия функциональных модулей приложения, а значения — результаты их тестирования.
// Проверьте, есть ли модули с неудачным тестированием.
    println(
        testMetadataAuto.any { (key, value) ->
            value == "Fail"
        }
    )

//15. Добавьте в изменяемый словарь с настройками тестовой среды настройки из другого словаря.
    mutableTestEnvironment.putAll(testDevices)
    println(mutableTestEnvironment)

//16. Объедините два неизменяемых словаря с данными о багах.
    val allTestBugs = testBug + testBugArchive
    println(allTestBugs)

//17. Очистите изменяемый словарь с временными данными о последнем прогоне автоматизированных тестов.
    testMetadataAuto.clear()
    println(testMetadataAuto)

//18. Исключите из отчета по автоматизированному тестированию те случаи, где тесты были пропущены (имеют статус “skipped”).
// Ключи - название теста, значения - статус.
    val clearMetadata = testRunResults.filterNot { it.value == "Skipped" }
    println(clearMetadata)

//19. Создайте копию словаря с конфигурациями тестирования удалив из него несколько конфигураций.
    val testEnvironmentCopy = testEnvironment.filterKeys { !it.startsWith("browser") }
    println(testEnvironmentCopy)

//20. Создайте отчет о тестировании, преобразовав словарь с результатами тестирования
// (ключ — идентификатор теста, значение — результат) в список строк формата "Test ID: результат".
    val testReport = testResults.map { (key, value) ->
        "$key: $value"
    }
    println(testReport)

//21. Преобразуйте изменяемый словарь с результатами последнего тестирования в неизменяемый для архивации.
    val testMetadataArchive = testRunResults.toMap()
    println(testMetadataArchive)

//22. Преобразуйте словарь, содержащий числовой ID теста и данные о времени выполнения тестов, заменив идентификаторы тестов на их строковый аналог
// (например через toString()).
    val testExecutionTimeString = testExecutionTime.mapKeys { it.key.toString() }
    println(testExecutionTimeString)

//23. Для словаря с оценками производительности различных версий приложения (ключи - строковая версия, значения - дробное число времени ответа сервера)
// увеличьте каждую оценку на 10%, чтобы учесть новые условия тестирования.
    val testAppVersionResponseUpTime = testAppVersionResponseTime.mapValues {
        it.value * 1.1
    }
    println(testAppVersionResponseUpTime)

//24. Проверьте, пуст ли словарь с ошибками компиляции тестов.
    println(testBug.isEmpty())

//25. Убедитесь, что словарь с результатами нагрузочного тестирования не пуст.
    println(testResponseTime.isNotEmpty())

//26. Проверьте, прошли ли успешно все автоматизированные тесты в словаре с результатами.
    val passedTestsCount = testAutomationResults.values.count { //общее количество тестов
        it == "Passed" //количество успешно пройденных тестов
    }
    println(passedTestsCount == testAutomationResults.size) //сравниваем числа и выводим true, если все тесты прошли успешно

//27. Определите, содержит ли словарь с результатами тестирования хотя бы один тест с ошибкой.
    val failedTestsCount = testAutomationResults.values.count {
        it == "Fail"
    }
    println(failedTestsCount > 0)

//28. Отфильтруйте словарь с результатами тестирования сервисов, оставив только те тесты,
// которые не прошли успешно и содержат в названии “optional”.
    println(testServiceResults.filter { (key, value) ->
        key.contains("optional") && //содержит слово
        value != "Passed" // не содержит статус
    })
}