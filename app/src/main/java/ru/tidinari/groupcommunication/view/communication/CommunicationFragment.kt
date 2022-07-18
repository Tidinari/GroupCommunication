package ru.tidinari.groupcommunication.view.communication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.tidinari.groupcommunication.databinding.FragmentCommunicationBinding
import ru.tidinari.groupcommunication.viewmodels.communication.CommunicationViewModel

class CommunicationFragment : Fragment() {

    private var _binding: FragmentCommunicationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val communicationViewModel =
            ViewModelProvider(this).get(CommunicationViewModel::class.java)

        _binding = FragmentCommunicationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        communicationViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}