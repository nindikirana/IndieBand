package com.nindikiranaf.indieband

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPref: SharedPref
    lateinit var indieBandDao: IndieBandDao
    lateinit var adapter: IndieBandAdapter

    companion object{
        const val REQUEST_ADD = 100
        const val REQUEST_EDIT = 200
        const val REQUEST_REMOVE = 300
        const val REQUEST_DETAIL= 500
        const val KEY_INDIEBAND = "indieBand"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indieBandDao = AppDatabase.getInstance(this).indieBandDao()
        sharedPref = SharedPref(this)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = IndieBandAdapter(indieBandDao.selectAll(), sharedPref)
        recyclerView.adapter = adapter
        adapter.onItemClickListener = {
            val intent = Intent (this, DetailActivity::class.java)
            intent.putExtra(KEY_INDIEBAND, it)
            startActivityForResult(intent, REQUEST_DETAIL)
        }
        buttonAdd.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD)
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.layoutManager = if(sharedPref.gridLayout && sharedPref.column > 0){
            GridLayoutManager(this, sharedPref.column)
        }else{
            LinearLayoutManager(this)
        }
        supportActionBar?.title = sharedPref.appName
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ADD && resultCode == Activity.RESULT_OK && data != null){
            val indieBand = data.getParcelableExtra<IndieBand>(KEY_INDIEBAND)
            indieBand?.apply {
                indieBandDao.insert(this)
            }
            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        }
        adapter.list = indieBandDao.selectAll()
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu,menu)
        menu?.add(Menu.NONE, 1 , Menu.NONE, "Dark Mode")
        menu?.add(Menu.NONE, 2 , Menu.NONE, "Light Mode")
        menu?.add(Menu.NONE, 3 , Menu.NONE, "Follow by system")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuSettings->startActivity(Intent(this,SettingsActivity::class.java))
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        return super.onOptionsItemSelected(item)
    }
}