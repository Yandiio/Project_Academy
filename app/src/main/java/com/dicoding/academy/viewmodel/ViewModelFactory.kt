package com.dicoding.academy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.academy.data.source.AcademyRepository
import com.dicoding.academy.di.Injection
import com.dicoding.academy.ui.academy.AcademyViewModel
import com.dicoding.academy.ui.bookmark.BookmarkViewModel
import com.dicoding.academy.ui.detail.DetailCourseViewModel
import com.dicoding.academy.ui.reader.CourseReaderViewModel

class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override  fun <T: ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                return AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
                return DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                return BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                return CourseReaderViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}