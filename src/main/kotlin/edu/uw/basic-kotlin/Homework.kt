package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    when(arg) {
        "Hello" -> return("world")
        "Howdy", "Bonjour" -> return("Say what?")
        0 -> return("zero")
        1 -> return("one")
        in 2..10 -> return("low number")
        is Int -> return ("a number")
        else -> return("I don't understand")
    }
}


// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(x: Int, y: Int): Int {
    return (x + y)
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(x: Int, y: Int): Int {
    return (x - y)
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(x: Int, y: Int, op: (input1: Int, input2: Int) -> Int): Int {
    return op(x, y)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, var age: Int) {
    val debugString: String = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(var amount: Int, var currency: String) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount cannot be less than zero!")
        }
        
        if (currency != "USD" && currency != "EUR" &&
            currency != "CAN" && currency != "GBP") {
            throw IllegalArgumentException("Currency is not supported here!")
        }
    }

    fun convert(newCurrency: String): Money {
        var newAmount: Int = amount
        when (this.currency) {
            "USD" -> when(newCurrency) {
                "GBP" -> newAmount = newAmount / 2
                "EUR" -> newAmount = (3 * newAmount) / 2
                "CAN" -> newAmount = (5 * newAmount) / 4
            }
            "EUR" -> when(newCurrency) {
                "USD" -> newAmount = (2 * newAmount) / 3
                "GBP" -> newAmount = newAmount / 3
                "CAN" -> newAmount = (5 * newAmount) / 6
            }
            "CAN" -> when(newCurrency) {
                "USD" -> newAmount = (4 * newAmount) / 5
                "EUR" -> newAmount = (6 * newAmount) / 5
                "GBP" -> newAmount = (2 * newAmount) / 5
            }
            "GBP" -> when(newCurrency) {
                "USD" -> newAmount = 2 * newAmount
                "EUR" -> newAmount = 3 * newAmount
                "CAN" -> newAmount = (5 * newAmount) / 2
            }
        }
        return Money(newAmount, newCurrency)
    } 

    operator fun plus(otherMoney: Money): Money {
        var convertMoney = otherMoney.convert(this.currency)
        var total = Money(convertMoney.amount + this.amount, this.currency)
        return total
    }
}
