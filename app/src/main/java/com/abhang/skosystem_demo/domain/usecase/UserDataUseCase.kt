package com.abhang.skosystem_demo.domain.usecase

import com.abhang.skosystem_demo.domain.models.UserData
import com.abhang.skosystem_demo.domain.repository.UserDataLocalRepository
import com.abhang.skosystem_demo.domain.repository.UserDataRepository
import com.abhang.skosystem_demo.utils.ResponseState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataUseCase @Inject constructor(
    private val repository: UserDataRepository,
    private val localRepository: UserDataLocalRepository
) {

    operator fun invoke(dataPage: Int) = flow {
        try{
            emit(ResponseState.Loading())
            val userData = repository.getUserData(dataPage)
            val localData = localRepository.checkIfUserExist(userData.data.map { it.id })
            var mList : List<UserData> = ArrayList()
            mList = if(userData.data.isNotEmpty()){
                if(localData.containsAll(userData.data.map { it.id })) {
                    localRepository.getUserData(userData.per_page, if(dataPage==1) 0 else (dataPage-1)*userData.per_page)
                } else {
                    val pData = userData.data.filter { it.id !in localData }
                    localRepository.addUsers(pData.map { it.toUserData() })
                    localRepository.getUserData(userData.per_page, if(dataPage==1) 0 else dataPage*userData.per_page)
                }
            } else {
                localRepository.getUserData(userData.per_page, if(dataPage==1) 0 else dataPage*userData.per_page)
            }
            emit(ResponseState.Success(mList))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}