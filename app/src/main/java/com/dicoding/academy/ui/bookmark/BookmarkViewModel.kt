package com.dicoding.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.dicoding.academy.data.CourseEntity
import com.dicoding.academy.utils.DataDummy

class BookmarkViewModel : ViewModel() {
    fun getBookmarks() : List<CourseEntity> = DataDummy.generateDummyCourses()
}