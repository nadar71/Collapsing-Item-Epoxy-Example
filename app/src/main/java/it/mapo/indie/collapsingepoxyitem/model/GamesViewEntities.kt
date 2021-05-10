package it.mapo.indie.collapsingepoxyitem.model

import java.util.concurrent.atomic.AtomicInteger

private val globalId = AtomicInteger(1)

typealias OnGenreExpanded = (genre: Genre) -> Unit

data class Container(
    val genres: List<GamesPerGenre>,
    val onGenreExpanded: OnGenreExpanded // function type which act on genre object as parameter
)

data class GamesPerGenre(
    val genre: Genre,
    val games: List<Game>
)

data class Genre(
    val name: String,
    val isExpanded: Boolean = false,
    val id: Int = globalId.getAndIncrement()
)

data class Game(
    val name: String,
    val id: Int = globalId.getAndIncrement()
)