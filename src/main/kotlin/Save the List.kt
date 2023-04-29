import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.io.File
import java.time.LocalTime
import java.util.*

data class Task(
    val priority: String,
    val date: String,
    val time: String,
    val daysUntil: String,
    val description: List<String>
)


fun main() {
    val myList = readTask() ?: mutableListOf()
    var action: String
    do {
        println("Input an action (add, print, edit, delete, end):")
        action = readln().lowercase()
        when (action) {
            "add" -> {
                val add = add()
                if (add != null) myList.add(add)
            }

            "print" -> {
                printList(myList)
            }

            "edit" -> {
                edit(myList)
            }

            "delete" -> {
                delete(myList)
            }

            "end" -> {
                saveTasklist(myList)
                println("Tasklist exiting!")
            }

            else -> {
                println("The input action is invalid")
            }
        }
    } while (action != "end")
}

fun edit(myList: MutableList<Task>) {
    if (myList.isEmpty()) {
        println("No tasks have been input")
    } else {
        printList(myList)
        val input = getValidTaskNumber(myList.size)
        editField(myList, input - 1)
    }
}

fun editField(myList: MutableList<Task>, index: Int) {
    val task = myList[index]
    var validField = false
    while (!validField) {
        println("Input a field to edit (priority, date, time, task):")
        when (readln().trim().lowercase()) {
            "priority" -> {
                val newPriority = priority()
                myList[index] = task.copy(priority = newPriority)
                println("The task is changed")
                validField = true
            }

            "date" -> {
                val newDate = date().toString()
                val newDaysUntil = daysUntil(LocalDate.parse(newDate))
                myList[index] = task.copy(date = newDate, daysUntil = newDaysUntil)
                println("The task is changed")
                validField = true
            }

            "time" -> {
                val newTime = time().toString()
                myList[index] = task.copy(time = newTime)
                println("The task is changed")
                validField = true
            }

            "task" -> {
                println("Input a new task (enter a blank line to end):")
                val newTask = readln().trim()
                if (newTask.isNotEmpty()) {
                    val newDescription = task.description.toMutableList()
                    newDescription.clear()
                    newDescription.add(newTask)
                    myList[index] = task.copy(description = newDescription)
                    println("The task is changed")
                    validField = true
                } else {
                    println("The task is not changed")
                }
            }

            else -> {
                println("Invalid field")
            }
        }
    }
}


fun delete(myList: MutableList<Task>) {
    if (myList.isEmpty()) {
        println("No tasks have been input")
    } else {
        printList(myList)
        val input = getValidTaskNumber(myList.size)
        removeTaskAt(myList, input - 1)
    }
}

fun getValidTaskNumber(maxTaskNumber: Int): Int {
    var input: Int?
    do {
        println("Input the task number (1-$maxTaskNumber):")
        val userInput = readln().trim()
        input = try {
            userInput.toInt()
        } catch (e: NumberFormatException) {
            println("Invalid task number")
            null
        }
        if (input != null && (input < 1 || input > maxTaskNumber)) {
            println("Invalid task number")
            input = null
        }
    } while (input == null)
    return input
}

fun removeTaskAt(myList: MutableList<Task>, index: Int) {
    myList.removeAt(index)
    println("The task is deleted")
}


fun priority(): String {
    var priority: String
    do {
        println("Input the task priority (C, H, N, L):")
        priority = readln().uppercase(Locale.getDefault())
    } while (!setOf("C", "H", "N", "L").contains(priority))
    return priority
}

fun printList(taskList: List<Task>) {
    if (taskList.isEmpty()) {
        println("No tasks have been input")
    } else {
        val separator = "+----+------------+-------+---+---+--------------------------------------------+"
        val header = "| N  |    Date    | Time  | P | D |                   Task                     |"
        println(separator)
        println(header)
        println(separator)
        for ((index, task) in taskList.withIndex()) {
            val formattedIndex = (index + 1).toString().padEnd(2)
            val date = task.date.padEnd(10)
            val time = task.time.padEnd(5)
            val p = task.priority.padEnd(1)
            val d = task.daysUntil.padEnd(1)
            val taskTexts = task.description
            //val taskTexts = task.subList(4, task.size)
            val taskLines = taskTexts.flatMap { it.chunked(44) }
            val colorPriority = colors(p)
            val dueTagColor = colors(d)

            for (i in taskLines.indices) {
                val taskInfo = taskLines[i].padEnd(44)
                if (i == 0) {
                    println("| $formattedIndex | $date | $time | $colorPriority | $dueTagColor |$taskInfo|")
                } else {
                    println("|    |            |       |   |   |$taskInfo|")
                }
                if (i == taskLines.lastIndex) {
                    println(separator)
                }
            }
        }
    }
}

fun colors(p: String): String {
    return when (p) {
        "C", "O" -> "\u001B[101m \u001B[0m"
        "H", "T" -> "\u001B[103m \u001B[0m"
        "N", "I" -> "\u001B[102m \u001B[0m"
        "L" -> "\u001B[104m \u001B[0m"
        else -> "Error in color"
    }
}

fun add(): Task? {
    val priority = priority()
    val date = date()
    val time = time()
    val daysUntil = daysUntil(date)
    val taskList = mutableListOf<String>()
    println("Input a new task (enter a blank line to end):")
    var task = readln().trim()
    while (task.isNotEmpty()) {
        taskList.add(task)
        task = readln().trim()
    }
    return if (taskList.isEmpty()) {
        println("The task is blank")
        null
    } else {
        Task(
            priority = priority,
            date = date.toString(),
            time = time.toString(),
            daysUntil = daysUntil,
            description = taskList
        )
    }
}

fun date(): LocalDate {
    while (true) {
        println("Input the date (yyyy-mm-dd):")
        val input = readln().trim().split("-")
        if (input.size != 3) {
            println("The input date is invalid")
            continue
        }
        val year = input[0].toInt()
        val month = input[1].toInt()
        val dayOfMonth = input[2].toInt()
        try {
            return LocalDate(year, month, dayOfMonth)
        } catch (e: Exception) {
            println("The input date is invalid")
        }
    }
}

fun time(): LocalTime {
    var input: String
    var time: LocalTime?
    do {
        println("Input the time (hh:mm):")
        input = readln().trim()
        time = isValidTime(input)
        if (time == null) {
            println("The input time is invalid")
        }
    } while (time == null)
    return time
}

fun isValidTime(input: String): LocalTime? {
    return try {
        val (hours, minutes) = input.split(":").map { it.toInt() }
        val time = LocalTime.of(hours, minutes)
        time
    } catch (e: Exception) {
        null
    }
}

fun daysUntil(date: LocalDate): String {
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+2")).date
    val numberOfDays = currentDate.daysUntil(date)
    val daysUntil =
        when {
            numberOfDays == 0 -> "T"
            numberOfDays > 0 -> "I"
            else -> "O"
        }
    return daysUntil
}

fun saveTasklist(taskList: List<Task>) {
    val jsonFile = File("tasklist.json")
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val type = Types.newParameterizedType(List::class.java, Task::class.java)
    val jsonAdapter: JsonAdapter<List<Task>> = moshi.adapter(type)
    val json = jsonAdapter.toJson(taskList)
    jsonFile.writeText(json)

}

fun readTask(): MutableList<Task>? {
    val jsonFile = File("tasklist.json")
    if (!jsonFile.exists()) return null
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val type = Types.newParameterizedType(List::class.java, Task::class.java)
    val jsonAdapter: JsonAdapter<List<Task>> = moshi.adapter(type)
    val json = jsonFile.readText()
    return jsonAdapter.fromJson(json)?.toMutableList()

}