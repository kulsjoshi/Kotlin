package com.kuldeepjoshi.kotlintutorial.collection

fun main() {

    val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)
    println("list:   ${numbers}")
    println("sorted: ${numbers.sorted()}")
    val setOfNumbers = numbers.toSet()
    println("set:    ${setOfNumbers}")
    val set1 = setOf(1, 2, 3)
    val set2 = mutableSetOf(3, 2, 1)
    println("$set1 == $set2: ${set1 == set2}")
    println("contains 7: ${setOfNumbers.contains(7)}")

    /**
     * Learn more about maps
     */
    val peopleAges = mutableMapOf<String, Int>(
        "Kuls" to 26,
        "Neha" to 27
    )
    peopleAges.put("Prahaan", 3)
    peopleAges["Maahir"] = 8
    peopleAges.put("Kuls", 100)

    println(peopleAges)

    /**
     * forEach loop for collection
     */
    peopleAges.forEach { println("${it.key} is ${it.value}") }

    println(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", "))

    /**
     * filter
     */
    val filteredName = peopleAges.filter { it.key.length < 5 }
    println("Filtered name with less than four character >> $filteredName")

    /**
     * Example
     */
    val words = listOf(
        "about",
        "acute",
        "awesome",
        "balloon",
        "best",
        "brief",
        "class",
        "coffee",
        "creative"
    )

    val filteredWords = words
        .filter { it.startsWith("c", ignoreCase = true) }
        .shuffled()
        .take(4)
        .sorted()

    println("Filtered Words : $filteredWords")


    val cities = listOf("Jeddah", "Bengaluru", "Shenzhen", "Abu Dhabi", "Mountain View", "Tripoli", "Bengaluru", "Lima", "Mandalay", "Tripoli")
    val oneWordCities = cities.toSet().toList().filter { !it.contains(" ")}.sorted()

    println("One Word City > $oneWordCities")

}