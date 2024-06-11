package com.example.paperless.models

import java.util.Date

data class PdfEntity(
    val id:String,
    val name:String,
    val size:String,
    val lastModified: Date
)
