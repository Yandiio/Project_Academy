package com.dicoding.academy.ui.academy

import junit.framework.TestCase
import org.junit.Before

class AcademyViewModelTest : TestCase() {
    private lateinit var viewModel: AcademyViewModel

    @Before
    override fun setUp() {
        viewModel = AcademyViewModel()
    }

    fun testGetCourses() {
        val courseEntities = viewModel.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}