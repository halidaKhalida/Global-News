package com.example.globalnews.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.globalnews.R
import com.example.globalnews.adapter.BusinessAdapter
import com.example.globalnews.adapter.ViewpagerAdapter
import com.example.globalnews.model.ArticlesItem
import com.example.globalnews.model.ArticlesItem2
import com.example.globalnews.navigation.MyNavigator
import com.example.globalnews.ui.fragment.headlinesfragments.EntertainmentsFragment
import com.example.globalnews.ui.fragment.headlinesfragments.HealthFragment
import com.example.globalnews.ui.fragment.headlinesfragments.BusinessFragment
import com.example.globalnews.ui.fragment.headlinesfragments.SciensFragment
import com.example.globalnews.ui.fragment.headlinesfragments.SportFragment
import com.google.android.material.tabs.TabLayout


class HeadlinesFragment : Fragment(R.layout.fragment_headlines),MyNavigator{
    lateinit var myadapter:ViewpagerAdapter
    lateinit var titles:ArrayList<String>
    lateinit var bu:ArrayList<ArticlesItem2>
    lateinit var fragment:ArrayList<Fragment>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        loadFragment()

        bu= ArrayList()
        val viewPager=view.findViewById<ViewPager>(R.id.vp_list)
        val tabLayout= view.findViewById<TabLayout>(R.id.tablayout_1)
        tabLayout.setupWithViewPager(viewPager)
        myadapter= ViewpagerAdapter(fragment,titles,childFragmentManager)
        viewPager.adapter=myadapter


    }

    private fun loadFragment() {
        fragment= ArrayList()
        titles= ArrayList()
        fragment.add(BusinessFragment(this))
        titles.add("Бизнес")
        fragment.add(HealthFragment(this))
        titles.add("Здоровье")
        fragment.add(EntertainmentsFragment(this))
        titles.add("Развлечения")
        fragment.add(SportFragment(this))
        titles.add("Спорт")
        fragment.add(SciensFragment(this))
        titles.add("Наука")
    }

    override fun saveAction(actionID: Int, bundle: Bundle?) {
        when(actionID){
            R.id.action_headlinesFragment_to_detailFragment->{
                findNavController().navigate(R.id.action_headlinesFragment_to_detailFragment,bundle)
            }
        }
    }


}