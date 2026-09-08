package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradoEscolaridadSelector(seleccionado: String, onSeleccionChange: (String) -> Unit) {
  val opciones = listOf("Primaria", "Secundaria", "Universitaria", "Otro")
  var expanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = !expanded }
  ) {
    TextField(
      value = seleccionado,
      onValueChange = {},
      readOnly = true,
      label = { Text("Grado de escolaridad") },
      modifier = Modifier.menuAnchor()
    )
    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      opciones.forEach { opcion ->
        DropdownMenuItem(
          text = { Text(opcion) },
          onClick = {
            onSeleccionChange(opcion)
            expanded = false
          }
        )
      }
    }
  }
}