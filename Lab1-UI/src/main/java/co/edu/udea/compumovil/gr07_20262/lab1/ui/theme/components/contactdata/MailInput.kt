package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import android.inputmethodservice.Keyboard
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
fun MailInput(mailValue: String, validMail: () -> Boolean, onMailChange: (String) -> Unit) {
  TextField(
    value = mailValue,
    onValueChange = onMailChange,
    label = { Text(stringResource(R.string.mail)) },
    maxLines = 1,
    isError = validMail(),
    singleLine = true,
    leadingIcon = {
      Icon(
        painterResource(R.drawable.mail),
        "Mail icon",
        tint = MaterialTheme.colorScheme.primary
      )
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Email
    )
  )
}

@Preview
@Composable
private fun PreviewMailInput() {
  MailInput("", { true }) { }
}