package com.example.paperless.ui.screens.home.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.paperless.R
import com.example.paperless.Data.models.PdfEntity
import com.example.paperless.utils.getfileUri
import com.example.paperless.ui.viewmodels.PdfViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfLayout(pdfEntity: PdfEntity, pdfViewModel: PdfViewModel){
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
        Card(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
            onClick = {
                val getFileUri= getfileUri(context,pdfEntity.name)
                val browserIntent = Intent(Intent.ACTION_VIEW,getFileUri)
                browserIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                activity.startActivity(browserIntent)
            }
        )
        {
            Row(
                modifier = Modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(id = R.drawable.pdficon), contentDescription =null)
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f))
                {
                    Text(text = pdfEntity.name
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text ="Size: ${pdfEntity.size}")
                }
                IconButton(onClick = {pdfViewModel.currentPdfEntity=pdfEntity
                    pdfViewModel.showRenameDialog=true
                }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_more_vert_24), contentDescription = "more")

                }
            }
        }
}



//@Preview(showBackground = true)
//@Composable
//fun PdfLayoutPreview() {
//    PdfLayout(
//        pdfEntity = PdfEntity(
//            id = "1",
//            name = "Sample PDF",
//            size = "100 KB",
//            lastModified="2023-06-11"
//        )
//    )
//}