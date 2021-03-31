package com.wavesplatform.we.app.example.app.api

data class IncreaseBalanceRequest(
        val id: String,
        val sum: Float,
        val contractId: String
)
