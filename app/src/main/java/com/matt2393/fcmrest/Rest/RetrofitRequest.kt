package com.matt2393.fcmrest.Rest


import com.matt2393.fcmrest.Entitys.Notif

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitRequest {

    @Headers(
        "Content-Type: application/json",
        "Authorization: key=AAAAW9XstZg:APA91bEG_RXAtSEa10M4SRBUuwHsg9-ELBPH5PUle2ZJtOUhQNmlJY_CoNhfxVXJUtUVNr7annf-E3Ag4_d1RsEWxmQ9sYPPsbuxgk9O_dmrdpWAAskPIWPsMaHH71gZlMabdevGF6MT"
    )
    @POST("send")
    fun postFCM(@Body notif: Notif): Observable<ResponseBody>
}
