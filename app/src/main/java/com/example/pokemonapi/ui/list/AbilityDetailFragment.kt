package com.example.pokemonapi.ui.detail

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemonapi.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AbilityDetailFragment : Fragment() {

    private val vm: AbilityDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_ability_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Botón volver
        val btnBack = view.findViewById<View>(R.id.btnBack)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val name = view.findViewById<TextView>(R.id.txtAbilityName)
        val effect = view.findViewById<TextView>(R.id.txtEffect)
        val gen = view.findViewById<TextView>(R.id.txtGeneration)
        val shortEffect = view.findViewById<TextView>(R.id.txtShortEffect)
        val txtFlavor = view.findViewById<TextView>(R.id.txtFlavor)
        val txtPokemonList = view.findViewById<TextView>(R.id.txtPokemonList)

        val loader = view.findViewById<View>(R.id.loader)
        val txtError = view.findViewById<TextView>(R.id.txtError)

        val id = requireArguments().getInt("id")
        vm.load(id)

        lifecycleScope.launchWhenStarted {

            vm.loading.collectLatest { loading ->
                loader.visibility = if (loading) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launchWhenStarted {
            vm.error.collectLatest { err ->
                txtError.visibility = if (err != null) View.VISIBLE else View.GONE
                txtError.text = err
            }
        }

        lifecycleScope.launchWhenStarted {
            vm.data.collectLatest { ab ->
                ab?.let {

                    name.text = it.name.capitalize()

                    val effectEn = it.effect_entries.firstOrNull { e -> e.language.name == "en" }
                    effect.text = effectEn?.effect ?: "No effect available"
                    shortEffect.text = effectEn?.short_effect ?: "No short effect"

                    gen.text = it.generation.name.replace("-", " ").uppercase()

                    val flavorEn = it.flavor_text_entries
                        .filter { f -> f.language.name == "en" }
                        .lastOrNull()

                    txtFlavor.text = flavorEn?.flavor_text?.replace("\n", " ")
                        ?: "No Pokédex description"

                    txtPokemonList.text = it.pokemon.joinToString("\n") { p ->
                        "- ${p.pokemon.name.capitalize()} ${if (p.is_hidden) "(Hidden)" else ""}"
                    }
                }
            }
        }
    }


    companion object {
        fun newInstance(id: Int) = AbilityDetailFragment().apply {
            arguments = Bundle().apply { putInt("id", id) }
        }
    }
}
