//import kotlinx.datetime.*
//import java.time.LocalTime
//import java.util.*
//
//fun main() {
//    val myList = mutableListOf<List<String>>()
//    var action: String
//    do {
//        println("Input an action (add, print, end):")
//        action = readln().lowercase()
//        when (action) {
//            "add" -> {
//                val add = add()
//                if (add != null) myList.add(add)
//            }
//
//            "print" -> {
//                printList(myList)
//            }
//
//            "end" -> {
//                println("Tasklist exiting!")
//            }
//
//            else -> {
//                println("The input action is invalid")
//            }
//        }
//    } while (action != "end")
//}
//
//fun printList(myList: MutableList<List<String>>) {
//    if (myList.isEmpty()) {
//        println("No tasks have been input")
//    } else {
//        for ((indice, taskList) in myList.withIndex()) {
//            if (indice + 1 < 10) {
//                println("${indice + 1}  ${taskList[1]} ${taskList[2]} ${taskList[0]}")
//
//            } else {
//                println("${indice + 1} ${taskList[1]} ${taskList[2]} ${taskList[0]}")
//            }
//
//            for (j in 3 until taskList.size) {
//                println("   ${taskList[j]}")
//
//            }
//            println()
//        }
//    }
//}
//
//fun add(): List<String>? {
//    val priority = priority()
//    val date = date()
//    val time = time()
//    val taskList = mutableListOf<String>()
//    println("Input a new task (enter a blank line to end):")
//    var task = readln().trim()
//    while (task.isNotEmpty()) {
//        taskList.add(task)
//        task = readln().trim()
//    }
//    return if (taskList.isEmpty()) {
//        println("The task is blank")
//        null
//    } else {
//        listOf(priority, date.toString(), time.toString()) + taskList
//    }
//}
//
//
//fun priority(): String {
//    var priority: String
//    do {
//        println("Input the task priority (C, H, N, L):")
//        priority = readln().uppercase(Locale.getDefault())
//    } while (!setOf("C", "H", "N", "L").contains(priority))
//    return priority
//}
//
//fun date(): LocalDate {
//    while (true) {
//        println("Input the date (yyyy-mm-dd):")
//        val input = readln().trim().split("-")
//        if (input.size != 3) {
//            println("The input date is invalid")
//            continue
//        }
//        val year = input[0].toInt()
//        val month = input[1].toInt()
//        val dayOfMonth = input[2].toInt()
//        try {
//            return LocalDate(year, month, dayOfMonth)
//        } catch (e: Exception) {
//            println("The input date is invalid")
//        }
//    }
//}
//
//fun time(): LocalTime {
//    var input: String
//    var time: LocalTime?
//    do {
//        println("Input the time (hh:mm):")
//        input = readln().trim()
//        time = isValidTime(input)
//        if (time == null) {
//            println("The input time is invalid")
//        }
//    } while (time == null)
//    return time
//}
//
//fun isValidTime(input: String): LocalTime? {
//    return try {
//        val (hours, minutes) = input.split(":").map { it.toInt() }
//        val time = LocalTime.of(hours, minutes)
//        time
//    } catch (e: Exception) {
//        null
//    }
//}