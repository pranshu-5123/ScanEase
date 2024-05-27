package com.example.paperless.screens

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.paperless.R
import com.example.paperless.utils.showToast
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val activity=LocalContext.current as Activity
    val context=LocalContext.current
    val scannerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult())
    {result->
        if(result.resultCode == Activity.RESULT_OK){
            val scanningResult= GmsDocumentScanningResult.fromActivityResultIntent(result.data)

            scanningResult?.pdf?.let{
                pdf->
                Log.d("pdfName",pdf.uri.lastPathSegment.toString())
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
            TopAppBar(title = { Text(text= stringResource(id = R.string.app_name)) })
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
        Text(
            text = "Hello World",
            modifier = Modifier.padding(it)
        )
    }
}