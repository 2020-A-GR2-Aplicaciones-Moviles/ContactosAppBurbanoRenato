package com.burbanorenato.contactosappburbanorenato.ejercicios

class EjemploFunciones {
    fun sum(a: Int, b:Int) : Int {
        return a + b
    }
    fun sum1(a: Int, b: Int) = a + b

    fun EjemploLlamadasAFunciones(){
        presentGently("World") // Hello. I would like to present you: World
        presentGently(42) // Hello. I would like to present you: 42

        printValue("str", suffix = "!") // Prints: (str)!

    }

    fun presentGently(v: Any) {
        println("Hello. I would like to present you: $v")
    }

    fun printValue(value: String, inBracket: Boolean = true,
                   prefix: String = "", suffix: String = "") {
        print(prefix)
        if (inBracket) {
            print("(${value})")
        } else {
            print(value)
        }
        println(suffix)
    }

    //Funciones anidadas
    fun printTwoThreeTimes() {
        fun printThree() {
            print(3)
        }
        printThree()
        printThree()
    }

}