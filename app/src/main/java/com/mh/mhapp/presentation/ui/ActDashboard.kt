package com.mh.mhapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.mh.mhapp.R
import com.mh.mhapp.data.model.UserInfo
import com.mh.mhapp.databinding.ActDashboardBinding
import com.mh.mhapp.presentation.vm.DashboardViewModel

class ActDashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            viewModel.getInfo(this, bundle.getString("user", ""))
        }

        setDrawerLayout()

        viewModel.infoUser.observe(this) {
            setNavigationView(it)
        }
    }

    private fun setNavigationView(userInfo: UserInfo) {
        val navigationView = findViewById<View>(R.id.navView) as NavigationView

        val viewHeader: View = LayoutInflater.from(this)
            .inflate(R.layout.layout_dashboard_header, navigationView, false)

        if (userInfo.status) {
            viewHeader.findViewById<TextView>(R.id.tvName).text = userInfo.user.name
            viewHeader.findViewById<TextView>(R.id.tvEmail).text = userInfo.user.email
            // viewHeader.findViewById<TextView>(R.id.tvUser).text = userInfo.user.
        }
        navigationView.removeHeaderView(navigationView.getHeaderView(0))
        navigationView.addHeaderView(viewHeader)
    }

    private fun setDrawerLayout() {
        setSupportActionBar(binding.topAppBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        actionBarDrawerToggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun navigate(idDestination: Int) {
        Navigation.findNavController(this, R.id.navHostDashboard)
            .navigate(idDestination)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                println("Profile")
                navigate(R.id.frgHome)
            }

            R.id.menu_profile -> {
                println("Profile")
                navigate(R.id.frgProfile)
            }

            R.id.menu_content -> {
                println("Content")
            }

            R.id.menu_about -> {
                println("About")
                // navigate(R.id.)
            }
        }

        /**
         * Close drawer when user click in item action
         */
        binding.drawerLayout.closeDrawer(GravityCompat.START)

        return super.onOptionsItemSelected(item)
    }

    /**
     * Open the drawer when user click in sandwich icon
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
