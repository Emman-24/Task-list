
# TaskList

A simple task tracking application that allows users to manage their tasks with priorities, dates, and time. It provides options to add, edit, delete, and print tasks. Task data is saved to a local JSON file.


# Features

Add tasks with priority, date, and time
Edit existing tasks
Delete tasks
Print the list of tasks
Save task data to a local JSON file

# Dependencies

- Moshi: A modern JSON library for Kotlin and Java that makes it easy to parse and generate JSON data.

- kotlinx-datetime: A multiplatform Kotlin library for working with date and time.

# Roadmap

This Project is completed in 6 different stages

Stage 1: Read the list

Stage 2: The tasklist menu

Stage 3: Date and time

Stage 4: Edit the list

Stage 5: Frame and colors

Stage 6: Save the list

# Demo
<img width="657" alt="Captura de pantalla 2023-04-29 a la(s) 4 21 13 p m" src="https://user-images.githubusercontent.com/44042132/235324738-7f199eae-1541-47f9-a712-f5543e005d95.png">

## Lessons Learned

- Working with date and time: Managing date and time data can be complex due to the various formats, time zones, and edge cases. We used the kotlinx-datetime library to simplify working with date and time in Kotlin.
- Implementing data persistence: Storing the task data in a local JSON file allowed us to save and load the user's data between sessions. We used the Moshi library to handle JSON serialization and deserialization.

