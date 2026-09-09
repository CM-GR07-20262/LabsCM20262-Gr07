package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradoEscolaridadSelector(seleccionado: String, onSeleccionChange: (String) -> Unit) {
  val opciones = listOf(
    stringResource(R.string.primary),
    stringResource(R.string.secondary),
    stringResource(R.string.university),
    stringResource(R.string.other)
  )
  var expanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = !expanded }
  ) {
    TextField(
      value = seleccionado,
      onValueChange = {},
      readOnly = true,
      label = { Text(stringResource(R.string.school_level)) },
      modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
        .fillMaxWidth(),
      leadingIcon = {
        Icon(
          painterResource(R.drawable.school),
          "School icon",
          tint = MaterialTheme.colorScheme.primary
        )
      }
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

@Preview
@Composable
private fun PreviewGradoEscolaridadSelector() {
  GradoEscolaridadSelector("") { }
}