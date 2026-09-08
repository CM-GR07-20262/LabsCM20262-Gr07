package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SexoSelector(sexo: String, onSexoChange: (String) -> Unit) {
  Column {
    Text("Sexo:")
    Row(verticalAlignment = Alignment.CenterVertically) {
      RadioButton(
        selected = sexo == "Hombre",
        onClick = { onSexoChange("Hombre") }
      )
      Text("Hombre")

      Spacer(modifier = Modifier.width(16.dp))

      RadioButton(
        selected = sexo == "Mujer",
        onClick = { onSexoChange("Mujer") }
      )
      Text("Mujer")
    }
  }
}