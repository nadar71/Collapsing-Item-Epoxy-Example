package it.mapo.indie.collapsingepoxyitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.mapo.indie.collapsingepoxyitem.model.*

class GamesListViewModel : ViewModel() {
    val gamesLiveData: LiveData<Container>
        get() = _liveData
    private val _liveData = MutableLiveData<Container>()

    private val onGenreExpanded: OnGenreExpanded = { genre: Genre ->
        val oldContainer = _liveData.value
        if (oldContainer != null) {
            val newGenres = oldContainer.genres.map {
                if (it.genre.id == genre.id) {
                    it.copy(genre = it.genre.copy(isExpanded = !genre.isExpanded))
                } else {
                    it
                }
            }

            _liveData.value = oldContainer.copy(genres = newGenres)
        }
    }

    init {
        _liveData.value = Container(
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