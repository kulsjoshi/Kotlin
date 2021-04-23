package com.kuldeepjoshi.kotlintutorial.list

fun main() {


    /** Entire below sections are for list */
//  val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    println("List: $numbers")
    println("Size: ${numbers.size}")

    // Access elements of the list
    println("First element: ${numbers[0]}")
    println("Second element: ${numbers[1]}")
    println("Last index: ${numbers.size - 1}")
    println("Last element: ${numbers[numbers.size - 1]}")
    println("First: ${numbers.first()}")
    println("Last: ${numbers.last()}")

    // Use the contains() method
    println("Contains 4? ${numbers.contains(4)}")
    println("Contains 7? ${numbers.contains(7)}")


    val colors = listOf("Green", "Orange", "Blue", "Red", "Purple")
    println("\nColors are $colors")
    println("Reversed Colors are ${colors.reversed()}")
    println("Sorted colors are ${colors.sorted()}")

    /** Entire below sections are for MutableList */

    val entrees = mutableListOf<String>()
    println("Entrees: $entrees")

    // Add individual items using add()
    println("Add noodles: ${entrees.add("noodles")}")
    println("Entrees: $entrees")
    println("Add spaghetti: ${entrees.add("spaghetti")}")
    println("Entrees: $entrees")

    // Add a list of items using addAll()
    val moreItems = listOf("ravioli", "lasagna", "fettuccine")
    println("Add list: ${entrees.addAll(moreItems)}")
    println("Entrees: $entrees")

    // Remove an item using remove()
    println("Remove spaghetti: ${entrees.remove("spaghetti")}")
    println("Entrees: $entrees")
    println("Remove item that doesn't exist: ${entrees.remove("rice")}")
    println("Entrees: $entrees")

    // Remove an item using removeAt() with an index
    println("Remove first element: ${entrees.removeAt(0)}")
    println("Entrees: $entrees")

    // Clear out the list
    entrees.clear()
    println("Entrees: $entrees")

    // Check if the list is empty
    println("Empty? ${entrees.isEmpty()}")


    /** Entire below sections are for Loop */

    /** While Loop */

    val guestsPerFamily = listOf(2, 4, 1, 3)
    var totalGuests = 0
    var index = 0

    while (index < guestsPerFamily.size) {
        totalGuests += guestsPerFamily[index]
        index++
    }

    println("Total Guest Count: $totalGuests")

    /** For Loop */

    val listOfNames = listOf("Prahaan", "Maahir", "Kuldeep", "Neha", "Mohini")

    for (name in listOfNames) {
        println("$name has total ${name.length} characters")
    }

    for (item in 'b'..'g') {
        println(item)
    }

    for (item in 1..5) {
        println(item)
    }

    for (item in 5 downTo 1) {
        println(item)
    }

    for (item in 3..6 step 2) {
        println(item)
    }


}