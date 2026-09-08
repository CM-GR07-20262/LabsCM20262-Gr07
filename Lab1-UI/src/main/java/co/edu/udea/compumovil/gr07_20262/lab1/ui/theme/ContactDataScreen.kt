package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.TopBar

@Composable
fun ContactDataScreen() {
  Scaffold(
    topBar = { TopBar() }
  ) {
    Content(paddingValues = it)
  }
}

@Composable
fun Content(paddingValues: PaddingValues) {
  Column(Modifier.padding(paddingValues)) {
    Text("Cualquier cosa")
  }
}

