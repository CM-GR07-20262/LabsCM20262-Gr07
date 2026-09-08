package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.screens

import android.content.res.Configuration
import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


@Composable
fun PersonalDataScreen(
  //onSiguienteClick: () -> Unit
) {

  Scaffold(
    topBar = { TopBar() }

  ) { paddingValues ->
    Content(paddingValues, {})
  }
}

@Preview
@Composable
private fun PreviewNameInput() {
  NameInput("Nombres", "", Modifier.fillMaxSize(), {})
}

@Composable
fun NameInput(
  label: String,
  value: String,
  modifier: Modifier = Modifier,
  onValueChange: (String) -> Unit,  // "recibe un String y no devuelve nada"
) {
  TextField(
    label = { Text(label) },
    value = value,
    modifier = modifier.fillMaxWidth(),
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
fun FechaNacimientoSelector(
  fechaNacimiento: String,
  onFechaClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  OutlinedTextField(
    value = fechaNacimiento,
    onValueChange = {},
    label = { Text("Fecha de nacimiento *") },
    modifier = modifier
      .fillMaxWidth()
      .clickable { onFechaClick() },
    enabled = false,    // Desabilitamos la escritura directa para obligar a utilizar el Picker
    colors = OutlinedTextFieldDefaults.colors(
      disabledTextColor = MaterialTheme.colorScheme.onSurface,
      disabledBorderColor = MaterialTheme.colorScheme.outline,
      disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
  paddingValues: PaddingValues,
  onSiguienteClick: () -> Unit
) {
  // Orientación
  val configuration = LocalConfiguration.current
  val esHorizontal = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

  // Alineación
  val alineacionHorizontal = if (esHorizontal) Alignment.CenterHorizontally else Alignment.Start
  val anchoFraccion = if (esHorizontal) 0.7f else 1f // Reduce form 70%

  // Variables contenedoras
  var nombres by rememberSaveable { mutableStateOf("") }
  var apellidos by rememberSaveable { mutableStateOf("") }
  var fechaNacimiento by rememberSaveable { mutableStateOf("") }
  var sexo by rememberSaveable { mutableStateOf("") }
  var gradoEscolaridad by rememberSaveable { mutableStateOf("") }

  // Estados para el DatePicker
  var mostrarDatePicker by rememberSaveable { mutableStateOf(false) }
  val datePickerState = rememberDatePickerState()

  Box(
    Modifier
      .padding(paddingValues)
      .fillMaxSize(),
    contentAlignment = if (esHorizontal) Alignment.Center else Alignment.TopStart
  ) {
    Column(
      Modifier
        .padding(16.dp)
        .fillMaxWidth(anchoFraccion)
        .verticalScroll(rememberScrollState()),
      horizontalAlignment = alineacionHorizontal,
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      if (esHorizontal) {
        NameAndSurnameInputHorizontal(
          nombres, apellidos,
          onChangeName = { nombres = it },
          onChangeLastName = { apellidos = it }
        )
      } else {
        NameAndSurnameInputVertical(
          nombres, apellidos,
          onNameChange = { nombres = it },
          onLastNameChange = { apellidos = it },
        )
      }
      SexoSelector(sexo) { sexo = it }
      FechaNacimientoSelector(
        fechaNacimiento = fechaNacimiento,
        onFechaClick = { mostrarDatePicker = true }
      )
      GradoEscolaridadSelector(gradoEscolaridad) { gradoEscolaridad = it }

      Spacer(modifier = Modifier.height(24.dp))

      Button(
        onClick = onSiguienteClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = nombres.isNotBlank() && apellidos.isNotBlank() && fechaNacimiento.isNotBlank()
      ) {
        Text("Siguiente")
      }
    }

    // Lógica para diálogo flotante (Se visualiza solo si mostrarDatePicker es true)
    if (mostrarDatePicker) {
      DatePickerDialog(
        onDismissRequest = { mostrarDatePicker = false },
        confirmButton = {
          TextButton(onClick = {
            val dateMillis = datePickerState.selectedDateMillis
            if (dateMillis != null) {
              // Formatear fecha
              val formatter = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
              formatter.timeZone = TimeZone.getTimeZone("UTC")
              fechaNacimiento = formatter.format(Date(dateMillis))
            }
            mostrarDatePicker = false // Oculta selector
          }) {
            Text("Aceptar")
          }
        },
        dismissButton = {
          TextButton(onClick = { mostrarDatePicker = false }) {
            Text("Cancelar")
          }
        }
      ) {
        DatePicker(state = datePickerState)
      }
    }
  }
}

@Composable
private fun NameAndSurnameInputVertical(
  nombres: String,
  apellidos: String,
  onNameChange: (String) -> Unit,
  onLastNameChange: (String) -> Unit
) {

  NameInput(
    label = "Nombres *",
    value = nombres,
    onValueChange = onNameChange,
  )

  NameInput(
    label = "Apellidos *",
    value = apellidos,
    onValueChange = onLastNameChange
  )
}

@Composable
private fun NameAndSurnameInputHorizontal(
  nombres: String,
  apellidos: String,
  onChangeName: (String) -> Unit,
  onChangeLastName: (String) -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    NameInput(
      label = "Nombres *",
      value = nombres,
      modifier = Modifier.weight(1f),
      onValueChange = onChangeName
    )

    NameInput(
      label = "Apellidos *",
      value = apellidos,
      modifier = Modifier.weight(1f),
      onValueChange = onChangeLastName
    )
  }
}