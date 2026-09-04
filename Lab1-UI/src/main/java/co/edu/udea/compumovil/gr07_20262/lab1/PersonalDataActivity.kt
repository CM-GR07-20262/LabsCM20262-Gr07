package co.edu.udea.compumovil.gr07_20262.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.screens.PersonalDataScreen

class PersonalDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PersonalDataScreen()
            }
        }
    }
}