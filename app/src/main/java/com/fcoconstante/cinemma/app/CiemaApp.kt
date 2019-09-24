package com.fcoconstante.cinemma.app

import android.app.Application
import com.fcoconstante.cinemma.data.db.AppDatabase
import com.fcoconstante.cinemma.data.network.DBMovieApi
import com.fcoconstante.cinemma.data.network.NetworkConnectionInterceptor
import com.fcoconstante.cinemma.data.preferences.PreferenceProvider
import com.fcoconstante.cinemma.data.repositories.MoviesRepository
import com.fcoconstante.cinemma.ui.movies.MoviesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CiemaApp: Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@CiemaApp))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { DBMovieApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { MoviesRepository(instance(),instance(),instance()) }
        bind() from provider { MoviesViewModelFactory(instance()) }

    }

}