package it.mapo.indie.collapsingepoxyitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.mapo.indie.collapsingepoxyitem.databinding.ActivityGamesListBinding

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