package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelector(
  countries: List<String>, onSelectCountry: (String) -> Unit
) {
  var expanded by remember { mutableStateOf(false) }
  var filterValue by remember { mutableStateOf("") }

  ExposedDropdownMenuBox(
    expanded = expanded, onExpandedChange = { expanded = !expanded }) {
    TextField(
      value = filterValue,
      onValueChange = {
        filterValue = it
        expanded = true
      },
      label = { Text("Pais") },
      singleLine = true,
      maxLines = 1,
      leadingIcon = {
        Icon(
          painterResource(R.drawable.world), "World icon", tint = MaterialTheme.colorScheme.primary
        )
      },
      modifier = Modifier
        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true)
    )
    val filteredCountries = countries.filter {
      it.lowercase(getDefault())
        .contains(filterValue.lowercase(getDefault()))
    }

    ExposedDropdownMenu(
      expanded = expanded, onDismissRequest = { }) {

      filteredCountries.forEach {
        DropdownMenuItem(text = { Text(it) }, onClick = {
          filterValue = it
          onSelectCountry(it)
          expanded = false
        })
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCountrySelector() {
  Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    var country by remember { mutableStateOf("") }
    val onSelectCountry: (String) -> Unit = { country = it }
    val countries = listOf(
      "Colombia",
      "Argentina",
      "Brasil",
      "Chile",
      "Perú",
      "México",
      "Ecuador",
      "Bolivia",
      "Paraguay",
      "Uruguay",
      "Venezuela",
      "Panamá",
      "Costa Rica",
      "Nicaragua",
      "Honduras",
      "Guatemala",
      "El Salvador",
      "Cuba",
      "República Dominicana",
      "España",
      "Estados Unidos",
      "Canadá",
      "Francia",
      "Italia",
      "Alemania",
      "Portugal",
      "Reino Unido",
      "Japón",
      "China",
      "Corea del Sur",
      "Australia"
    )
    CountrySelector(
      countries = countries, onSelectCountry = onSelectCountry
    )
  }
}
