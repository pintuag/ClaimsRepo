package com.example.gitproject.models.repository

import com.example.gitproject.models.dataModel.ClaimsDataModel
import com.example.gitproject.models.httpService.ResponseHandler
import com.example.gitproject.models.httpService.Result

class DataRepository(val dataSource: DataSource) {

    fun getClaimDataFromJson(
        json: String,
        responseHandler: ResponseHandler<Result<ClaimsDataModel>>
    ) {
        dataSource.getClaimDataFromJson(json, responseHandler)
    }

}