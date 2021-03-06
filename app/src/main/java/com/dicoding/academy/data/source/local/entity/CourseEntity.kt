package com.dicoding.academy.data.source.local.entity

import android.os.Parcelable

data class CourseEntity (
    var courseId: String,
    var title: String,
    var description: String,
    var deadline: String,
    var bookmarked: Boolean = false,
    var imagePath: String
)
