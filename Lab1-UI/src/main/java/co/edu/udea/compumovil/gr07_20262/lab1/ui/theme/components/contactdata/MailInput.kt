package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R

@Composable
fun MailInput(mailValue: String, onMailChange: (String) -> Unit) {
  TextField(
    value = mailValue,
    onValueChange = onMailChange,
    label = { Text(stringResource(R.string.mail)) },
    leadingIcon = {
      Icon(painterResource(R.drawable.mail),
        "Mail icon",
        tint = MaterialTheme.colorScheme.primary)
    }
  )
}

@Preview
@Composable
private fun PreviewMailInput() {
  MailInput("") { }
}