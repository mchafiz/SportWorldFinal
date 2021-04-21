package com.hafiz.sportworld.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.hafiz.sportworld.R
import com.hafiz.sportworld.core.domain.model.SportWold
import com.hafiz.sportworld.databinding.ActivityDetalSportBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailSportActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailSportViewModel: DetailSportViewModel by viewModel()

    private lateinit var binding: ActivityDetalSportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalSportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val detailSport = intent.getParcelableExtra<SportWold>(EXTRA_DATA)
        showDetailSport(detailSport)
    }

    private fun showDetailSport(sport: SportWold?) {
        sport?.let {
            supportActionBar?.title = sport.strSport
            binding.content.tvDetailDescription.text = sport.strSportDescription
            Glide.with(this@DetailSportActivity)
                .load(sport.strSportThumb)
                .into(binding.ivDetailImage)

            var statusFavorite = sport.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailSportViewModel.setFavSport(sport, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}