@file:Suppress("Annotator", "Annotator", "Annotator", "Annotator", "Annotator")

package com.hafiz.sportworld.favorits

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafiz.sportworld.core.SportAdapter
import com.hafiz.sportworld.detail.DetailSportActivity
import com.hafiz.sportworld.favorits.databinding.FavoriteActivitiyBinding
import com.hafiz.sportworld.favorits.di.favoritsModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules




class FavoritsActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoritsViewModel by viewModel()
    private var _binding: FavoriteActivitiyBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FavoriteActivitiyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorits Sport"

        loadKoinModules(favoritsModule)
        val sportAdapter = SportAdapter()
        sportAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailSportActivity::class.java)
            intent.putExtra(DetailSportActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteSport.observe(this, { sport ->
            sportAdapter.setData(sport)
            binding.viewEmpty.root.visibility = if(sport.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvSport) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = sportAdapter
        }
    }
}

