package co.edu.udea.compumovil.gr07_20262.lab1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.Labs20262Gr07Theme
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.screens.PersonalDataScreen

class PersonalDataActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val intent = Intent(this, ContactDataActivity::class.java)
    val navigateNext = { startActivity(intent) }
    setContent {
      Labs20262Gr07Theme {
        PersonalDataScreen(navigateNext)
      }
    }
  }
}