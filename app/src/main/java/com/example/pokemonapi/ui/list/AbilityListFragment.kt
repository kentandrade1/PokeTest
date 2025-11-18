package com.example.pokemonapi.ui.list

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapi.R
import com.example.pokemonapi.ui.detail.AbilityDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AbilityListFragment : Fragment() {

    private val viewModel: AbilityListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_ability_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val selector = view.findViewById<Spinner>(R.id.spinnerLimit)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerAbilities)
        val prevBtn = view.findViewById<Button>(R.id.prevBtn)
        val nextBtn = view.findViewById<Button>(R.id.nextBtn)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AbilityAdapter { ability ->
            parentFragmentManager.commit {
                replace(R.id.fragmentContainer, AbilityDetailFragment.newInstance(ability.id))
                addToBackStack(null)
            }
        }
        recycler.adapter = adapter

        selector.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listOf(10, 25, 100))

        selector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View?, pos: Int, id: Long) {
                viewModel.setLimit(parent.getItemAtPosition(pos) as Int)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        nextBtn.setOnClickListener { viewModel.nextPage() }
        prevBtn.setOnClickListener { viewModel.prevPage() }

        lifecycleScope.launchWhenStarted {
            viewModel.abilities.collectLatest {
                adapter.submitList(it)
            }
        }
    }
}
