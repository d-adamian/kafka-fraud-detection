package org.stepik.kafka.fraud_detection.controller

import jakarta.transaction.Transactional
import java.time.LocalDateTime
import java.time.ZoneOffset
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.stepik.kafka.fraud_detection.model.Transaction
import org.stepik.kafka.fraud_detection.model.TransactionStatus
import org.stepik.kafka.fraud_detection.repository.TransactionRepository

data class TransactionRequest(
    val amount: Double,
    val userId: Long,
    val currency: String,
)

@RestController
@RequestMapping("/transactions")
class TransactionController(val repository: TransactionRepository) {

  @PostMapping
  @Transactional
  fun post(@RequestBody request: TransactionRequest): Transaction {
    val transaction =
        Transaction(
            id = null,
            amount = request.amount,
            userId = request.userId,
            currency = request.currency,
            status = TransactionStatus.NEW,
            timestamp = LocalDateTime.now(ZoneOffset.UTC),
        )
    return repository.save(transaction)
  }
}
