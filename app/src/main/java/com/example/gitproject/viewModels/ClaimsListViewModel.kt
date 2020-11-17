package com.example.gitproject.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.gitproject.models.dataModel.ClaimsDataModel
import com.example.gitproject.models.httpService.ResponseHandler
import com.example.gitproject.models.httpService.Result
import com.example.gitproject.models.repository.DataRepository

class ClaimsListViewModel(application: Application, val dataRepository: DataRepository) :
    AndroidViewModel(application) {


    private val claimListMutableLiveData = MutableLiveData<Result<ClaimsDataModel>>()
    val claimLiveData: MutableLiveData<Result<ClaimsDataModel>>
        get() = claimListMutableLiveData


    fun getClaimDataFromJson(json: String) {
        dataRepository.getClaimDataFromJson(json, object :
            ResponseHandler<Result<ClaimsDataModel>> {
            override fun response(response: Result<ClaimsDataModel>) {
                claimLiveData.value = response
            }
        })
    }

    override fun onCleared() {
        super.onCleared()

    }

}