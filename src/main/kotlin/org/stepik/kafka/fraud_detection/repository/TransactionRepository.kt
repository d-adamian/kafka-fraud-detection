package org.stepik.kafka.fraud_detection.repository

import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.stepik.kafka.fraud_detection.model.Transaction

interface TransactionRepository : CrudRepository<Transaction, UUID>
