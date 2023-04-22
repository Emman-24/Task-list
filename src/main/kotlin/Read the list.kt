fun main() {
    val myList = mutableListOf<String>()
    println("Input the tasks (enter a blank line to end):")
    while (!isSpace(myList)) {
        val task = readln()
        myList.add(task.trim())
    }
    remove(myList)
    printList(myList)
}

fun isSpace(list: MutableList<String>): Boolean {
    return list.contains("")
}

fun printList(list: MutableList<String>) {
    if (list.isEmpty()) println("No tasks have been input") else
        for (i in list.indices) {
            if (i + 1 >= 10) {
                println("${i + 1} ${list[i]}")
            } else {
                println("${i + 1}  ${list[i]}")
            }
        }
}

fun remove(list: MutableList<String>): MutableList<String> {
    list.removeLast()
    return list
}