package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R
import androidx.compose.ui.text.input.ImeAction

@Composable
fun PhoneInput(
  phoneValue: String,
  invalidPhoneNumber: () -> Boolean,
  onPhoneChange: (String) -> Unit
) {
  TextField(
    value = phoneValue,
    onValueChange = onPhoneChange,
    label = { Text(stringResource(R.string.phone)) },
    maxLines = 1,
    isError = invalidPhoneNumber(),
    singleLine = true,
    leadingIcon = {
      Icon(
        painterResource(R.drawable.phone),
        "Phone icon",
        tint = MaterialTheme.colorScheme.primary
      )
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Phone,
      imeAction = ImeAction.Next
    ),
    modifier = Modifier.fillMaxWidth(),
    supportingText = {
      if (invalidPhoneNumber()) {
        Text(stringResource(R.string.invalid_phone_numer_message))
      }
    }
  )
}


@Preview
@Composable
private fun PreviewPhoneInput() {
  PhoneInput("", { false }) { }
}

@Preview(showBackground = true)
@Composable
private fun PreviewInvalidPhoneInput() {
  PhoneInput("", { true }) { }
}