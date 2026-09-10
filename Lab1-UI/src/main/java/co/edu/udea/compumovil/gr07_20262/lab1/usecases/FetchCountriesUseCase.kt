package co.edu.udea.compumovil.gr07_20262.lab1.usecases

import android.util.Log
import co.edu.udea.compumovil.gr07_20262.lab1.repository.CountriesNowApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchCountriesUseCase(private val repository: CountriesNowApi) {

  suspend fun execute(): List<String> = withContext(Dispatchers.IO) {
    try {
      val response = repository.getCountries()
      if (response.error) {
        Log.e("FetchCountriesUseCase", "error cargando los paises")
        return@withContext emptyList()
      }
      return@withContext response.data.map { it.name }
    } catch (e: Exception) {
      Log.e("FetchCountriesUseCase", "Excepción al cargar países: ${e.message}")
      return@withContext emptyList()
    }
  }
}