package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
  // Projects
  @Query("SELECT * FROM projects ORDER BY id ASC")
  fun getAllProjects(): Flow<List<ProjectEntity>>

  @Query("SELECT * FROM projects WHERE id = :id")
  fun getProjectById(id: Long): Flow<ProjectEntity?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertProjects(projects: List<ProjectEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertProject(project: ProjectEntity): Long

  @Update
  suspend fun updateProject(project: ProjectEntity)

  // Portfolio
  @Query("SELECT * FROM portfolio_items ORDER BY isFeatured DESC, id ASC")
  fun getAllPortfolio(): Flow<List<PortfolioItemEntity>>

  @Query("SELECT * FROM portfolio_items WHERE category = :category ORDER BY id ASC")
  fun getPortfolioByCategory(category: String): Flow<List<PortfolioItemEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPortfolioItems(items: List<PortfolioItemEntity>)

  // Consultations
  @Query("SELECT * FROM consultations ORDER BY id DESC")
  fun getAllConsultations(): Flow<List<ConsultationEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertConsultation(consultation: ConsultationEntity): Long

  @Query("UPDATE consultations SET status = :newStatus WHERE id = :id")
  suspend fun updateConsultationStatus(id: Long, newStatus: String)

  // Materials
  @Query("SELECT * FROM material_samples ORDER BY id ASC")
  fun getAllMaterials(): Flow<List<MaterialSampleEntity>>

  @Query("UPDATE material_samples SET isClientApproved = :isApproved, status = :status WHERE id = :id")
  suspend fun updateMaterialApproval(id: Long, isApproved: Boolean, status: String)

  @Query("UPDATE material_samples SET sampleRequested = :requested WHERE id = :id")
  suspend fun toggleSampleRequest(id: Long, requested: Boolean)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMaterials(materials: List<MaterialSampleEntity>)

  // Chat
  @Query("SELECT * FROM chat_messages ORDER BY id ASC")
  fun getAllChatMessages(): Flow<List<ChatMessageEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertChatMessage(msg: ChatMessageEntity): Long

  @Query("UPDATE chat_messages SET isCompletedAction = 1 WHERE id = :id")
  suspend fun markChatActionCompleted(id: Long)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertChatMessages(messages: List<ChatMessageEntity>)

  // Invoices
  @Query("SELECT * FROM invoices ORDER BY id ASC")
  fun getAllInvoices(): Flow<List<InvoiceEntity>>

  @Query("UPDATE invoices SET status = 'Paid', paidDate = :paidDate, transactionRef = :txnRef WHERE id = :id")
  suspend fun markInvoicePaid(id: Long, paidDate: String, txnRef: String)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertInvoices(invoices: List<InvoiceEntity>)
}
