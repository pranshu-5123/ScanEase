package com.example.paperless.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.paperless.R
import com.example.paperless.models.PdfEntity
import com.example.paperless.viewmodels.PdfViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenameDeleteDialog(pdfViewModel: PdfViewModel) {
    var newNameText by remember { mutableStateOf("") }
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
                            pdfViewModel.showRenameDialog = false
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
                        pdfViewModel.showRenameDialog = false
                    },colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                        Text(text = "Update")
                    }
                }
            }
        }
    }
    }

}
@Preview
@Composable
fun RenameDeleteDialogPreview() {
    val viewModel = PdfViewModel() // You might need to adjust this if your ViewModel has dependencies
    RenameDeleteDialog(viewModel)
}