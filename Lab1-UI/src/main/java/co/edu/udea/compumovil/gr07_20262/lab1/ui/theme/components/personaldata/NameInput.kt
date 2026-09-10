package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R
import androidx.compose.ui.text.input.ImeAction


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
      autoCorrectEnabled = false, //Quitar el autocorrector
      imeAction = ImeAction.Next
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
