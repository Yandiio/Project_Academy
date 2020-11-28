package com.dicoding.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.academy.data.source.local.entity.ContentEntity
import com.dicoding.academy.data.source.local.entity.CourseEntity
import com.dicoding.academy.data.source.local.entity.ModuleEntity
import com.dicoding.academy.data.source.remote.RemoteDataSource
import com.dicoding.academy.data.source.remote.response.ContentResponse
import com.dicoding.academy.data.source.remote.response.CourseResponse
import com.dicoding.academy.data.source.remote.response.ModuleResponse

class AcademyRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    AcademyDataSource {
    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteData: RemoteDataSource): AcademyRepository =
            instance ?: synchronized(this) {
                instance ?: AcademyRepository(remoteData)
            }
    }

    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val courseResult = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(loadCourses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in loadCourses) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResult.postValue(courseList)
            }
        })

        return courseResult
    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(loadCourses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in loadCourses) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }

        })


        return courseResults
    }

    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResults = MutableLiveData<CourseEntity>()

        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(loadCourses: List<CourseResponse>) {
                lateinit var course: CourseEntity
                for (response in loadCourses) {
                    if (response.id == courseId) {
                        course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath
                        )
                    }
                }
                courseResults.postValue(course)
            }
        })

        return courseResults
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResult = MutableLiveData<List<ModuleEntity>>()

        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(loadModule: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in loadModule) {
                    val course = ModuleEntity(
                        response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false
                    )

                    moduleList.add(course)
                }

                moduleResult.postValue(moduleList)
            }
        })

        return moduleResult
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResults = MutableLiveData<ModuleEntity>()

        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(loadModule: List<ModuleResponse>) {
                lateinit var module: ModuleEntity
                for (response in loadModule) {
                    if (response.moduleId == moduleId) {
                        module = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false
                        )
                        remoteDataSource.getContent(moduleId, object : RemoteDataSource.LoadContentCallback {
                            override fun onContentReceived(loadContent: ContentResponse) {
                                module.contentEntity = ContentEntity(loadContent.content)
                                moduleResults.postValue(module)
                            }
                        })
                        break
                    }
                }
            }
        })

        return moduleResults
    }


}