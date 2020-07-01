package com.epnfis.contactosapp.ejercicios

class EjemploFor {
    fun EjemploFor(){
        /* JAVA
        String str = "Foo Bar";
        for(int i=0; i<str.length(); i++)
           System.out.println(str.charAt(i));
        */
        val str = "Foo Bar"
        for (i in 0 until str.length)
            println(str[i])

        for (i in 1..1) println(i) // Prints: 1

        for (i in 5..1) println(i) // Prints nothing
        for (i in 5 downTo 1) println(i) // Prints: 54321

        for (i in 3..6 step 2) println(i) // Prints: 35
        for (i in 9 downTo 1 step 3) println(i) // Prints: 963


        for (i in 'b'..'g') println(i) // Prints: bcdefg

        var array = arrayOf(1, 2, 3)
        for (item in array) //To iterate through a collection of items
            println(item) // item is Int
        for (i in array.indices) //To iterate through the collection using its index
            println(array[i])
        for ((index, value) in array.withIndex()) {
            println("Element at $index is $value")
        }

        val capitols = listOf("England" to "London", "Poland" to "Warsaw")
        for ((country, city) in capitols) {
            println("Capitol of $country is $city")
        }

    }
}