package com.dicoding.academy.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.academy.R
import com.dicoding.academy.data.source.local.entity.CourseEntity
import com.dicoding.academy.ui.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_bookmark.view.*

class BookmarkAdapter(private var callback: BookmarkFragmentCallback) : RecyclerView.Adapter<BookmarkAdapter.CourseViewHolder>() {

    private var listCourses = ArrayList<CourseEntity>()

    fun setCourses(courses: List<CourseEntity>?) {
        if (courses == null) return
        listCourses.clear()
        listCourses.addAll(courses)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkAdapter.CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_bookmark, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.CourseViewHolder, position: Int) {
        val course = listCourses[position]
       holder.bind(course)
    }

    override fun getItemCount(): Int = listCourses.size


    inner class CourseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind (course : CourseEntity) {
            with (itemView) {
                tv_item_title.text = course.title
                tv_item_description.text = course.description
                tv_item_date.text = resources.getString(R.string.deadline_date, course.deadline)
                setOnClickListener{
                    val intent = Intent(context, DetailCourseActivity::class.java).apply {
                        putExtra(DetailCourseActivity.EXTRA_COURSES, course.courseId)
                    }
                    context.startActivity(intent)
                }

                Glide.with(context)
                    .load(course.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(img_poster)
            }
        }
    }


}