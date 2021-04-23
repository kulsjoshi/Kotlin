package com.kuldeepjoshi.kotlintutorial.inheritance

/**
 * Round tower with multiple stories.
 *
 * @param residents Current number of residents
 * @param radius Radius
 * @param floors Number of stories
 */
class RoundTower(
    resident: Int,
    radius: Double,
    val floor: Int
) : RoundHut(resident, radius) {
    override val buildingMaterial: String
        get() = "Stone"

    // Capacity depends on the number of floors.
    override val capacity: Int
        get() = 4 * floor

    /**
     * Calculates the total floor area for a tower dwelling
     * with multiple stories.
     *
     * @return floor area
     */
    override fun floorArea(): Double {
        return super.floorArea() * floor
    }

}