package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20262.lab1.R
import co.edu.udea.compumovil.gr07_20262.lab1.di.UseCasesProvider
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.AddressInput
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.CitySelector
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.CountrySelector
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.LoadingDialog
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.MailInput
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.PhoneInput
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.TopBar

@Composable
fun ContactDataScreen() {
  val provider = remember { UseCasesProvider() }
  val fetchCities = remember { provider.fetchCitiesProvider() }
  val fetchCuntries = remember { provider.fetchCuntriesProvider() }

  var selectedCountry by remember { mutableStateOf("") }
  val onSelectCountry: (String) -> Unit = { country -> selectedCountry = country }

  var countries by remember { mutableStateOf<List<String>>(emptyList()) }
  var cities by remember { mutableStateOf<List<String>>(emptyList()) }

  var isLoadingCountries by remember { mutableStateOf(true) }
  var isLoadingCities by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    isLoadingCountries = true
    countries = fetchCuntries()
    isLoadingCountries = false
  }

  LaunchedEffect(selectedCountry) {
    isLoadingCities = true
    cities = if (selectedCountry.isNotEmpty()) {
      fetchCities(selectedCountry)
    } else {
      emptyList()
    }
    isLoadingCities = false
  }

  Scaffold(
    topBar = { TopBar() }
  ) {
    Content(
      paddingValues = it,
      cities = cities,
      onSelectCountry = onSelectCountry,
      countries = countries,
      selectedCountry = selectedCountry
    )
  }

  LoadingDialog(isLoading = isLoadingCountries || isLoadingCities)
}

@Composable
fun Content(
  paddingValues: PaddingValues,
  onSelectCountry: (String) -> Unit,
  countries: List<String>,
  cities: List<String>,
  selectedCountry: String
) {
  Column(Modifier.padding(paddingValues)) {


    var phone by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val onNext = {
      val logText = buildLogString(phone, address, mail, selectedCountry, city)
      Log.i("Información de contacto", logText)
    }

    val validAddress: (String) -> Boolean = {
      val addressRegex = Regex(
        """^(Calle|Cra?\.?|Carrera|Cl\.?|Transversal|Tv\.?|Diagonal|Dg\.?)\s+\d+[A-Za-z]?\s*#\s*\d+[A-Za-z]?\s*-\s*\d+$"""
      )
      address.isNotEmpty() && !address.matches(addressRegex)
    }

    Column(
      Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      PhoneInput(phone) { phone = it }
      MailInput(mail) { mail = it }
      CountrySelector(countries, onSelectCountry)
      CitySelector(cities) { city = it }
      AddressInput(address, onAddressChange = { address = it }, validAddress = validAddress)

      Button(
        { onNext() },
        enabled = phone.isNotBlank() && mail.isNotBlank() && selectedCountry.isNotBlank()
            && !validAddress(address)
      ) {
        Text(stringResource(R.string.next))
      }

    }

  }
}

private fun buildLogString(
  phone: String,
  address: String,
  mail: String,
  selectedCountry: String,
  city: String
): String {
  val stringBuilder = StringBuilder()

  stringBuilder.append("Información de contacto\n")
  stringBuilder.append("Teléfono: $phone\n")

  if (address.isNotEmpty())
    stringBuilder.append("Dirección: $address\n")

  stringBuilder.append("Email: $mail\n")
  stringBuilder.append("Pais: $selectedCountry\n")

  if (city.isNotEmpty())
    stringBuilder.append("Ciudad: $city\n")

  val logText = stringBuilder.toString()
  return logText
}

