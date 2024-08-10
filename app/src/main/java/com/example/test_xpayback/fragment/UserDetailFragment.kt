package com.example.test_xpayback.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.test_xpayback.R
import com.example.test_xpayback.apicall.RetrofitClient
import com.example.test_xpayback.apicall.UserRepository
import com.example.test_xpayback.databinding.FragmentUserDetailBinding
import com.example.test_xpayback.viewmodel.UserDetailViewModel

class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserDetailViewModel by activityViewModels {
        UserDetailViewModelFactory(UserRepository(RetrofitClient.apiService),arguments?.getInt("userId") ?: -1) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val userId = arguments?.getInt("userId") ?: -1
        binding.ivBack.setOnClickListener(View.OnClickListener { activity?.finish();
        })

        viewModel.user.observe(viewLifecycleOwner) { user ->
            Glide.with(view.context)
                .load(user.image)
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .into(binding.ivprofile)

            binding.userName.text = user.firstName +" "+ user.lastName
            binding.userEmail.text = user.email
            binding.tvAge.text = user.age.toString()
            binding.tvbirthDate.text =  user.birthDate
            binding.tvbloodGroup.text =  user.bloodGroup

            binding.tvMadenname.text = user.maidenName
            binding.tvgender.text = user.gender

            binding.tvgender.text = user.gender
            binding.tvusername.text = user.username
            binding.tveyeColor.text =  getString(R.string.eyecolor)+ user.eyeColor
            binding.tvaddress.text = user.address.address +"\n"+user.address.city+"\n"+user.address.state
            binding.tvContactInfo.text = user.phone
            binding.tvemail.text = user.email
            binding.tvaddress1.text =user.address.city+"\n"+user.address.state+"\n"+user.address.country
            binding.tvuniversity.text = user.university

            binding.tvbank.text = user.bank.cardExpire+"\n"+user.bank.cardNumber+"\n"+user.bank.cardType+"\n"+user.bank.currency
            binding.tvCompany.text =user.company.title+"\n"+ user.company.name+"\n"+user.company.address+"\n"+user.company.department
            binding.tvPostalCode.text = user.address.postalCode
            binding.tvCrypto.text = user.crypto.coin +"\n"+ user.crypto.network +"\n"+ user.crypto.network +"\n"
            binding.tvuniversity.text = user.university





        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        viewModel.fetchUserById(userId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
