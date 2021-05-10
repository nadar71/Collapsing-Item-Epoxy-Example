package it.mapo.indie.collapsingepoxyitem

import com.airbnb.epoxy.TypedEpoxyController
import it.mapo.indie.collapsingepoxyitem.model.Container

class GamesListController: TypedEpoxyController<Container>() {
    override fun buildModels(container: Container?) {
        container?.genres?.forEach {
            header {
                id(it.genre.id)
                genre(it.genre)

                onHeaderExpandedByClick { model, _, _, _ ->
                    container.onGenreExpanded(model.genre())
                }
            }
            if (it.genre.isExpanded) {
                it.games.forEach { game ->
                    onelineWithIcon {
                        id(game.id)
                        game(game)
                    }
                }
            }
        }
    }
}