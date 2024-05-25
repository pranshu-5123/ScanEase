package com.example.paperless.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.paperless.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text= stringResource(id = R.string.app_name)) })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {

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