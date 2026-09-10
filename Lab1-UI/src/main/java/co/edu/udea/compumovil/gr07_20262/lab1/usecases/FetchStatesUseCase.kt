package co.edu.udea.compumovil.gr07_20262.lab1.usecases

import android.util.Log
import co.edu.udea.compumovil.gr07_20262.lab1.repository.CountriesNowApi
import co.edu.udea.compumovil.gr07_20262.lab1.repository.dto.CountryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchStatesUseCase(private val repository: CountriesNowApi) {

  suspend fun execute(country: String): List<String> = withContext(Dispatchers.IO) {
    try {
      val request = CountryRequest(country)
      val response = repository.getStates(request)
      if (response.error) {
        Log.e("FetchStatesUseCase", "error cargando los departamentos")
        return@withContext emptyList()
      }
      return@withContext response.data.states.map { it.name }
    } catch (e: Exception) {
      Log.e("FetchStatesUseCase", "Excepción al cargar departamentos: ${e.message}")
      return@withContext emptyList()
    }
  }
}
