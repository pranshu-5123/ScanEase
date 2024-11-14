package com.tech.ScanEase.Data.Local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tech.ScanEase.Data.Local.Converter.DateTypeConverter
import com.tech.ScanEase.Data.Local.dao.PdfDao
import com.tech.ScanEase.Data.models.PdfEntity

@Database
    (entities = [PdfEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class PDfDatabase:RoomDatabase(){
    abstract val pdfDao:PdfDao
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