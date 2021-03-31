package com.wavesplatform.we.app.example.app.api

data class CreateProducerRequest (
    val id: String,
    val name: String,
    val contractId: String
)