package com.dicoding.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academy.data.source.AcademyRepository
import com.dicoding.academy.data.source.local.entity.ContentEntity
import com.dicoding.academy.data.source.local.entity.ModuleEntity
import com.dicoding.academy.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules() : LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule() : LiveData<ModuleEntity> = academyRepository.getContent(courseId, moduleId)
}