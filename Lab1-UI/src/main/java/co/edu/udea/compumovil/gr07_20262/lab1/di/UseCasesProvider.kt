package co.edu.udea.compumovil.gr07_20262.lab1.di

import co.edu.udea.compumovil.gr07_20262.lab1.repository.CountriesNowApi
import co.edu.udea.compumovil.gr07_20262.lab1.usecases.FetchCitiesByStateUseCase
import co.edu.udea.compumovil.gr07_20262.lab1.usecases.FetchCitiesUseCase
import co.edu.udea.compumovil.gr07_20262.lab1.usecases.FetchCountriesUseCase
import co.edu.udea.compumovil.gr07_20262.lab1.usecases.FetchStatesUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UseCasesProvider {
  private var repository: CountriesNowApi

  constructor() {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://countriesnow.space/api/v0.1/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    this.repository = retrofit.create(CountriesNowApi::class.java)
  }

  fun fetchCuntriesProvider(): suspend () -> List<String> {
    val useCase = FetchCountriesUseCase(repository)
    return {
      useCase.execute()
    }
  }

  fun fetchCitiesProvider(): suspend (String) -> List<String> {
    val useCase = FetchCitiesUseCase(repository)
    return {
      useCase.execute(it)
    }
  }

  fun fetchCitiesByStateProvider(): suspend (String, String) -> List<String> {
    val useCase = FetchCitiesByStateUseCase(repository)
    return { country, state ->
      useCase.execute(country, state)
    }
  }

  fun fetchStatesProvider(): suspend (String) -> List<String> {
    val useCase = FetchStatesUseCase(repository)
    return {
      useCase.execute(it)
    }
  }
}
