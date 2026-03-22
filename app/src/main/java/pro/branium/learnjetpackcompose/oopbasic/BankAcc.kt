package pro.branium.learnjetpackcompose.oopbasic

@Suppress("unused")
class BankAcc(
    private val accId: String,
    private val accNumber: String? = null,
    private val ownerName: String? = null,
    private val balance: Double? = null
) {
    fun deposit(amount: Double) {
        println("Depositing $amount to account $accId")
    }

    private fun doSomething() {

    }

    fun transfer(amount: Double, toAccount: BankAcc): Boolean {
        return false
    }

    fun withdraw(amount: Double) {

    }

    fun getBalance(): Double {
        return 0.0
    }
}

// class name: a-Z, 0-9, _

class Banking // ...

class Booking //...


fun doSomething() {

}