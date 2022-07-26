package ru.tidinari.groupcommunication.view.schedule

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.models.repo.schedule.Lesson
import ru.tidinari.groupcommunication.models.repo.schedule.LessonInDay

class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val time = itemView.findViewById<TextView>(R.id.time)
    val name = itemView.findViewById<TextView>(R.id.name_of_the_lesson)
    val teacher = itemView.findViewById<TextView>(R.id.teacher)
    val activityType = itemView.findViewById<TextView>(R.id.activity_type)
    val room = itemView.findViewById<TextView>(R.id.room)

    fun applyLesson(lesson: Lesson) {
        time.text = LessonInDay.values()[lesson.lessonInDay].value
        name.text = lesson.name
        teacher.text = lesson.teacher
        activityType.text = lesson.activityType
        room.text = lesson.room
    }
}