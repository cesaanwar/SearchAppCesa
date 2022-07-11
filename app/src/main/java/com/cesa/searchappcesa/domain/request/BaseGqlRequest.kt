package com.cesa.searchappcesa.domain.request

import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

abstract class BaseGqlRequest() {

    abstract fun getQueryString(): String

    abstract fun getParams(): String

    fun createRequestBody(): RequestBody {
        val variablesJson = JSONObject()
        variablesJson.put("params", getParams())
        val queryJson = JSONObject()
        queryJson.put("variables", variablesJson)
        queryJson.put("query", getQueryString())
        return RequestBody.create(
            MediaType.get("application/json; charset=utf-8"),
            queryJson.toString()
        )
    }

}