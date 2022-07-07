package com.example.favouritedish.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.favouritedish.R
import com.example.favouritedish.databinding.FragmentAllDishesBinding
import com.example.favouritedish.view.activities.AddUpdateDishActivity
import com.example.favouritedish.viewmodel.HomeViewModel

class AllDishesFragment : Fragment() {

    private var _binding: FragmentAllDishesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //to show create menu items
        setHasOptionsMenu(true)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentAllDishesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //implement this function, to inflate the menu in the fragment/activity
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.all_dishes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    //implement this function, what will happen if we select an option from the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add_dish -> {
                //since we are in a fragment, we cannot simply use "this" to get the context. So, we use requireActivity()
                startActivity(Intent(requireActivity(), AddUpdateDishActivity::class.java))
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}