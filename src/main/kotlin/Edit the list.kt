//import kotlinx.datetime.*
//import kotlinx.datetime.TimeZone
//import java.time.LocalTime
//import java.util.*
//
//fun main() {
//    val myList = mutableListOf<List<String>>()
//    var action: String
//    do {
//        println("Input an action (add, print, edit, delete, end):")
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
//            "edit" -> {
//                edit(myList)
//            }
//
//            "delete" -> {
//                delete(myList)
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
//fun edit(myList: MutableList<List<String>>) {
//    if (myList.isEmpty()) {
//        println("No tasks have been input")
//    } else {
//        printList(myList)
//        val input = getValidTaskNumber(myList.size)
//        editField(myList, input - 1)
//    }
//}
//
//fun editField(myList: MutableList<List<String>>, index: Int) {
//    val task = myList[index].toMutableList()
//    var validField = false
//    while (!validField) {
//        println("Input a field to edit (priority, date, time, task):")
//        when (readln().trim().lowercase()) {
//            "priority" -> {
//                task[0] = priority()
//                println("The task is changed")
//                validField = true
//            }
//
//            "date" -> {
//                task[1] = date().toString()
//                task[3] = daysUntil(LocalDate.parse(task[1]))
//                println("The task is changed")
//                validField = true
//            }
//
//            "time" -> {
//                task[2] = time().toString()
//                println("The task is changed")
//                validField = true
//            }
//
//            "task" -> {
//                println("Input a new task (enter a blank line to end):")
//                val newTask = readln().trim()
//                if (newTask.isNotEmpty()) {
//                    task.removeAt(4)
//                    task.add(4, newTask)
//                    println("The task is changed")
//                    validField = true
//                } else {
//                    println("The task is not changed")
//                }
//            }
//
//            else -> {
//                println("Invalid field")
//            }
//        }
//    }
//    myList[index] = task
//}
//
//
//fun delete(myList: MutableList<List<String>>) {
//    if (myList.isEmpty()) {
//        println("No tasks have been input")
//    } else {
//        printList(myList)
//        val input = getValidTaskNumber(myList.size)
//        removeTaskAt(myList, input - 1)
//    }
//}
//
//fun getValidTaskNumber(maxTaskNumber: Int): Int {
//    var input: Int?
//    do {
//        println("Input the task number (1-$maxTaskNumber):")
//        val userInput = readln().trim()
//        input = try {
//            userInput.toInt()
//        } catch (e: NumberFormatException) {
//            println("Invalid task number")
//            null
//        }
//        if (input != null && (input < 1 || input > maxTaskNumber)) {
//            println("Invalid task number")
//            input = null
//        }
//    } while (input == null)
//    return input
//}
//
//fun removeTaskAt(myList: MutableList<List<String>>, index: Int) {
//    myList.removeAt(index)
//    println("The task is deleted")
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
//
//fun printList(taskList: List<List<String>>) {
//    if (taskList.isEmpty()) {
//        println("No tasks have been input")
//    } else {
//        for ((index, task) in taskList.withIndex()) {
//            val formattedIndex = (index + 1).toString().padEnd(3)
//            val mainTaskInfo = "${task[1]} ${task[2]} ${task[0]} ${task[3]}"
//            println("$formattedIndex$mainTaskInfo")
//            for (j in 4 until task.size) {
//                println("   ${task[j]}")
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
//    val daysUntil = daysUntil(date)
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
//        listOf(priority, date.toString(), time.toString(), daysUntil) + taskList
//    }
//}
//
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
//
//fun daysUntil(date: LocalDate): String {
//    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
//    val numberOfDays = currentDate.daysUntil(date)
//    val daysUntil =
//        when {
//            numberOfDays == 0 -> "T"
//            numberOfDays > 0 -> "I"
//            else -> "O"
//        }
//    return daysUntil
//}