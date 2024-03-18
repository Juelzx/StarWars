package julian.scholler.starwars.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import julian.scholler.starwars.characters.data.api.StarWarsService
import julian.scholler.starwars.characters.data.repository.CharactersRepository
import julian.scholler.starwars.start.view.StartViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://akabab.github.io/starwars-api/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideStarWarsService(retrofit: Retrofit): StarWarsService {
        return retrofit.create(StarWarsService::class.java)
    }

    @Singleton
    @Provides
    fun provideStarWarsRepository(starWarsService: StarWarsService): CharactersRepository {
        return CharactersRepository(starWarsService)
    }
}