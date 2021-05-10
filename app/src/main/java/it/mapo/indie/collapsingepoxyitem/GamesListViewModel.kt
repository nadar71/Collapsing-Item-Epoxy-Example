package it.mapo.indie.collapsingepoxyitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.mapo.indie.collapsingepoxyitem.model.*

class GamesListViewModel : ViewModel() {
    private val _gamesLiveData = MutableLiveData<Container>()
    val gamesLiveData: LiveData<Container>
        get() = _gamesLiveData

    private val onGenreExpanded: OnGenreExpanded = { genre: Genre ->
        val oldContainer = _gamesLiveData.value
        if (oldContainer != null) {
            val newGenres = oldContainer.genres.map {
                if (it.genre.id == genre.id) {
                    it.copy(genre = it.genre.copy(isExpanded = !genre.isExpanded))
                } else {
                    it
                }
            }

            _gamesLiveData.value = oldContainer.copy(genres = newGenres)
        }
    }


    init {
        _gamesLiveData.value = Container(
            listOf(
                GamesPerGenre(
                    Genre(name = "Action RPG Games"),
                    listOf(
                        Game("Dark Souls"),
                        Game("Diablo"),
                        Game("Bloodborne"),
                        Game("Sekiro")
                    )
                ),
                GamesPerGenre(
                    Genre(name = "FPS Games"),
                    listOf(
                        Game("Doom"),
                        Game("Borderlands"),
                        Game("Battlefield"),
                        Game("Call of Duty")
                    )
                )
            ),
            onGenreExpanded
        )
    }
}