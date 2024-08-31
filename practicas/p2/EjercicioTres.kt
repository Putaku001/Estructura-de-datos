
fun main(){
    val cadenas = Array(5) {""}

    for (i in cadenas.indices){
        println("Ingresa una palabra (${i+1}/5)")
        cadenas[i] = readLine()?: ""
    }
     println("cadenas ingresadas")
     for (cadena in cadenas) {
        print(cadena)
     }
}