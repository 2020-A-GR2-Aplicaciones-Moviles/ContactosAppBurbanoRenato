package com.burbanorenato.contactosappburbanorenato.ejercicios

class TipoDatos {

    fun variablesPrimitivas(){

    var age0: Byte = 18
    var age1: Int = 18
    var age2: Short = 18
    var age3: Long = 18 // Explicitly define variable type
    var age4 = 18L // Implicit define variable type
    val intRange = 1..4 // 1, 2, 3, 4

    val weight = 52 // Int inferred
    val healthy = 50..75
    if (weight in healthy)
    println("$weight is in $healthy range") //Prints: 52 is in 50..75 range


    //FLOTANTES
    var myValue1 = 54F
    var myValue2:Float = Float.MAX_VALUE
    println("My float value" + myValue2)
    println("My float value ${myValue2}")
    //DOUBLE
    var myValue3 = 12.34
    var myValue4:Double = Double.MAX_VALUE

    //Char
    val myChar1 = 'a' //implicito
    val myChar2:Char = 'a' //explicito
    val alphabet = 'a'..'z' //rango a-z

    //String
    val myString = "a"
    val str = "abcd"
    println (str[1]) // Prints: b
    println(str.reversed()) // Prints: dcba
    println(str.takeLast(2)) // Prints: cd
    println("student@epn.edu.ec".substringBefore("@")) // Prints: student
    println("student@epn.edu.ec".startsWith("@")) // Prints: false

    //Kotlin
    val name = "Eva"
    val age = 27
    var message = "My name is $name and I am $age years old“

    println(message) //Prints: My name is Eva and I am 27 years old
    message = "My name has ${name.length} characters"
    println(message) //Prints: My name has 3 characters

    //Boolean
    val flag: Boolean = true
    val flagl: Boolean? = null
    }

}