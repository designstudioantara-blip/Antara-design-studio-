package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
  entities = [
    ProjectEntity::class,
    PortfolioItemEntity::class,
    ConsultationEntity::class,
    MaterialSampleEntity::class,
    ChatMessageEntity::class,
    InvoiceEntity::class
  ],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun appDao(): AppDao

  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context, scope: CoroutineScope = CoroutineScope(Dispatchers.IO)): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          "antara_design_db"
        )
          .addCallback(AppDatabaseCallback(scope))
          .build()
        INSTANCE = instance
        instance
      }
    }

    private class AppDatabaseCallback(
      private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
      override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
          scope.launch(Dispatchers.IO) {
            populateDatabase(database.appDao())
          }
        }
      }

      suspend fun populateDatabase(dao: AppDao) {
        dao.insertProjects(InitialSeedData.initialProjects)
        dao.insertPortfolioItems(InitialSeedData.initialPortfolio)
        dao.insertMaterials(InitialSeedData.initialMaterials)
        dao.insertChatMessages(InitialSeedData.initialChatMessages)
        dao.insertInvoices(InitialSeedData.initialInvoices)
        InitialSeedData.initialConsultations.forEach {
          dao.insertConsultation(it)
        }
      }
    }
  }
}
