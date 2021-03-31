package com.wavesplatform.we.app.example.contract

import com.wavesplatform.vst.contract.ContractAction
import com.wavesplatform.vst.contract.ContractInit
import com.wavesplatform.vst.contract.InvokeParam

interface ExampleContract {

    @ContractInit
    fun create()

    @ContractAction
    fun createDso(
            @InvokeParam(name = "name") name: String,
            @InvokeParam(name = "address") address: String,
            @InvokeParam(name = "phone_num") phone_num: String,
            @InvokeParam(name = "website") website: String
    )

    @ContractAction
    fun createProducer(
            @InvokeParam(name = "id") id: String,
            @InvokeParam(name = "name") name: String
    )

    @ContractAction
    fun createConsumer(
            @InvokeParam(name = "id") id: String,
            @InvokeParam(name = "name") name: String
    )

    @ContractAction
    fun emitRec(
            @InvokeParam(name = "id") id: String
    )

    @ContractAction
    fun increaseBalance(
            @InvokeParam(name = "id") id: String,
            @InvokeParam(name = "sum") sum: Float
    )

    @ContractAction
    fun sellRec(
            @InvokeParam(name = "id") id: String,
            @InvokeParam(name = "price") price: Float
    )

    @ContractAction
    fun buyRec(
            @InvokeParam(name = "id") id: String
    )

    @ContractAction
    fun useRec(
            @InvokeParam(name = "id") id: String
    )
}
