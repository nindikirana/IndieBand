package com.nindikiranaf.indieband

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var indieBandDao: IndieBandDao
    var indieBand: IndieBand? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        indieBandDao = AppDatabase.getInstance(this).indieBandDao()
        indieBand = intent.getParcelableExtra<IndieBand>(MainActivity.KEY_INDIEBAND)
        populate(indieBand)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun populate(indieBand: IndieBand?) {
        indieBand?.apply {
            Glide.with(this@DetailActivity).load(gambar).into(detailImage)
            detailNama.text = nama_band
            detailGenre.text = genre
            detailAsal.text = asal
            detailThAktif.text = th_aktif
            detailLabel.text = label
            detailAnggota.text = anggota
            detailLaguFav.text = lagu_fav
            detailUrl.text = url
        }
        supportActionBar?.apply {
//            title = indieBand?.nama_band
//            subtitle = indieBand?.genre
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuEdit -> {
                val intent = Intent(this, AddEditActivity::class.java)
                intent.putExtra(MainActivity.KEY_INDIEBAND, indieBand)
                startActivityForResult(intent, MainActivity.REQUEST_EDIT)
            }
            R.id.menuRemove -> {
                AlertDialog.Builder(this)
                    .setMessage("Apakah Anda yakin menghapus item ini?")
                    .setPositiveButton("Ya") {_,_->
                        populate(indieBand)
                        indieBand?.apply {
                            indieBandDao.delete(this)
                        }
                        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("Tidak", null)
                    .show()
            }
            else -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MainActivity.REQUEST_EDIT && resultCode == Activity.RESULT_OK && data != null) {
            indieBand = data.getParcelableExtra(MainActivity.KEY_INDIEBAND)
            populate(indieBand)
            indieBand?.apply {
                indieBandDao.update(this)
            }
            Toast.makeText(this, "Data berhasil di edit", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}