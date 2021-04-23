package com.kuldeepjoshi.kotlintutorial.inheritance

/**
 * A square cabin dwelling.
 *
 *  @param residents Current number of residents
 *  @param length Length
 */
class SquareCabin(resident: Int, val length: Double) : Dwelling(resident) {
    override val buildingMaterial = "Wood"
    override val capacity = 6

    /**
     * Calculates floor area for a square dwelling.
     *
     * @return floor area
     */
    override fun floorArea(): Double {
        return length * length
    }
}