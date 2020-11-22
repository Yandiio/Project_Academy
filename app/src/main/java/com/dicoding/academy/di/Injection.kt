package com.dicoding.academy.di

import android.content.Context
import com.dicoding.academy.data.source.AcademyRepository
import com.dicoding.academy.data.source.remote.RemoteDataSource
import com.dicoding.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}