package com.burbanorenato.contactosappburbanorenato.ejercicios

class EjemploWhen{
    fun ejemploWhen(){
        val vehicle = "Car"
        when (vehicle) {
            "Car", "Bike" -> print("Vehicle")
            else -> print("Unidentified funny object")
        }


        val riskAssessment = 80
        val handleStrategy = "Warn"
        val risk = when (riskAssessment) {
            in 1..20 -> print("negligible risk")
            in 21..40 -> print("minor risk")
            in 41..60 -> print("major risk")
            else -> when (handleStrategy){
                "Warn" -> "Risk assessment warning"
                "Ignore" -> "Risk ignored"
                else -> "Unknown risk!"
            }
        }
        println(risk) // Prints: Risk assessment warning
    }
}