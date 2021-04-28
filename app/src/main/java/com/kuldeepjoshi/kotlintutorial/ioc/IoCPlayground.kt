package com.kuldeepjoshi.kotlintutorial.ioc

fun main() {

    var iMessageService: IMessageService
    iMessageService = WhatsApp()

    val seller = Seller(iMessageService)
    seller.notifyCustomer("Hello", "Kuls")

}

class Seller(iMessageService: IMessageService) {

    private val iMessageService: IMessageService = iMessageService

    fun notifyCustomer(message: String, userName: String) {
        iMessageService.sendMessage(message, userName)
    }
}

interface IMessageService {
    fun sendMessage(message: String, userName: String)
}

class WhatsApp : IMessageService {
    override fun sendMessage(message: String, userName: String) {
        println("WHATSAPP >> Sending message to $userName and message content is $message")
    }
}

class Hike : IMessageService {
    override fun sendMessage(message: String, userName: String) {
        println("HIKE >> Sending message to $userName and message content is $message")
    }
}