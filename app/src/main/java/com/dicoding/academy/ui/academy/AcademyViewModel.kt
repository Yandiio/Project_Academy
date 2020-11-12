package com.dicoding.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.dicoding.academy.data.CourseEntity
import com.dicoding.academy.utils.DataDummy

class AcademyViewModel : ViewModel(){
    fun getCourses() : List<CourseEntity> = DataDummy.generateDummyCourses()
}