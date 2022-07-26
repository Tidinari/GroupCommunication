package ru.tidinari.groupcommunication.models.repo.schedule

// TODO: добавить domain директорию, конвертировать данные, полученные из интернета, в data class с этим энамом
enum class LessonInDay(val value: String) {
    FIRST("09:00\n10:30"),
    SECOND("10:40\n12:10"),
    THIRD("12:40\n14:10"),
    FORTH("14:20\n15:50"),
    FIFTH("16:20\n17:50"),
    SIXTH("18:00\n19:30"),
    SEVENTH("19:40\n21:00");
}