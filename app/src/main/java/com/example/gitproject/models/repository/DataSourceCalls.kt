package com.example.gitproject.models.repository

import com.example.gitproject.models.dataModel.ClaimsDataModel
import com.example.gitproject.models.httpService.ResponseHandler
import com.example.gitproject.models.httpService.Result

interface DataSourceCalls {

    fun getClaimDataFromJson(
        json: String,
        responseHandler: ResponseHandler<Result<ClaimsDataModel>>
    )

}