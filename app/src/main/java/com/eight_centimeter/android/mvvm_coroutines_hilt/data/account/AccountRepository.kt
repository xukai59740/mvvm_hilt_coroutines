package com.eight_centimeter.android.mvvm_coroutines_hilt.data.account

import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.EmployerResponse
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.account.entity.LoginRequest
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.common.SharedPrefs
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.room.UserDB
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.room.UserDao
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.BadHttpException
import com.eight_centimeter.android.mvvm_coroutines_hilt.ui.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class AccountRepository constructor(
    private val accountApi: AccountApi,
    private val userDao: UserDao,
    private val sharedPrefs: SharedPrefs
) {

    suspend fun login(): Any {
        sharedPrefs.getApiToken()
        val response = accountApi.login(
            LoginRequest(
                "asd@test.com",
                "Wsadwsad123"
            )
        )
        if (response.code != 200) {
            throw BadHttpException(
                Throwable(),
                response.code.toString(),
                response.message
            )

        }
        return response.data
    }

    suspend fun getProfile(): EmployerResponse {
        return accountApi.getProfile()
    }

    fun test2(): Flow<Result<String>> {
        return flowOf(Result.Success("zxc"))
    }

    fun test(): Flow<Result<String>> {
        return flow {
            val userDB = UserDB(
                id = 100L,
                name = "kevins"
            )
            userDao.insert(userDB)
            emit(Result.Success(userDB.name + ":" + userDB.id))
        }
    }
}
