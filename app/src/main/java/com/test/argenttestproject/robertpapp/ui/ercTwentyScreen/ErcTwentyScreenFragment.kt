package com.test.argenttestproject.robertpapp.ui.ercTwentyScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.test.argenttestproject.databinding.ErcTwentyScreenFragmentBinding
import kotlinx.android.synthetic.main.erc_twenty_screen_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ErcTwentyScreenFragment : Fragment() {

    val ercTwentyScreenViewModel: ErcTwentyScreenViewModelImpl by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ErcTwentyScreenFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = ercTwentyScreenViewModel
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeData()
    }

    fun observeData() {
        ercTwentyScreenViewModel.tokenValues.observe(this, {
            erc_twenty_screen_fragment_token_values.adapter =
                TokenValueResultAdapter( it)
        })
    }
}
