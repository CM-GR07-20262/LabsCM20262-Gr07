package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.screens

import android.graphics.Paint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20262.lab1.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.RadioButton


@Composable
fun PersonalDataScreen() {

  Scaffold(
    topBar = { TopBar() }

  ) { paddingValues ->
    Content(paddingValues)
  }
}

@Preview
@Composable
private fun PreviewNameInput() {
  NameInput("Nombres", "", {})
}

@Composable
fun NameInput(
  label: String,
  value: String,
  onValueChange: (String) -> Unit  // "recibe un String y no devuelve nada"
) {
  TextField(
    label = { Text(label) },
    value = value,
    onValueChange = onValueChange,
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Text,
      capitalization = KeyboardCapitalization.Words, //Poner primera letra en mayuscula
      autoCorrectEnabled = false //Quitar el autocorrector
    ),
    leadingIcon = {
      Icon(
        painter = painterResource(R.drawable.user),
        contentDescription = "Icono de usuario",
        tint = MaterialTheme.colorScheme.primary
      )
    }
  )
}

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

@Preview
@Composable
fun PreviewTopBar() {
  TopBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
  TopAppBar(
    title = { Text(stringResource(R.string.app_title)) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer,
      titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )
  )
}

@Composable
fun Content(paddingValues: PaddingValues) {
  var nombres by remember { mutableStateOf("") }
  var apellidos by remember { mutableStateOf("") }
  var sexo by remember { mutableStateOf("") }
  var gradoEscolaridad by remember { mutableStateOf("") }

  Box(Modifier.padding(paddingValues)) {
    Column(
      Modifier.padding(16.dp),
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      NameInput("Nombres *", nombres) { nombres = it }
      NameInput("Apellidos *", apellidos) { apellidos = it }
      SexoSelector(sexo) { sexo = it }
      GradoEscolaridadSelector(gradoEscolaridad) { gradoEscolaridad = it }
    }
  }
}