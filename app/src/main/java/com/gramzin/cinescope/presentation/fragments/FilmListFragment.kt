package com.gramzin.cinescope.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gramzin.cinescope.R
import com.gramzin.cinescope.appComponent
import com.gramzin.cinescope.databinding.FilmListFragmentBinding
import com.gramzin.cinescope.presentation.fragments.adapter.ListLoadStateAdapter
import com.gramzin.cinescope.presentation.fragments.adapter.TopFilmsAdapter
import com.gramzin.cinescope.presentation.fragments.viewModel.FilmListViewModel
import com.gramzin.cinescope.presentation.fragments.viewModel.factory.FilmListViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmListFragment : Fragment() {
    private lateinit var binding: FilmListFragmentBinding
    private val viewModel: FilmListViewModel by viewModels{
        factory.create(requireArguments().getInt(CATEGORY_KEY))
    }
    @Inject
    lateinit var factory: FilmListViewModelFactory.Factory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilmListFragmentBinding.inflate(inflater, container, false)

        val categoryName = when (requireArguments().getInt(CATEGORY_KEY)) {
            MainFilmCategories.TOP_POPULAR.ordinal -> getString(R.string.top_popular)
            MainFilmCategories.TOP_BEST.ordinal -> getString(R.string.top_best)
            else -> getString(R.string.top_await)
        }
        binding.categoryName.text = categoryName
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagingAdapter = TopFilmsAdapter()
        val newAdapter = pagingAdapter.withLoadStateHeaderAndFooter(
            ListLoadStateAdapter(pagingAdapter::retry), ListLoadStateAdapter(pagingAdapter::retry))
        val rcView = binding.rcView
        rcView.adapter = newAdapter
        rcView.layoutManager = LinearLayoutManager(requireContext())
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filmFlow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    companion object{
        const val CATEGORY_KEY = "CATEGORY_KEY"
    }

    enum class MainFilmCategories {
        TOP_POPULAR,
        TOP_BEST,
        TOP_AWAIT
    }
}