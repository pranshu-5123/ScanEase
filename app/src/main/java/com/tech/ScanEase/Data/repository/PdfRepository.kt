package com.tech.ScanEase.Data.repository

import android.app.Application
import com.tech.ScanEase.Data.Local.PDfDatabase
import com.tech.ScanEase.Data.Local.dao.PdfDao
import com.tech.ScanEase.Data.models.PdfEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class PdfRepository(application: Application) {
    private  val pdfDao: PdfDao=PDfDatabase.getInstance(application).pdfDao

    fun getPdfList()=pdfDao.getAllPdfs().flowOn(Dispatchers.IO)
    suspend fun insertPdf(pdfEntity:PdfEntity):Long{
        return pdfDao.insertPdf(pdfEntity)
    }
    suspend fun deletePdf(pdfEntity:PdfEntity):Int{
        return pdfDao.deletePdf(pdfEntity)
    }
    suspend fun updatePdf(pdfEntity:PdfEntity):Int{
        return pdfDao.updatePdf(pdfEntity)
    }

}