// Proyecto: UD01_Actividad04_Conversores
// Aplicación de consola: conversor de divisas, distancias y temperaturas

// ---------- Factores de conversión fijos (val) ----------
const val DOLAR = 1.08
const val LIBRA = 0.86
const val YEN = 163.0
const val FRANCO_SUIZO = 0.95

const val KM_A_MILLAS = 0.621371
const val MILLAS_A_KM = 1.609344

const val ZERO_ABSOLUTO_K = 0.0

// ---------- Lectura de datos con validación ----------

// Lee un número Double; si el usuario escribe algo que no es un número, vuelve a pedirlo
fun leerDouble(mensaje: String): Double {
    var resultado: Double? = null
    while (resultado == null) {
        print(mensaje)
        resultado = readLine()?.trim()?.replace(',', '.')?.toDoubleOrNull()
        if (resultado == null) {
            println("Error: entrada no válida. Introduce un número.")
        }
    }
    return resultado
}

// Lee un número Double que no puede ser negativo
fun leerCantidadPositiva(mensaje: String): Double {
    var cantidad = leerDouble(mensaje)
    while (cantidad < 0) {
        println("Error: la cantidad no puede ser negativa.")
        cantidad = leerDouble(mensaje)
    }
    return cantidad
}

// Lee una opción entera (devuelve -1 si no es un número)
fun leerOpcion(mensaje: String): Int {
    print(mensaje)
    return readLine()?.trim()?.toIntOrNull() ?: -1
}

// ---------- Menús ----------

fun mostrarMenuPrincipal() {
    println()
    println("===== CONVERSOR DE UNIDADES =====")
    println("1. Conversor de divisas")
    println("2. Conversor de distancias")
    println("3. Conversor de temperaturas")
    println("4. Salir")
}

// ---------- Conversor de divisas ----------

fun conversorDivisas() {
    println()
    println("--- CONVERSOR DE DIVISAS (desde euros) ---")
    println("1. Euros a dólares")
    println("2. Euros a libras esterlinas")
    println("3. Euros a yenes")
    println("4. Euros a francos suizos")
    val opcion = leerOpcion("Seleccione una opción: ")

    if (opcion !in 1..4) {
        println("Error: opción no válida.")
        return
    }

    val euros = leerCantidadPositiva("Introduce la cantidad en euros: ")

    when (opcion) {
        1 -> println("$euros euros equivalen a ${euros * DOLAR} dólares.")
        2 -> println("$euros euros equivalen a ${euros * LIBRA} libras esterlinas.")
        3 -> println("$euros euros equivalen a ${euros * YEN} yenes.")
        4 -> println("$euros euros equivalen a ${euros * FRANCO_SUIZO} francos suizos.")
    }
}

// ---------- Conversor de distancias ----------

fun conversorDistancias() {
    println()
    println("--- CONVERSOR DE DISTANCIAS ---")
    println("1. Kilómetros a metros")
    println("2. Metros a kilómetros")
    println("3. Kilómetros a millas")
    println("4. Millas a kilómetros")
    println("5. Centímetros a metros")
    val opcion = leerOpcion("Seleccione una opción: ")

    if (opcion !in 1..5) {
        println("Error: opción no válida.")
        return
    }

    val valor = leerCantidadPositiva("Introduce la distancia: ")

    when (opcion) {
        1 -> println("$valor kilómetros equivalen a ${valor * 1000} metros.")
        2 -> println("$valor metros equivalen a ${valor / 1000} kilómetros.")
        3 -> println("$valor kilómetros equivalen a ${valor * KM_A_MILLAS} millas.")
        4 -> println("$valor millas equivalen a ${valor * MILLAS_A_KM} kilómetros.")
        5 -> println("$valor centímetros equivalen a ${valor / 100} metros.")
    }
}

// ---------- Conversor de temperaturas ----------

fun conversorTemperaturas() {
    println()
    println("--- CONVERSOR DE TEMPERATURAS ---")
    println("1. Celsius a Fahrenheit")
    println("2. Fahrenheit a Celsius")
    println("3. Celsius a Kelvin")
    println("4. Kelvin a Celsius")
    val opcion = leerOpcion("Seleccione una opción: ")

    if (opcion !in 1..4) {
        println("Error: opción no válida.")
        return
    }

    val temperatura = leerDouble("Introduce la temperatura: ")

    when (opcion) {
        1 -> println("$temperatura °C equivalen a ${temperatura * 9 / 5 + 32} °F.")
        2 -> println("$temperatura °F equivalen a ${(temperatura - 32) * 5 / 9} °C.")
        3 -> println("$temperatura °C equivalen a ${temperatura + 273.15} K.")
        4 -> {
            if (temperatura < ZERO_ABSOLUTO_K) {
                println("Error: la temperatura en Kelvin no puede ser inferior a 0.")
            } else {
                println("$temperatura K equivalen a ${temperatura - 273.15} °C.")
            }
        }
    }
}

// ---------- Programa principal ----------

fun main() {
    var salir = false

    while (!salir) {
        mostrarMenuPrincipal()
        val opcion = leerOpcion("Seleccione una opción: ")

        when (opcion) {
            1 -> conversorDivisas()
            2 -> conversorDistancias()
            3 -> conversorTemperaturas()
            4 -> {
                println("Saliendo del programa. ¡Hasta pronto!")
                salir = true
            }
            else -> println("Error: opción no válida.")
        }
    }
}