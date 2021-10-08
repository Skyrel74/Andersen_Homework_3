package ru.skyrel74.andersenhomework3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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

                Glide.with(this)
                    .asBitmap()
                    .load(textView.text.toString())
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean,
                        ): Boolean {
                            Toast.makeText(this@SecondActivity,
                                "Не получилось загрузить изображение",
                                Toast.LENGTH_SHORT).show()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean,
                        ): Boolean = false

                    })
                    .into(binding.imageView)

//                val inputMethodManager =
//                    applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                inputMethodManager.hideSoftInputFromWindow(binding.editTextUrl.windowToken, 0)
//
//                val urlString = textView.text.toString()
//                val image = RemoteImageManager().execute(urlString).get()
//                if (image != null)
//                    binding.imageView.setImageBitmap(image)
//                else
//                    Toast.makeText(this, "Не получилось загрузить изображение", Toast.LENGTH_SHORT)
//                        .show()

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