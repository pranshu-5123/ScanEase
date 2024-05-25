package com.example.paperless
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.paperless.screens.HomeScreen
import com.example.paperless.ui.theme.PaperlessTheme
import com.example.paperless.viewmodels.PdfViewModel

class MainActivity : ComponentActivity() {
    private val pdfViewModel by viewModels<PdfViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        installSplashScreen()
        setContent {
            splashScreen.setKeepOnScreenCondition{pdfViewModel.isSplashScreen}
            PaperlessTheme {
                    HomeScreen()
                }
            }
        }
    }
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PaperlessTheme {
//        Greeting("Android")
//    }
//}