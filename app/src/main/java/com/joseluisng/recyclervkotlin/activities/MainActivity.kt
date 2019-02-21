package com.joseluisng.recyclervkotlin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.joseluisng.recyclervkotlin.R
import com.joseluisng.recyclervkotlin.fragments.ArrivalsFragment
import com.joseluisng.recyclervkotlin.fragments.DeparturesFragment
import com.joseluisng.recyclervkotlin.fragments.HomeFragment
import com.joseluisng.recyclervkotlin.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Agregando el toolbar que diseñamos
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar as Toolbar)

        setNavDrawer()
        setUserHeaderInformation()

        //Cuando rotes el celular, no se pierda el fragment donde estas, o el item seleccionado del navDrawerView
        if(savedInstanceState == null){
            fragmentTransaction(HomeFragment())
            navView.menu.getItem(0).isChecked = true
        }


    }

    // función par activar el navDrawer
    private fun setNavDrawer(){
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar as Toolbar?, R.string.open_drawer, R.string.close_drawer)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

    }
    // Función para pasarle el fragmento que se eligio
    private fun fragmentTransaction(fragment: Fragment){

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()

    }
    // Funcipon para elegir el fragment mediante el item que se selecciona
    fun loadFragmenById(id: Int){
        when(id){
            R.id.nav_home -> fragmentTransaction(HomeFragment())
            R.id.nav_departures -> fragmentTransaction(DeparturesFragment())
            R.id.nav_arrivals -> fragmentTransaction(ArrivalsFragment())
        }
    }
    // Funcion para Elegir un fragment
    fun showMessageNavItemSelectedById(id: Int){
        when(id){
            R.id.nav_profile -> toast("Profile")
            R.id.nav_settings -> toast("Settings")
        }
    }

    // función para pasar textos al header el navDrawerView
    private fun setUserHeaderInformation(){
        val name = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)

        name?.let { name.text = getString(R.string.user_name) }
        email?.let { email.text = getString(R.string.user_email) }
    }

    // función que hace cambiar de fragmento
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelectedById(item.itemId)
        loadFragmenById(item.itemId)
        drawerLayout.closeDrawer(Gravity.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()

        }
    }

}
