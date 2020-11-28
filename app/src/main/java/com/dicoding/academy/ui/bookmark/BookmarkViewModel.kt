package com.dicoding.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academy.data.source.AcademyRepository
import com.dicoding.academy.data.source.local.entity.CourseEntity
import com.dicoding.academy.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks() : LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}