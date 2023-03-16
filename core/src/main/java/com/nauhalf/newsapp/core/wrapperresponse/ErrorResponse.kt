package com.nauhalf.newsapp.core.wrapperresponse

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NoInternetError : WrapperResponse.Error(message = "No Internet")

object TimeOutError : WrapperResponse.Error(message = "Time Out")

data class HttpError(
    override val message: String,
    val code: Int,
    val data: Any?,
) : WrapperResponse.Error(message = message)

fun Exception.toError(): WrapperResponse.Error {
    return try {
        when {
            // when the exception is IOException and message NoInternet
            // set error as NoInternetError
            this is IOException && message == "No Internet" -> NoInternetError

            //when the exception is HttpException
            this is HttpException -> {

                // parse the error body to RawHttpError data class
                val error = Gson().fromJson(
                    response()?.errorBody()?.string().orEmpty(),
                    RawHttpError::class.java,
                )

                // set error to HttpError with the message from error.error property or HTTP status message if it's null
                // set error code from the response HTTP Status Code or 400 if it's null
                // set data to null
                HttpError(
                    message = error.message ?: message(),
                    code = response()?.code() ?: 400,
                    data = mapOf(
                        "status" to error?.status,
                        "code" to error?.code
                    )
                )
            }
            // when the exception is SocketTimeoutException, set error to TimeOutError
            this is SocketTimeoutException -> {
                TimeOutError
            }
            // when the exception is UnknownHostException, set error to NoInternetError
            this is UnknownHostException -> {
                NoInternetError
            }
            // else set to default Error
            else -> {
                WrapperResponse.Error(message = message.orEmpty())
            }
        }
    } catch (e: Exception) {
        WrapperResponse.Error(message = e.message.orEmpty())
    }
}
