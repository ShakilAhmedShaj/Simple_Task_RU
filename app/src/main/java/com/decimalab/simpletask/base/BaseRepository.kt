package com.decimalab.simpletask.base

import androidx.lifecycle.MutableLiveData
import com.decimalab.simpletask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * Created by Shakil Ahmed Shaj on 23,September,2020.
 * shakilahmedshaj@gmail.com
 */
abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T,
        liveData: MutableLiveData<Resource<T>>
    ) {
        withContext(Dispatchers.IO) {
            liveData.postValue(Resource.Loading)
            try {
                liveData.postValue(Resource.Success(apiCall.invoke()))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        liveData.postValue(
                            Resource.Failure(false, throwable.code(), throwable.response()?.errorBody()
                            )
                        )
                    }
                    else -> {
                        liveData.postValue(
                            Resource.Failure(true, null, null)
                        )
                    }
                }
            }
        }
    }
}