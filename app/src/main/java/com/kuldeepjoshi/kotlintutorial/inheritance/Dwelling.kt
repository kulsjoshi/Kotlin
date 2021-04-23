package com.kuldeepjoshi.kotlintutorial.inheritance

/**
 * Defines properties common to all dwellings.
 * All dwellings have floorspace,
 * but its calculation is specific to the subclass.
 * Checking and getting a room are implemented here
 * because they are the same for all Dwelling subclasses.
 *
 * @param residents Current number of residents
 */
abstract class Dwelling(private var resident: Int) {

    abstract val buildingMaterial: String
    abstract val capacity: Int

    /**
     * Calculates the floor area of the dwelling.
     * Implemented by subclasses where shape is determined.
     *
     * @return floor area
     */
    abstract fun floorArea(): Double

    /**
     * Checks whether there is room for another resident.
     *
     * @return true if room available, false otherwise
     */
    fun hasRoom(): Boolean {
        return resident < capacity
    }

    /**
     * Compares the capacity to the number of residents and
     * if capacity is larger than number of residents,
     * add resident by increasing the number of residents.
     * Print the result.
     */
    fun getRoom(){
        if(capacity > resident){
            resident++
            println("YOu got a room!")
        }else{
            println("Sorry, at capacity and no rooms left.")
        }
    }

}