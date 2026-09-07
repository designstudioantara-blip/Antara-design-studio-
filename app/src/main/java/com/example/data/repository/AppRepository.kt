package com.example.data.repository

import com.example.data.local.AppDao
import com.example.data.local.ChatMessageEntity
import com.example.data.local.ConsultationEntity
import com.example.data.local.InvoiceEntity
import com.example.data.local.MaterialSampleEntity
import com.example.data.local.PortfolioItemEntity
import com.example.data.local.ProjectEntity
import kotlinx.coroutines.flow.Flow

class AppRepository(private val dao: AppDao) {
  // Projects
  val allProjects: Flow<List<ProjectEntity>> = dao.getAllProjects()
  fun getProjectById(id: Long): Flow<ProjectEntity?> = dao.getProjectById(id)
  suspend fun insertProject(project: ProjectEntity): Long = dao.insertProject(project)
  suspend fun updateProject(project: ProjectEntity) = dao.updateProject(project)

  // Portfolio
  val allPortfolio: Flow<List<PortfolioItemEntity>> = dao.getAllPortfolio()
  fun getPortfolioByCategory(category: String): Flow<List<PortfolioItemEntity>> = dao.getPortfolioByCategory(category)

  // Consultations
  val allConsultations: Flow<List<ConsultationEntity>> = dao.getAllConsultations()
  suspend fun bookConsultation(consultation: ConsultationEntity): Long = dao.insertConsultation(consultation)
  suspend fun updateConsultationStatus(id: Long, status: String) = dao.updateConsultationStatus(id, status)

  // Materials
  val allMaterials: Flow<List<MaterialSampleEntity>> = dao.getAllMaterials()
  suspend fun updateMaterialApproval(id: Long, isApproved: Boolean, status: String) = dao.updateMaterialApproval(id, isApproved, status)
  suspend fun toggleSampleRequest(id: Long, requested: Boolean) = dao.toggleSampleRequest(id, requested)

  // Chat
  val allChatMessages: Flow<List<ChatMessageEntity>> = dao.getAllChatMessages()
  suspend fun sendChatMessage(msg: ChatMessageEntity): Long = dao.insertChatMessage(msg)
  suspend fun markChatActionCompleted(id: Long) = dao.markChatActionCompleted(id)

  // Invoices
  val allInvoices: Flow<List<InvoiceEntity>> = dao.getAllInvoices()
  suspend fun payInvoice(id: Long, paidDate: String, txnRef: String) = dao.markInvoicePaid(id, paidDate, txnRef)
}
