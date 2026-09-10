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
fun MailInput(mailValue: String, invalidEmail: () -> Boolean, onMailChange: (String) -> Unit) {
  TextField(
    value = mailValue,
    onValueChange = onMailChange,
    label = { Text(stringResource(R.string.mail)) },
    maxLines = 1,
    isError = invalidEmail(),
    modifier = Modifier.fillMaxWidth(),
    singleLine = true,
    leadingIcon = {
      Icon(
        painterResource(R.drawable.mail),
        "Mail icon",
        tint = MaterialTheme.colorScheme.primary
      )
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Email,
      imeAction = ImeAction.Next
    ),
    supportingText = {
      if (invalidEmail()) {
        Text(
          stringResource(R.string.invalid_mail_message),
          color = MaterialTheme.colorScheme.error
        )
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMailInput() {
  MailInput("", { true }) { }
}

@Preview(showBackground = true)
@Composable
private fun PreviewValidMailInpu() {
  MailInput("", { false }) { }
}