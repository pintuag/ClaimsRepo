package com.example.gitproject.models.repository

import com.example.gitproject.models.dataModel.ClaimsDataModel
import com.example.gitproject.models.httpService.ResponseHandler
import com.example.gitproject.models.httpService.Result
import com.google.gson.Gson
import org.json.JSONObject
import java.io.IOException

object DataSource : DataSourceCalls {


    override fun getClaimDataFromJson(
        json: String,
        responseHandler: ResponseHandler<Result<ClaimsDataModel>>
    ) {
        try {
            val jsonObject = JSONObject(json)
            val gson = Gson()
            val claimsDataModel = gson.fromJson(jsonObject.toString(), ClaimsDataModel::class.java)
            responseHandler.response(Result.Success(claimsDataModel))
        } catch (e: Exception) {
            e.printStackTrace()
            responseHandler.response(Result.Error(Exception(exceptionErrors(e))))
        }
    }

    /*
    * Different type of exception handling
    * */
    fun exceptionErrors(throwable: Throwable): String {
        if (throwable is IOException) {
            // A network or conversion error happened
            return "Network Error"
        }
        if (throwable is IllegalStateException) {
            // data parsing error
            return "Data Parsing Error"
        }
        // any other network call exception
        return "Please try again Later"
    }

}