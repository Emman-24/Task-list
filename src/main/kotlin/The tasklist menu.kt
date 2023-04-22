fun main() {
    val myList = mutableListOf<List<String>>()
    var action: String
    do {
        println("Input an action (add, print, end):")
        action = readln().lowercase()
        when (action) {
            "add" -> {
                val add = add()
                if (add != false) myList.add(add as List<String>)
            }

            "print" -> {
                printList(myList)
            }

            "end" -> {
                println("Tasklist exiting!")
            }

            else -> {
                println("The input action is invalid")
            }
        }
    } while (action != "end")
}

fun printList(myList: MutableList<List<String>>) {
    if (myList.isEmpty()) {
        println("No tasks have been input")
    } else {
        for ((indice, taskList) in myList.withIndex()) {
            if (indice + 1 < 10) {
                println("${indice + 1}  ${taskList[0]}")

            } else {
                println("${indice + 1} ${taskList[0]}")
            }

            for (j in 1 until taskList.size) {
                println("   ${taskList[j]}")

            }
            println()
        }
    }
}


fun add(): Any {
    val taskList = mutableListOf<String>()
    println("Input a new task (enter a blank line to end):")
    var task = readln().trim()
    while (task.isNotEmpty()) {
        taskList.add(task)
        task = readln().trim()
    }
    return if (taskList.isEmpty()) {
        println("The task is blank")
        false
    } else {
        taskList
    }
}


