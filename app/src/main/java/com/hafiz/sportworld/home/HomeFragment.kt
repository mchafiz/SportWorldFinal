package com.hafiz.sportworld.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hafiz.sportworld.R
import com.hafiz.sportworld.core.SportAdapter
import com.hafiz.sportworld.core.data.Resource
import com.hafiz.sportworld.databinding.FragmentHomeBinding
import com.hafiz.sportworld.detail.DetailSportActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val sportAdapter = SportAdapter()
            sportAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailSportActivity::class.java)
                intent.putExtra(DetailSportActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.sport.observe(viewLifecycleOwner, { sport ->
                if (sport != null) {
                    when (sport) {

                        is Resource.Waiting -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Berhasil -> {
                            binding.progressBar.visibility = View.GONE
                            sportAdapter.setData(sport.data)
                        }
                        is Resource.Exception -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = sport.message ?: getString(R.string.Error)
                        }
                    }
                }
            })

            with(binding.rvSport) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = sportAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}