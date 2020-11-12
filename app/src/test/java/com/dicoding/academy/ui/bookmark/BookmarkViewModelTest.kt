package com.dicoding.academy.ui.bookmark

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class BookmarkViewModelTest : TestCase() {
    private lateinit var viewModel : BookmarkViewModel
    @Before
    override fun setUp() {
        viewModel = BookmarkViewModel()
    }

    @Test
    fun testGetBookmarks() {
        val courseEntities = viewModel.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}