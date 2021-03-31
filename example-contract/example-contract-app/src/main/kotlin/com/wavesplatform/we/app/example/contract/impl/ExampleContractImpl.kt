package com.wavesplatform.we.app.example.contract.impl

import com.wavesplatform.vst.contract.data.ContractCall
import com.wavesplatform.vst.contract.mapping.Mapping
import com.wavesplatform.vst.contract.spring.annotation.ContractHandlerBean
import com.wavesplatform.vst.contract.state.ContractState
import com.wavesplatform.vst.contract.state.getValue
import com.wavesplatform.vst.contract.state.setValue
import com.wavesplatform.we.app.example.contract.ExampleContract

@ContractHandlerBean
class ExampleContractImpl(
    state: ContractState,
    private val call: ContractCall
) : ExampleContract {

    private var create: Boolean? by state
    private val dsos: Mapping<Dso> by state
    private val producers: Mapping<Producer> by state
    private val consumers: Mapping<Consumer> by state
    private val recs: Mapping<Rec> by state

    override fun create(){
        create = true
    }


    override fun createDso(name: String,
                           address: String,
                           phone_num: String,
                           website: String) {
        require(!dsos.has(call.sender)) {
            "DSO_IS_ALREADY_REGISTERED"
        }
        require(!producers.has(call.sender)) {
            "ALREADY_REGISTERED_AS_PRODUCER"
        }
        require(call.sender == "3QVUnXdCq7vMJDjfcuM7G5qCqFKeBQuac8P") {
            "INVALID_SENDER"
        }// хардкод просто для учебных целей, очень уж хотелось реализовать логику ролей и разрешений, а ролевая модель
        // Waves осталась за рамками двух пар, поэтому Mapping-и и функции
        val dso = Dso(
                id = call.sender,
                name = name,
                address = address,
                phone_num = phone_num,
                website = website
        )
        dsos.put(call.sender, dso)
    }

    override fun createProducer(id: String,
                                name: String) {
        require(!dsos.has(id)) {
            "ALREADY_REGISTERED_AS_DSO"
        }
        require(!producers.has(id)) {
            "ALREADY_REGISTERED"
        }
        require(dsos.has(call.sender)) {
            "INVALID_SENDER"
        }
        val producer = Producer(
                id = id,
                name = name
        )
        producers.put(id, producer)
    }

    override fun createConsumer(id: String,
                                name: String) {
        require(!consumers.has(id)) {
            "ALREADY_REGISTERED"
        }
        require(dsos.has(call.sender)) {
            "INVALID_SENDER"
        }
        val consumer = Consumer(
                id = id,
                balance = 0f)
        consumers.put(id, consumer)
    }


    override fun emitRec(id: String) {
        // в реальности выпуск должен быть привзян к произвдению электричества, а здесь просто так
        require(!recs.has(id)) {
            "ALREADY_REGISTERED"
        }
        require(producers.has(call.sender)){
            "INVALID_PRODUCER"
        }
        val rec = Rec(
                id =id,
                price = 0f,
                producer = call.sender,
                owner = call.sender,
                status = "Not for sale")
        recs.put(id, rec)
    }


    override fun increaseBalance(id: String,
                                 sum: Float) {
        require(consumers.has(id)) {
            "INVALID_CONSUMER"
        }
        val consumer = consumers[id]
        val updatedConsumer = consumer.copy(
                balance = consumer.balance + sum
        ) // баланс пополняем искусственно
        consumers.put(id, updatedConsumer)
    }

    override fun sellRec(id: String,
                         price: Float) {
        val rec = recs[id]
        require(rec.owner == call.sender){
            "NOT_YOURS_TO_SELL"
        }
        require(price >= 0) {
            "INVALID_PRICE"
        }
        val updatedRec = rec.copy(
                price = price,
                status = "For sale"
        )
        recs.put(id, updatedRec)
    }

    override fun buyRec(id: String) {
        // по задумке на уравне фронта потребитель дела с ID иметь не будет, конечно
        require(recs.has(id)) {
            "INVALID_REC"
        }
        val rec = recs[id]
        require(consumers.has(call.sender)) {
            "INVALID_BUYER"
        }
        val buyer = consumers[call.sender]
        require(rec.status == "For sale"){
            "NOT_FOR_SALE"
        }
        require(buyer.balance >= rec.price){
            "NOT_ENOUGH_MONEY"
        }
        val updatedBuyer = buyer.copy(
                balance = buyer.balance - rec.price
        )
        consumers.put(call.sender, updatedBuyer)
        val updatedRec = rec.copy(
                status = "Not for sale",
                owner = call.sender
        )
        recs.put(id, updatedRec)
    }

    override fun useRec(id: String) {
        require(recs.has(id)) {
            "INVALID_REC"
        }
        val rec = recs[id]
        require(consumers.has(call.sender)) {
            "INVALID_BUYER"
        }
        require(rec.status == "Not for sale"){
            "IT_IS_FOR_SALE"
        }
        require(call.sender == rec.owner){
            "NOT_YOURS_TO_USE"
        }
        val updatedRec = rec.copy(
                status = "Used"
        )
        recs.put(id, updatedRec)
    }
}

data class Dso(
        val id: String,
        val name: String,
        val address: String,
        val phone_num: String,
        val website: String?
)

data class Producer(
        val id: String,
        val name: String
)

data class Consumer(
        val id: String,
        var balance: Float
)

data class Rec(
        val id: String,
        val price: Float,
        val producer: String,
        val owner: String,
        val status: String
)
