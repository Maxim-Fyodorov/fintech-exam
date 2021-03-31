package com.wavesplatform.we.app.example.app.api

data class CreateDsoRequest(
        val name: String,
        val address: String,
        val phone_num: String,
        val website: String,
        val contractId: String
)
