package co.edu.udea.compumovil.gr07_20262.lab1.usecases

import android.util.Log
import co.edu.udea.compumovil.gr07_20262.lab1.repository.CountriesNowApi
import co.edu.udea.compumovil.gr07_20262.lab1.repository.dto.CountryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchCitiesUseCase(private val repo: CountriesNowApi) {

  suspend fun execute(country: String): List<String> = withContext(Dispatchers.IO) {
    val request = CountryRequest(country)

    val response = repo.getCities(request)

    if (response.error) {
      Log.e("FetchCitiesUseCase", "error cargando las ciudades")
      return@withContext emptyList()
    }

    return@withContext response.data
  }
}