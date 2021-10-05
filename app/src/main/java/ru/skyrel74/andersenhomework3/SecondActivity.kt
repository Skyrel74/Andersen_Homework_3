package ru.skyrel74.andersenhomework3

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.skyrel74.andersenhomework3.databinding.ActivitySecondBinding
import java.net.URL

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    private val binding by viewBinding(ActivitySecondBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.editTextUrl.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {

                val inputMethodManager =
                    applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.editTextUrl.windowToken, 0)

                val urlString = textView.text.toString()
                val image = RemoteImageManager().execute(urlString).get()
                if (image != null)
                    binding.imageView.setImageBitmap(image)
                else
                    Toast.makeText(this, "Не получилось загрузить изображение", Toast.LENGTH_SHORT)
                        .show()

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    class RemoteImageManager : AsyncTask<String, Void, Bitmap?>() {

        override fun doInBackground(vararg urls: String?): Bitmap? =
            try {
                BitmapFactory.decodeStream(URL(urls[0]).openConnection().getInputStream())
            } catch (e: Exception) {
                Log.e("Load image error: ", e.stackTraceToString())
                null
            }
    }
}