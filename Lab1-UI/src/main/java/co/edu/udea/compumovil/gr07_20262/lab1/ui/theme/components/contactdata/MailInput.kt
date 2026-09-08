package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun MailInput(mailValue: String, onMailChange: (String) -> Unit) {
  TextField(
    value = mailValue,
    onValueChange = onMailChange
  )
}