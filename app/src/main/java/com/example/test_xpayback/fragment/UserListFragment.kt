package com.example.test_xpayback.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_xpayback.R
import com.example.test_xpayback.adapter.UserAdapter
import com.example.test_xpayback.apicall.RetrofitClient
import com.example.test_xpayback.apicall.UserRepository
import com.example.test_xpayback.databinding.FragmentUserListBinding
import com.example.test_xpayback.viewmodel.UserListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private lateinit var userRepository: UserRepository

    private val userListViewModel: UserListViewModel by activityViewModels {
        UserViewModelFactory(userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRepository = UserRepository(RetrofitClient.apiService)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = UserAdapter { userId ->
            val fragment = UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("userId", userId)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            userListViewModel.users.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
