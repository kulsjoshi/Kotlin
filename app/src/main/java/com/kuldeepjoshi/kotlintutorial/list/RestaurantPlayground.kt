package com.kuldeepjoshi.kotlintutorial.list

fun main() {

    val orderList = mutableListOf<Order>()

    val order1 = Order(1)
    order1.addItem(Noodles())
//    order1.print()
    orderList.add(order1)

    println()

    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
//    order2.print()
    orderList.add(order2)

    println()

    val order3 = Order(3)
    order3.addAllItem(listOf(Noodles(), Vegetables("Carrot", "Tomato", "Onion")))
//    order3.print()
    orderList.add(order3)


    orderList.add(
        Order(4)
            .addItem(Noodles())
            .addItem(Noodles())
            .addItem(Vegetables())
            .addItem(Vegetables("carrot", "beet root"))
    )


    for (order in orderList) {
        order.print()
        println()
    }

}

open class Item(val name: String, val price: Int)

class Noodles : Item("Noodles", 5) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(vararg val toppings: String) : Item("Vegetables", 10) {

    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "$name Chef's Choice"
        } else {
            return name + " " + toppings.joinToString()
        }
    }
}

class Order(val orderNumber: Int) {

    private val itemList = mutableListOf<Item>()

    fun addItem(newItem: Item): Order {
        itemList.add(newItem)
        return this
    }

    fun addAllItem(listOfItems: List<Item>): Order {
        itemList.addAll(listOfItems)
        return this
    }

    fun print() {
        println("Order #${orderNumber}")
        var total = 0
        for (item in itemList) {
            println("${item}: $${item.price}")
            total += item.price
        }
        println("Total: $${total}")
    }

}