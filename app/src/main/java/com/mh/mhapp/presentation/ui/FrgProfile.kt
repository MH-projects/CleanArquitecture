package com.mh.mhapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mh.mhapp.R
import com.mh.mhapp.databinding.FrgProfileBinding
import com.mh.mhapp.presentation.vm.DashboardViewModel

class FrgProfile : Fragment() {

    private lateinit var binding: FrgProfileBinding

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // viewModel = DashboardViewModel()
//        viewModel = requireActivity().run {
//            ViewModelProvider(this)[DashboardViewModel::class.java]
//        }
        binding = FrgProfileBinding.inflate(inflater, container, false)
        // binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = requireActivity().run {
            ViewModelProvider(this)[DashboardViewModel::class.java]
        }

        viewModel.infoUser.observe(viewLifecycleOwner) {
            if (it.status) {
                view.findViewById<TextView>(R.id.tvFullName).text = HtmlCompat.fromHtml(
                    getString(
                        R.string.full_name,
                        it.user.name,
                        it.user.lastname,
                        it.user.secondLastName
                    ),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                view.findViewById<TextView>(R.id.tvBirthday).text = HtmlCompat.fromHtml(
                    getString(R.string.birthday, it.user.birthday),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                view.findViewById<TextView>(R.id.tvEmail).text = HtmlCompat.fromHtml(
                    getString(R.string.email, it.user.email),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                view.findViewById<TextView>(R.id.tvGender).text = HtmlCompat.fromHtml(
                    getString(R.string.gender, it.user.gender),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                view.findViewById<TextView>(R.id.tvState).text = HtmlCompat.fromHtml(
                    getString(R.string.state, it.user.state),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                view.findViewById<TextView>(R.id.tvPhone).text = HtmlCompat.fromHtml(
                    getString(R.string.phone, it.user.phone),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            }
        }
    }
}
