package co.edu.udea.compumovil.gr07_20262.lab1.repository

import co.edu.udea.compumovil.gr07_20262.lab1.repository.dto.CitiesResponse
import co.edu.udea.compumovil.gr07_20262.lab1.repository.dto.CountryRequest
import co.edu.udea.compumovil.gr07_20262.lab1.repository.dto.CountryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CountriesNowApi {

  @GET("countries/iso")
  suspend fun getCountries(): CountryResponse

  @POST("countries/cities")
  suspend fun getCities(@Body body: CountryRequest): CitiesResponse

}