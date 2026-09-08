package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R

@Composable
fun PhoneInput(phoneValue: String, onPhoneChange: (String) -> Unit) {
  TextField(
    value = phoneValue,
    onValueChange = onPhoneChange,
    label = { Text(stringResource(R.string.phone)) },
    leadingIcon = {
      Icon(
        painterResource(R.drawable.phone),
        "Phone icon",
        tint = MaterialTheme.colorScheme.primary
      )
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Phone
    )
  )
}


@Preview
@Composable
private fun PreviewPhoneInput() {
  PhoneInput("") { }
}