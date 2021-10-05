package ru.skyrel74.andersenhomework3

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.skyrel74.andersenhomework3.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity(R.layout.activity_first) {

    private val binding by viewBinding(ActivityFirstBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupFlag(layoutId: Int, flagSize: Float) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flag_container, FlagFragment.newInstance(layoutId))
            .commit()
        val width = binding.flagContainer.width
        val layoutParams = LinearLayout.LayoutParams(width, (width * flagSize).toInt())
        binding.flagContainer.layoutParams = layoutParams
        binding.flagContainer.foregroundGravity = Gravity.CENTER_HORIZONTAL
    }

    private fun setupListeners() {

        val flagButtons = listOf(
            binding.buttonAustria,
            binding.buttonPoland,
            binding.buttonItaly,
            binding.buttonColombia,
            binding.buttonMadagascar,
            binding.buttonThailand,
            binding.buttonDenmark,
            binding.buttonSwitzerland
        )
        val flagLayout = listOf(
            R.layout.flag_austria,
            R.layout.flag_poland,
            R.layout.flag_italy,
            R.layout.flag_colombia,
            R.layout.flag_madagascar,
            R.layout.flag_thailand,
            R.layout.flag_denmark,
            R.layout.flag_switzerland
        )

        val flagSize = listOf(
            2 / 3F,
            5 / 8F,
            2 / 3F,
            2 / 3F,
            2 / 3F,
            2 / 3F,
            14 / 17F,
            1 / 1F
        )

        for (i: Int in flagButtons.indices)
            flagButtons[i].setOnClickListener { setupFlag(flagLayout[i], flagSize[i]) }
    }

    class FlagFragment(layoutId: Int) : Fragment(layoutId) {

        companion object {

            @JvmStatic
            fun newInstance(@LayoutRes layout: Int): FlagFragment {
                return FlagFragment(layout)
            }
        }
    }
}