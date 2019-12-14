package com.postagraph.app.ui.customui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.enyason.password_strength_indicator.R
import com.nulabinc.zxcvbn.Zxcvbn
import kotlinx.android.synthetic.main.indicator.view.*


class PwdStrengthIndicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    private val colorList =
        arrayListOf(
            R.color.red_pwd_indicator,
            R.color.yellow_pwd_indicator,
            R.color.green_pwd_indicator,
            R.color.grey_indicator
        )


    private val passWordStrengthGenerator by lazy {
        Zxcvbn()
    }
    init {
        LayoutInflater.from(context).inflate(R.layout.indicator, this)


        applyChangesToViews(5)

    }


    var pwdStrength = 0
        set(value) {

            applyChangesToViews(value)
        }


    fun measure(password:CharSequence){

        pwdStrength = if (password.isNotEmpty()){
            passWordStrengthGenerator.measure(password).score
        }else{
            5
        }
    }

    private fun applyChangesToViews(value: Int) {

        setText(value)
        when (value) {

            0, 1 -> {
                weak.setBackgroundColor(resources.getColor(colorList[0]))
                fair.setBackgroundColor(resources.getColor(colorList[3]))
                strong.setBackgroundColor(resources.getColor(colorList[3]))

            }
            2, 3 -> {

                weak.setBackgroundColor(resources.getColor(colorList[1]))
                fair.setBackgroundColor(resources.getColor(colorList[1]))
                strong.setBackgroundColor(resources.getColor(colorList[3]))

            }

            4 -> {
                weak.setBackgroundColor(resources.getColor(colorList[2]))
                fair.setBackgroundColor(resources.getColor(colorList[2]))
                strong.setBackgroundColor(resources.getColor(colorList[2]))

            }
            else -> {
                weak.setBackgroundColor(resources.getColor(colorList[3]))
                fair.setBackgroundColor(resources.getColor(colorList[3]))
                strong.setBackgroundColor(resources.getColor(colorList[3]))
            }

        }

    }

    fun setText(strength: Int) {

        when (strength) {

            0, 1 -> {
                tv_state.text = resources.getString(R.string.weak)
                tv_state.setTextColor(ContextCompat.getColor(context, colorList[0]))
            }
            2, 3 -> {
                tv_state.text = resources.getString(R.string.fair)
                tv_state.setTextColor(ContextCompat.getColor(context, colorList[1]))
            }
            4 -> {


                tv_state.text = resources.getString(R.string.strong)
                tv_state.setTextColor(ContextCompat.getColor(context, colorList[2]))
            }
            else -> {

                tv_state.text = ""
                tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.transparent))

            }
        }
    }

}