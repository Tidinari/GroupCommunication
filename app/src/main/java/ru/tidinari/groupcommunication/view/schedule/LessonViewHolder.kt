package ru.tidinari.groupcommunication.view.schedule

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.models.repo.schedule.Lesson
import ru.tidinari.groupcommunication.models.repo.schedule.LessonTime

class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val time: TextView = itemView.findViewById(R.id.time)
    private val name: TextView = itemView.findViewById(R.id.name_of_the_lesson)
    private val teacher: TextView = itemView.findViewById(R.id.teacher)
    private val activityType: TextView = itemView.findViewById(R.id.activity_type)
    private val room: TextView = itemView.findViewById(R.id.room)

    fun applyLesson(lesson: Lesson) {
        time.text = LessonTime.values()[lesson.lessonInDay].value
        name.text = lesson.name
        teacher.text = lesson.teacher
        activityType.text = lesson.activityType
        room.text = lesson.room
    }
}