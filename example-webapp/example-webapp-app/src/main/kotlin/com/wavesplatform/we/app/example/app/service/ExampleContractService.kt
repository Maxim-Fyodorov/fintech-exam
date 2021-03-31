package com.wavesplatform.we.app.example.app.service

import com.wavesplatform.vst.contract.factory.ContractClientFactory
import com.wavesplatform.we.app.example.contract.ExampleContract
import org.springframework.stereotype.Service

@Service
class ExampleContractService(
    val factory: ContractClientFactory<ExampleContract>
    ) {

    fun createDso(
            name: String,
            address: String,
            phone_num: String,
            website: String,
            contractId: String
    ) : String {
        val api = factory.client { it.contractId(contractId) }
        api.contract().createDso(name, address, phone_num, website)
        return api.lastTxId
    }

    fun createProducer(
            id: String,
            name: String,
            contractId: String
    ) : String {
        val api = factory.client { it.contractId(contractId) }
        api.contract().createProducer(id, name)
        return api.lastTxId
    }

    fun createConsumer(
            id: String,
            name: String,
            contractId: String
    ) : String {
        val api = factory.client { it.contractId(contractId) }
        api.contract().createConsumer(id, name)
        return api.lastTxId
    }

    fun emitRec(
            id: String,
            contractId: String
    ) : String {
        val api = factory.client { it.contractId(contractId) }
        api.contract().emitRec(id)
        return api.lastTxId
    }

    fun increaseBalance(
            id: String,
            sum: Float,
            contractId: String
    ) : String {
        val api = factory.client { it.contractId(contractId) }
        api.contract().increaseBalance(id, sum)
        return api.lastTxId
    }

    fun sellRec(
            id: String,
            price: Float,
            contractId: String
    ) : String {
        val api = factory.client { it.contractId(contractId) }
        api.contract().sellRec(id, price)
        return api.lastTxId
    }

    fun buyRec(
            id: String,
            contractId: String
    ) : String {
        val api = factory.client { it.contractId(contractId) }
        api.contract().buyRec(id)
        return api.lastTxId
    }

    fun useRec(
            id: String,
            contractId: String
    ) : String {
        val api = factory.client { it.contractId(contractId) }
        api.contract().useRec(id)
        return api.lastTxId
    }
}
