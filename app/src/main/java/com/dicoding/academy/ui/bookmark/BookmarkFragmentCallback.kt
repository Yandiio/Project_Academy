package com.dicoding.academy.ui.bookmark

import com.dicoding.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
