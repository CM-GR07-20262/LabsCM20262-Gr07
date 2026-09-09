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
fun AddressInput(
  addressValue: String,
  invalidAddress: () -> Boolean,
  onAddressChange: (String) -> Unit,
) {
  TextField(
    value = addressValue,
    onValueChange = onAddressChange,
    isError = invalidAddress(),
    leadingIcon = {
      Icon(
        painterResource(R.drawable.address),
        "Address icon",
        tint = MaterialTheme.colorScheme.primary
      )
    },
    maxLines = 1,
    singleLine = true,
    label = { Text(stringResource(R.string.address)) },
    supportingText = {
      if (invalidAddress()) {
        Text(stringResource(R.string.invalid_address_message))
      }
    }
  )
}


@Preview(showBackground = true)
@Composable
private fun PreviewInvalidAddressInput() {
  AddressInput("", { true }) { }
}


@Preview()
@Composable()
private fun PreviewAddressInput() {
  AddressInput("", { false }) { }
}
