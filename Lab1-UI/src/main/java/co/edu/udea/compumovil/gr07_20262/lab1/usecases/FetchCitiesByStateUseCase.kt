package co.edu.udea.compumovil.gr07_20262.lab1.usecases

import android.util.Log
import co.edu.udea.compumovil.gr07_20262.lab1.repository.CountriesNowApi
import co.edu.udea.compumovil.gr07_20262.lab1.repository.dto.StateCitiesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchCitiesByStateUseCase(private val repo: CountriesNowApi) {

  suspend fun execute(country: String, state: String): List<String> = withContext(Dispatchers.IO) {
    try {
      val request = StateCitiesRequest(country, state)
      val response = repo.getCitiesByState(request)

      if (response.error) {
        Log.e("FetchCitiesByState", "error cargando las ciudades del departamento")
        return@withContext emptyList()
      }

      return@withContext response.data
    } catch (e: Exception) {
      Log.e("FetchCitiesByState", "Excepción al cargar ciudades: ${e.message}")
      return@withContext emptyList()
    }
  }
}