package com.hafiz.sportworld

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hafiz.sportworld.core.domain.model.SportWold
import com.hafiz.sportworld.core.domain.usecase.SportUseCase
import com.hafiz.sportworld.detail.DetailSportViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
class DetailSportViewModelTest {
    private lateinit var viewModel: DetailSportViewModel
    @Mock
    private lateinit var useCase: SportUseCase
    @Mock
    private lateinit var observer: Observer<SportWold>
    @Mock
    private lateinit var data: SportWold

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailSportViewModel(useCase)
    }

    @Test
    fun `should get detail sportworld from sportworld model`() {
        val dummysport = data
        val sport = MutableLiveData<SportWold>()
        sport.value = dummysport
        suspend {
            Mockito.`when`(useCase.getSportWoldList())
            verify(observer).onChanged(dummysport)
        }
    }
}