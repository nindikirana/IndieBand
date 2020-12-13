package com.nindikiranaf.indieband

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add_edit.*

class AddEditActivity : AppCompatActivity() {
    var indieBand: IndieBand? = null

    companion object{
        const val  REQUEST_IMAGE = 400
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        indieBand = intent.getParcelableExtra(MainActivity.KEY_INDIEBAND)
        supportActionBar?.apply {
            title = if (indieBand == null) "Add IndieBand" else "Edit IndieBand"
            indieBand?.apply{
                subtitle = nama_band
            }
            setDisplayHomeAsUpEnabled(true)
        }
        indieBand?.apply {
            addEditNama.setText(nama_band)
            addEditGenre.setText(genre)
            addEditAsal.setText(asal)
            addEditThAktif.setText(th_aktif)
            addEditLabel.setText(label)
            addEditAnggota.setText(anggota)
            addEditLaguFav.setText(lagu_fav)
            addEditUrl.setText(url)
            addEditGambar.setText(gambar)
        }
        buttonBrowse.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Pilih Gambar"), REQUEST_IMAGE)
        }
        buttonSave.setOnClickListener {
            if(indieBand == null) indieBand = IndieBand()
            indieBand?.apply {
                nama_band = addEditNama.text.toString()
                genre = addEditGenre.text.toString()
                asal = addEditAsal.text.toString()
                th_aktif = addEditThAktif.text.toString()
                label = addEditLabel.text.toString()
                anggota = addEditAnggota.text.toString()
                lagu_fav = addEditLaguFav.text.toString()
                url = addEditUrl.text.toString()
                gambar = addEditGambar.text.toString()
            }
            val intent = Intent()
            intent.putExtra(MainActivity.KEY_INDIEBAND, indieBand)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUrl = data.data?.toString()
            addEditGambar.setText(imageUrl)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}