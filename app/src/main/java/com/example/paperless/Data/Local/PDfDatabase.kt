package com.example.paperless.Data.Local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.paperless.Data.Local.Converter.DateTypeConverter
import com.example.paperless.Data.Local.dao.PdfDao
import com.example.paperless.Data.models.PdfEntity

@Database(entities = [PdfEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class) // Place annotation here
abstract class PDfDatabase:RoomDatabase(){
    abstract val pdfDao: PdfDao

    companion object{
        @Volatile
        private var INSTANCES:PDfDatabase? = null

        fun getInstance(context: Context):PDfDatabase{
            synchronized(this){
                return INSTANCES ?: Room.databaseBuilder(
                    context.applicationContext,
                    PDfDatabase::class.java,
                    "pdf_db"
                ).build().also {
                    INSTANCES=it
                }
            }
        }
    }
}