package com.sonne.computation.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sonne.computation.R
import com.sonne.computation.databinding.FragmentGameBinding
import com.sonne.computation.domian.entity.GameResult
import com.sonne.computation.domian.entity.Level

class GameFragment : Fragment() {

//    первый способ получить args - с помощью ленивой инициализации
    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
//        второй способ
//        val args = GameFragmentArgs.fromBundle(requireArguments())
        GameViewModelFactory(args.level, requireActivity().application)
    }

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        //переопределяем геттер у binding, с помощью элвис-оператора проверяем на null,
        // и если что, то бросаем исключение
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
    }

    private fun observeViewModel() {
//        оставлено для примера:
//        viewModel.question.observe(viewLifecycleOwner) {
//            for (i in 0 until tvOptions.size) {
//                tvOptions[i].text = it.options[i].toString()
//            }
//        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragment2ToGameFinishedFragment(gameResult)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}