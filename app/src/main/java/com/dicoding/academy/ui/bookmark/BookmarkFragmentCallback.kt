package com.dicoding.academy.ui.bookmark

import com.dicoding.academy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
