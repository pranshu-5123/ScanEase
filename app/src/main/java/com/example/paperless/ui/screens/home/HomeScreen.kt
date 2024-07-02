package com.example.paperless.ui.screens.home

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.paperless.R
import com.example.paperless.Data.models.PdfEntity
import com.example.paperless.ui.screens.common.ErrorScreen
import com.example.paperless.ui.screens.common.LoadingDialog
import com.example.paperless.ui.screens.home.components.PdfLayout
import com.example.paperless.ui.screens.home.components.RenameDeleteDialog
import com.example.paperless.utils.copyPDfFileToAppDirectory
import com.example.paperless.utils.showToast
import com.example.paperless.ui.viewmodels.PdfViewModel
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(pdfViewModel: PdfViewModel){
    LoadingDialog(pdfViewModel = pdfViewModel)
    RenameDeleteDialog(pdfViewModel =pdfViewModel)
    val activity=LocalContext.current as Activity
    val context=LocalContext.current
    val pdfList by pdfViewModel.pdfStateFlow.collectAsState()
    val scannerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult())
    {result->
        if(result.resultCode == Activity.RESULT_OK){
            val scanningResult= GmsDocumentScanningResult.fromActivityResultIntent(result.data)

            scanningResult?.pdf?.let{
                pdf->
                Log.d("pdfName",pdf.uri.lastPathSegment.toString())
                val date= Date()
                //val fileName="My file"
                val fileName= SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(date) + ".pdf"
                copyPDfFileToAppDirectory(
                    context,
                    pdf.uri,fileName
                    )
                val pdfEntity= PdfEntity(UUID.randomUUID().toString(),fileName,"20kb",date)
                pdfViewModel.insertPdf(pdfEntity)
            }
        }
    }

    val scanner= remember {
        GmsDocumentScanning.getClient(
            GmsDocumentScannerOptions.Builder()
                .setGalleryImportAllowed(true)
                .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_PDF)
                .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL).build()
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text= stringResource(id = R.string.app_name))
            }, actions = {
                Switch(checked =pdfViewModel.isDarkMode, onCheckedChange =
                {pdfViewModel.isDarkMode=it})
            })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                  scanner.getStartScanIntent(activity).addOnSuccessListener {
                    scannerLauncher.launch(
                      IntentSenderRequest.Builder(it).build()
                    )
                  }.addOnFailureListener{
                      it.printStackTrace()
                      context.showToast(it.message.toString())
                  }

            },text= {Text(text = stringResource(R.string.scan))},
                icon = {
                    Icon(painter = painterResource(id = R.drawable.baseline_photo_camera_24), contentDescription ="camera access")
                }
            )
        }
    ) {
        paddingValue->
        if(pdfList.isEmpty()){
            ErrorScreen(message ="No pdf found")
        }
        else{
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue))
        {
            items(items = pdfList, key = { pdfEntity -> pdfEntity.id }) { pdfEntity ->
                PdfLayout(pdfEntity = pdfEntity, pdfViewModel = pdfViewModel)
            }
        }
        }
    }
}