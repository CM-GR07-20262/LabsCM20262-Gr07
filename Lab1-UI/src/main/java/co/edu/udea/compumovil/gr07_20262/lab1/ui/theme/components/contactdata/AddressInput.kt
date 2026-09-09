package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.Labs20262Gr07Theme

@Composable
fun AddressInput(addressValue: String, onAddressChange: (String) -> Unit, validAddress: (String) -> Boolean) {



  TextField(
    value = addressValue,
    onValueChange = onAddressChange,
    isError = validAddress(addressValue),
    leadingIcon = {
      Icon(
        painterResource(R.drawable.address),
        "Address icon",
        tint = MaterialTheme.colorScheme.primary
      )
    },
    maxLines = 1,
    singleLine = true,
    label = { Text(stringResource(R.string.address)) }
  )
}


