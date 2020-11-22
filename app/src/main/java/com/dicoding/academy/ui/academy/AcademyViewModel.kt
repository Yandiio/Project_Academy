package com.dicoding.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.dicoding.academy.data.source.AcademyRepository
import com.dicoding.academy.data.source.local.entity.CourseEntity
import com.dicoding.academy.utils.DataDummy

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel(){
    fun getCourses() : List<CourseEntity> =  academyRepository.getAllCourses()
}