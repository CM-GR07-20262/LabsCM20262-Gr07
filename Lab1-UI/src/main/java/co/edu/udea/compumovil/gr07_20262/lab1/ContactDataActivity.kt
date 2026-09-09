package co.edu.udea.compumovil.gr07_20262.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.ContactDataScreen
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.Labs20262Gr07Theme

class ContactDataActivity: ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Labs20262Gr07Theme {
        ContactDataScreen()
      }
    }
  }
}