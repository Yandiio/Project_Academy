package com.dicoding.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.academy.data.source.AcademyRepository
import com.dicoding.academy.data.source.local.entity.CourseEntity
import com.dicoding.academy.data.source.local.entity.ModuleEntity
import com.dicoding.academy.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId : String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse() : CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules() : List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}