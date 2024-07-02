package com.example.paperless
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.paperless.ui.screens.home.HomeScreen
import com.example.paperless.ui.theme.PaperlessTheme
import com.example.paperless.ui.viewmodels.PdfViewModel

class MainActivity : ComponentActivity() {
    private val pdfViewModel by viewModels<PdfViewModel>{
        viewModelFactory{
            addInitializer(PdfViewModel::class){
                PdfViewModel(application)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        installSplashScreen()
        setContent {
            splashScreen.setKeepOnScreenCondition{pdfViewModel.isSplashScreen}
            PaperlessTheme(pdfViewModel.isDarkMode,false) {
                    HomeScreen(pdfViewModel)
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