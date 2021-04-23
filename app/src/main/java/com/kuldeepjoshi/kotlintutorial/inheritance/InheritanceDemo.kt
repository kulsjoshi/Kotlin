package com.kuldeepjoshi.kotlintutorial.inheritance

/**
 * Program that implements classes for different kinds of dwellings.
 * Shows how to:
 * Create class hierarchy, variables and functions with inheritance,
 * abstract class, overriding, and private vs. public variables.
 */

fun main() {

    val squareCabin = SquareCabin(6, 50.0)
    val roundHut = RoundHut(3, 10.0)
    val roundTower = RoundTower(4, 10.0, 2)

    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor Area: ${floorArea()}")
    }

    with(roundHut) {

        println("\nRound Hut\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor Area: ${floorArea()}")
        println("Carpet Floor Area: ${calculateMaxCarpetSize()}")

        println("============")

        println("Has Room? ${hasRoom()}")
        getRoom()
        println("Has Room? ${hasRoom()}")
        getRoom()

    }

    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        println("Floor Area: ${floorArea()}")
    }

}