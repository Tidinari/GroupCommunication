package ru.tidinari.groupcommunication.presentation.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.data.DaySchedule
import ru.tidinari.groupcommunication.domain.models.DomainLesson

class LessonsAdapter(private val lessons: DaySchedule):
    RecyclerView.Adapter<LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the layout
        val lessonView = inflater
            .inflate(
                R.layout.lesson,
                parent, false
            )
        return LessonViewHolder(lessonView)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.applyLesson(lessons[position])
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

}