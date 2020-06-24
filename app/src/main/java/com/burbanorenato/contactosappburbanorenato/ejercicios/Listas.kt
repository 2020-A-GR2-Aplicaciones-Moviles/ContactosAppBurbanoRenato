package com.burbanorenato.contactosappburbanorenato.ejercicios

class Listas {
    fun EjemploListasNormal() {
        val lestaciones: List<String> = listOf("Primavera", "Verano", "Otoño", "Invierno")
        println(lestaciones) // Imprime: [Primavera, Verano, Otoño, Invierno]
        println(lestaciones.get(2)) // Imprime: Otoño
        println(lestaciones.first()) // Imprime: Primavera
        println(lestaciones.last()) // Imprime: Invierno
        val miColeccion: List<Int> = listOf(1, 3, 6, 9, 12)
        val res = miColeccion.filter { it >= 6 }
        println("resultado $res ")// Devuelve resultado [6, 9, 12])
    }

    fun EjemploListasMutable() {
        var amigos: MutableList<String> = mutableListOf("Paco", "Miguel", "Toni")
        println("Tengo ${amigos.size} amigos: $amigos") // Imprime: Tengo 3 amigos: [Paco, Miguel, Toni]
        amigos.add("Tintu")
        println("Tengo ${amigos.size} amigos: $amigos") // Imprime: Tengo 4 amigos: [Paco, Miguel, Toni, Tintu]
        amigos.removeAt(0)
        println("Tengo ${amigos.size} amigos: $amigos") // Imprime: Tengo 3 amigos: [Miguel, Toni, Tintu]
        amigos.add(0, "Jordi")
        println("Tengo ${amigos.size} amigos: $amigos") // Imprime: Tengo 4 amigos: [Jordi, Miguel, Toni, Tintu]
        amigos.set(1, "Sonia")
        println("Tengo " + amigos.size + " amigos: $amigos") // Imprime: Tengo 4 amigos: [Jordi, Sonia, Toni, Tintu]
        println("Vacia? ${amigos.none()}") //Imprime: Vacia? false
        for (amigo in amigos) {
            println(amigo)
        }
        amigos.forEach {
            println("Amigo ${it}")
        }

        val myList = listOf(2,3,4,5,6,7,8)
        myList.any { it % 2 == 0} //devuelve true
        myList.all { it % 2 == 0} //devuelve false
        myList.none { it % 2 == 0} //devuelve false
        myList.count { it % 2 == 0} //devuelve 4
        myList.min() //devuelve 2
        myList.max() //devuelve 8
        myList.drop(2) //devuelve {4,5,6,7,8}
        myList.filter{ it % 2 == 0 }// devuelve {2,4,6,8}
        myList.filterNot{ it % 2 == 0 } //devuelve {3,5,7}
        myList.slice(listOf(2,4)) //devuelve {4,6}
        myList.take(3) //devuelve {2,3,4}
        myList.takeLast(2) //devuelve {7,8}
        myList.map{ it + 1 } //devuelve {3,4,5,6,7,8,9}

    }
}