package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


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
          Text("Aceptar")
        }
      },
      dismissButton = {
        TextButton(onClick = onCloseDatePicker) {
          Text("Cancelar")
        }
      }
    ) {
      DatePicker(state = datePickerState)
    }
  }
}

