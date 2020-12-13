package com.nindikiranaf.indieband

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.apply {
            title = "Settings"
            setDisplayHomeAsUpEnabled(true)
        }

        val sharedPref = SharedPref(this)
        settingGrid.isChecked = sharedPref.gridLayout
        settingGrid.setOnCheckedChangeListener { buttonView, isChecked ->
            sharedPref.gridLayout = isChecked
        }
        
        settingGenre.isChecked = sharedPref.genre
        settingGenre.setOnCheckedChangeListener { buttonView, isChecked ->
            sharedPref.genre = isChecked
        }

        settingAsal.isChecked = sharedPref.asal
        settingAsal.setOnCheckedChangeListener { buttonView, isChecked ->
            sharedPref.asal = isChecked
        }

        settingTh_Aktif.isChecked = sharedPref.th_aktif
        settingTh_Aktif.setOnCheckedChangeListener { buttonView, isChecked ->
            sharedPref.th_aktif = isChecked
        }

        settingLagu_Fav.isChecked = sharedPref.lagu_fav
        settingLagu_Fav.setOnCheckedChangeListener { buttonView, isChecked ->
            sharedPref.lagu_fav = isChecked
        }

        settingAppName.setText(sharedPref.appName)
        settingAppName.addTextChangedListener {
            sharedPref.appName = it.toString()
        }

        SettingColumn.setText(sharedPref.column.toString())
        SettingColumn.addTextChangedListener {
            var cols = if(it.toString().length == 0) 1 else it.toString().toInt()
            cols = if(cols > 3){
                3
            }else if(cols < 1){
                1
            }else{
                cols
            }
            sharedPref.column = cols
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, 1 , Menu.NONE, "Dark Mode")
        menu?.add(Menu.NONE, 2 , Menu.NONE, "Light Mode")
        menu?.add(Menu.NONE, 3 , Menu.NONE, "Follow by system")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        finish()
        return super.onOptionsItemSelected(item)
    }
}