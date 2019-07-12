package com.example.stack

import io.reactivex.Observable
import retrofit2.http.GET

interface GetData {
    @GET("/2.2/questions?order=desc&sort=activity&site=stackoverflow")
    fun getData() : Observable<Question>
}