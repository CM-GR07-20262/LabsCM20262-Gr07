package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20262.lab1.R
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata.FechaNacimientoSelector
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata.GradoEscolaridadSelector
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata.NameAndSurnameInputHorizontal
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata.NameAndSurnameInputVertical
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata.SexoSelector
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata.TopBar


@Composable
fun PersonalDataScreen(
  navigateNext: () -> Unit
) {
  Scaffold(
    topBar = { TopBar() }
  ) { paddingValues ->
    Content(paddingValues, navigateNext)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
  paddingValues: PaddingValues,
  navigateNext: () -> Unit
) {

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


  val onSiguienteClick = {
    val informationText =
      buildLogPersonalInformation(nombres, apellidos, sexo, fechaNacimiento, gradoEscolaridad)
    Log.i("Información personal", informationText)
  }

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
        onFechaClick = { mostrarDatePicker = true },
        mostrarDatePicker = mostrarDatePicker,
        datePickerState = datePickerState,
        onCloseDatePicker = { mostrarDatePicker = false },
        onSetFechaDeNacimiento = { fechaNacimiento = it },
      )
      GradoEscolaridadSelector(gradoEscolaridad) { gradoEscolaridad = it }
      Text(
        stringResource(R.string.los_campos_marcados_con_son_obligatorios),
        style = MaterialTheme.typography.labelSmall
      )
      Spacer(modifier = Modifier.height(24.dp))


      Button(
        onClick = {
          onSiguienteClick()
          navigateNext()
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = nombres.isNotBlank() && apellidos.isNotBlank() && fechaNacimiento.isNotBlank()
      ) {
        Text(stringResource(R.string.next))
      }
    }
  }
}

private fun buildLogPersonalInformation(
  nombres: String,
  apellidos: String,
  sexo: String,
  fechaNacimiento: String,
  gradoEscolaridad: String
): String {
  val stringBuilder = StringBuilder()
  stringBuilder.append("Información personal:\n")
  stringBuilder.append("${nombres.trim()} ${apellidos.trim()}")

  if (sexo.isNotBlank())
    stringBuilder.append("\n$sexo")

  stringBuilder.append("\nNació el $fechaNacimiento")

  if (gradoEscolaridad.isNotBlank())
    stringBuilder.append("\n$gradoEscolaridad")

  return stringBuilder.toString()
}

