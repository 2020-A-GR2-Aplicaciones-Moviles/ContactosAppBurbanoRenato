package com.epnfis.contactosapp.ejercicios

class EjemploFunciones {
    fun EjemploFuncionesTipo1()
    {
        lateinit var a: (Int) -> Int
        lateinit var b: ()->Int
        lateinit var c: (String)->Unit

        a = fun(i: Int) = i * 2
        b = fun(): Int { return 4 }
        c = fun(s: String){ println(s) }

        // Usage
        println(a(10)) // Prints: 20
        println(b()) // Prints: 4
        c("Kotlin rules") // Prints: Kotlin rules
    }
    fun EjemploFuncionesTipo2()
    {
        var a = fun(i: Int) = i * 2
        var b = fun(): Int { return 4 }
        var c = fun(s: String){ println(s) }

        // Usage
        println(a(10)) // Prints: 20
        println(b()) // Prints: 4
        c("Kotlin rules") // Prints: Kotlin rules
    }
    fun EjemploFuncionesTipo3()
    {
        val a: (Int) -> Int = fun(i: Int) = i * 2
        val b: ()->Int = fun(): Int { return 4 }
        val c: (String)->Unit = fun(s: String){ println(s) }
        // Usage
        println(a(10)) // Prints: 20
        println(b()) // Prints: 4
        c("Kotlin rules") // Prints: Kotlin rules
    }
    fun EjemploFuncionesTipo4()
    {
        var a: (Int)->Int = fun(i) = i * 2
        var b: () ->Int = fun():Int{return 4}
        var c: (String)->Unit = fun(s){ println(s) }
        // Usage
        println(a(10)) // Prints: 20
        println(b()) // Prints: 4
        c("Kotlin rules") // Prints: Kotlin rules
    }

    //var a: (Int) -> Int = { i: Int -> return i * 2 } // Error: Return is not allowed there
    var l: (Int) -> Int = l@ { i: Int -> return@l i * 2 } //OK
    val printAndReturn1 = { i: Int, j: Int ->
        println("I calculate $i + $j")
        i + j
    }
    val printAndReturn2 = {i: Int, j: Int -> println("I calculate $i + $j"); i + j }

    fun EjemploLambda1()
    {
        var a: (Int)->Int = {i -> i * 2}
        var b: () ->Int = { 4 }
        var c: (String)->Unit = {s:String -> println(s) }
        // Usage
        println(a(10)) // Prints: 20
        println(b()) // Prints: 4
        c("Kotlin rules") // Prints: Kotlin rules
    }

    fun EjemploLambda2()
    {
        var a = {i:Int -> i * 2}
        var b = { 4 }
        var c = {s:String -> println(s) }
        // Usage
        println(a(10)) // Prints: 20
        println(b()) // Prints: 4
        c("Kotlin rules") // Prints: Kotlin rules
    }
    fun EjemploLambdaWithImplicitName()
    {
        var a: (Int) -> Int = {it * 2}
        var b = { 4 }
        var c: (String)->Unit = { println(it) }
        // Usage
        println(a(10)) // Prints: 20
        println(b()) // Prints: 4
        c("Kotlin rules") // Prints: Kotlin rules
    }

    fun longOperationAsync(longOperation: ()->Unit, callback: ()->Unit) {
        Thread({
            longOperation()
            callback()
        }).start()
    }
    fun probarOperacionAsincrona()
    {
        // Usage
        longOperationAsync(
            longOperation = { Thread.sleep(1000L) },
            callback = { println("After one second") } //Prints after one second
        )
        println("Now") //Prints: Now
    }
}