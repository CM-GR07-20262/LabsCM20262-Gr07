package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.Labs20262Gr07Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaNacimientoSelector(
  fechaNacimiento: String,
  onFechaClick: () -> Unit,
  modifier: Modifier = Modifier,
  mostrarDatePicker: Boolean,
  datePickerState: DatePickerState,
  onCloseDatePicker: () -> Unit,
  onSetFechaDeNacimiento: (String) -> Unit
) {
  TextField(
    value = fechaNacimiento,
    onValueChange = {},
    label = { Text(stringResource(R.string.brith_day)) },
    modifier = modifier
      .fillMaxWidth()
      .clickable { onFechaClick() },
    enabled = false,    // Deshabilitamos la escritura directa para obligar a utilizar el Picker
    colors = TextFieldDefaults.colors(
      disabledTextColor = MaterialTheme.colorScheme.onSurface,
      disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
    leadingIcon = {
      Icon(
        painterResource(R.drawable.calendar), "Calendar Icon",
        tint = MaterialTheme.colorScheme.primary
      )
    }
  )

  // Lógica para diálogo flotante (Se visualiza solo si mostrarDatePicker es true)
  if (mostrarDatePicker) {
    DatePickerDialog(
      onDismissRequest = onCloseDatePicker,
      confirmButton = {
        TextButton(onClick = {
          val dateMillis = datePickerState.selectedDateMillis
          if (dateMillis != null) {
            // Formatear fecha
            val formatter = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val formatedDate = formatter.format(Date(dateMillis))
            onSetFechaDeNacimiento(formatedDate)
          }
          onCloseDatePicker()
        }) {
          Text(stringResource(R.string.accept))
        }
      },
      dismissButton = {
        TextButton(onClick = onCloseDatePicker) {
          Text(stringResource(R.string.cancel))
        }
      }
    ) {
      DatePicker(state = datePickerState)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FechaNacimientoSelectorPreview() {
  Labs20262Gr07Theme {
    val datePickerState = rememberDatePickerState()
    FechaNacimientoSelector(
      fechaNacimiento = "",
      onFechaClick = {},
      mostrarDatePicker = false,
      datePickerState = datePickerState,
      onCloseDatePicker = {},
      onSetFechaDeNacimiento = {}
    )
  }
}

