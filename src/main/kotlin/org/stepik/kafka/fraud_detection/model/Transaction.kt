package org.stepik.kafka.fraud_detection.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

enum class TransactionStatus {
  NEW,
  SUSPICIOUS,
  CONFIRMED,
}

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.UUID) val id: UUID?,
    val amount: Double,
    val status: TransactionStatus,
    val userId: Long,
    val currency: String,
    val timestamp: LocalDateTime,
)
