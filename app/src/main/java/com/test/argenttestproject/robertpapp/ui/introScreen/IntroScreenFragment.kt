package com.test.argenttestproject.robertpapp.ui.introScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.test.argenttestproject.R
import com.test.argenttestproject.databinding.IntroScreenFragmentBinding
import com.test.argenttestproject.robertpapp.ui.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.androidx.viewmodel.ext.android.viewModel


class IntroScreenFragment : BaseFragment() {

    val introScreenViewModel: IntroScreenViewModelImpl by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return IntroScreenFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = introScreenViewModel
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introScreenViewModel.navigateToAccessTokensEvents.subscribeBy(
            onNext = {
                findNavController().navigate(R.id.actionToErcTwentyScreen)
            }
        ).disposeOnDestroy()
    }
}
