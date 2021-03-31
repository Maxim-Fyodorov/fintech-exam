package com.wavesplatform.we.app.example.app.controller

import com.wavesplatform.we.app.example.app.api.*
import com.wavesplatform.we.app.example.app.service.ExampleContractService
import org.springframework.http.HttpStatus.ACCEPTED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("rec")
class InvokeController(
    val contractService: ExampleContractService
) {

    @PostMapping
    fun createDso(
            @RequestBody rq: CreateDsoRequest
    ): ResponseEntity<TxDto> {
        val id = contractService.createDso(
                name = rq.name,
                address = rq.address,
                phone_num = rq.phone_num,
                website = rq.website,
                contractId = "contract_name")
        return ResponseEntity(TxDto(id), ACCEPTED)
    }

    @PostMapping
    fun createProducer(
            @RequestBody rq: CreateProducerRequest
    ): ResponseEntity<TxDto> {
        val id = contractService.createProducer(
                id = rq.id,
                name = rq.name,
                contractId = "contract_name")
        return ResponseEntity(TxDto(id), ACCEPTED)
    }

    @PostMapping
    fun createConsumer(
            @RequestBody rq: CreateConsumerRequest
    ): ResponseEntity<TxDto> {
        val id = contractService.createConsumer(
                id = rq.id,
                name = rq.name,
                contractId = "contract_name")
        return ResponseEntity(TxDto(id), ACCEPTED)
    }

    @PostMapping
    fun emitRec(
            @RequestBody rq: EmitRecRequest
    ): ResponseEntity<TxDto> {
        val id = contractService.emitRec(
                id = rq.id,
                contractId = "contract_name")
        return ResponseEntity(TxDto(id), ACCEPTED)
    }

    @PostMapping
    fun increaseBalance(
            @RequestBody rq: IncreaseBalanceRequest
    ): ResponseEntity<TxDto> {
        val id = contractService.increaseBalance(
                id = rq.id,
                sum = rq.sum,
                contractId = "contract_name")
        return ResponseEntity(TxDto(id), ACCEPTED)
    }

    @PostMapping
    fun sellRec(
            @RequestBody rq: SellRecRequest
    ): ResponseEntity<TxDto> {
        val id = contractService.sellRec(
                id = rq.id,
                price = rq.price,
                contractId = "contract_name")
        return ResponseEntity(TxDto(id), ACCEPTED)
    }

    @PostMapping
    fun buyRec(
            @RequestBody rq: BuyRecRequest
    ): ResponseEntity<TxDto> {
        val id = contractService.buyRec(
                id = rq.id,
                contractId = "contract_name")
        return ResponseEntity(TxDto(id), ACCEPTED)
    }

    @PostMapping
    fun useRec(
            @RequestBody rq: UseRecRequest
    ): ResponseEntity<TxDto> {
        val id = contractService.useRec(
                id = rq.id,
                contractId = "contract_name")
        return ResponseEntity(TxDto(id), ACCEPTED)
    }
}
