package it.mapo.indie.collapsingepoxyitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.mapo.indie.collapsingepoxyitem.databinding.ActivityGamesListBinding
import it.mapo.indie.collapsingepoxyitem.model.Genre
import it.mapo.indie.collapsingepoxyitem.model.OnGenreExpanded
import java.util.Collections.copy
import it.mapo.indie.collapsingepoxyitem.model.*


class GamesListActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(GamesListViewModel::class.java)
    }

    private val gamesListController = GamesListController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityGamesListBinding>(this, R.layout.activity_games_list)
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = gamesListController.adapter

        viewModel.gamesLiveData.observe(this, Observer { container ->
            gamesListController.setData(container)
        })
    }


}

/*
NB :
The data in list are set when GamesListViewModel is instantiated here in GamesListActivity
using init


The list of genres and games are loaded
when GamesListViewModel is instantiated here in GamesListActivity using init
and _gamesLiveData.value = Container are set to initial value too,
generating a Container object with params : list of GamesPerGenre and onGenreExpanded (implemented in vm).

This 1st changes in _gamesLiveData activate observer in GamesListActivity which through GamesListController instance
set the new data to model :
gamesListController.setData(container)


_gamesLiveData now wait for the next changes (i.e. onclick to expand genre), which happen at the next onGenreExpanded call.

Pressing genre item uses launch onHeaderExpandedByClick from
android:onClick="@{onHeaderExpandedByClick}">

onHeaderExpandedByClick is implemented in GamesListController, here :
    onHeaderExpandedByClick { model, _, _, _ ->
        container.onGenreExpanded(model.genre())
    }

This trigger onGenreExpanded again .

onGenreExpanded scan in the container genre list which genre has been clicked,
then modify its isExpandable = !genre.isExpanded (open/close)
and pass the new container to _gamesLiveData.value = oldContainer.copy(genres = newGenres)

This activate the observer in GamesListActivity which through GamesListController instance
set the new data to model :

gamesListController.setData(container)

which launch requestModelBuild() (--> see TypedEpoxyController object in Epoxy lib) and refresh models

 */