package com.ssafy.talkeasy.core.data.remote.datasource.follow

import com.ssafy.talkeasy.core.data.remote.datasource.common.DefaultResponse
import com.ssafy.talkeasy.core.data.remote.datasource.common.PagingDefaultResponse

interface FollowRemoteDataSource {

    suspend fun requestFollowList(): PagingDefaultResponse<List<FollowResponse>>
    suspend fun requestNotificationList(): DefaultResponse<List<NotificationResponse>>
}