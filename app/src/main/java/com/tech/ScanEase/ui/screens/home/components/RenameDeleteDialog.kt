package com.tech.ScanEase.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tech.ScanEase.ui.viewmodels.PdfViewModel
import com.tech.ScanEase.utils.deleteFile
import com.tech.ScanEase.utils.renameFile
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenameDeleteDialog(pdfViewModel: PdfViewModel) {
    var newNameText by remember(pdfViewModel.currentPdfEntity){
    mutableStateOf(pdfViewModel.currentPdfEntity?.name ?:"")
    }
    val context= LocalContext.current
    if(pdfViewModel.showRenameDialog){

    Dialog(onDismissRequest = { pdfViewModel.showRenameDialog = false }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Rename File",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newNameText,
                    onValueChange = { newNameText = it },
                    label = { Text("Enter PDF Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            // TODO: Handle delete action
                            pdfViewModel.currentPdfEntity?.let {
                                pdfViewModel.showRenameDialog=false
                                if(deleteFile(context,it.name)){
                                    pdfViewModel.deletePdf(it)
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red) // Red delete button
                    ) {
                        Text(text = "Delete", color = Color.White) // White text on red button
                    }

                    Button(onClick = { pdfViewModel.showRenameDialog = false }) {
                        Text(text = "Cancel")
                    }

                    Button(onClick = {
                        // TODO: Handle update action with newNameText
                        pdfViewModel.currentPdfEntity?.let {pdf->
                            if(!pdf.name.equals(newNameText,true)){
                                pdfViewModel.showRenameDialog = false
                                renameFile(context,pdf.name,newNameText)
                                val updatedPdf = pdf.copy(name = newNameText, lastModified= Date() )
                                pdfViewModel.updatePdf(updatedPdf)
                            }
                            else{
                                pdfViewModel.showRenameDialog = false
                            }
                            }

//                        else{
//
//                        }
                    }) {
                        Text(text = "Update")
                    }
                }
            }
        }
    }
    }
}
