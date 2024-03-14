package com.ft.tasbhi_widget

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ft.tasbhi_widget.databinding.ActivityMainBinding
import com.ft.tasbhi_widget.utils.ColorType
import com.ft.tasbhi_widget.utils.HawkManager
import com.ft.tasbhi_widget.utils.IntentManager
import com.ft.tasbhi_widget.utils.rotate
import com.ft.tasbhi_widget.utils.showSnackbar
import com.ft.tasbhi_widget.utils.vibratePhone
import com.ft.tasbhi_widget.base.BaseActivity
import com.ft.tasbhi_widget.utils.changeLanguage

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onAfterCreate() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //enableFullScreenMode(window = window)
        configMenuClickListeners()
        configDetailsClickListeners()
        configColorClickListeners()
        chooseSelectedTextColor()
        chooseSelectedLanguage()
    }
    private fun configMenuClickListeners() = with(binding) {
        clDetails.setOnClickListener {
            if(elDetails.isExpanded) {
                elDetails.collapse()
                imgDetailsArrow.rotate(destinationRotate = 0f)
            } else {
                elDetails.expand()
                imgDetailsArrow.rotate(destinationRotate = 180f)
            }
        }
        clLanguage.setOnClickListener {
            if(elLanguage.isExpanded) {
                elLanguage.collapse()
                imgLanguageArrow.rotate(destinationRotate = 0f, duration = 150)
            } else {
                elLanguage.expand()
                imgLanguageArrow.rotate(destinationRotate = 180f, duration = 150)
            }
        }
        clColor.setOnClickListener {
            if(elColor.isExpanded) {
                elColor.collapse()
                imgColorArrow.rotate(destinationRotate = 0f, duration = 150)
            } else {
                elColor.expand()
                imgColorArrow.rotate(destinationRotate = 180f, duration = 150)
            }
        }
        clStar.setOnClickListener {
            IntentManager.rateIntent(
                context = this@MainActivity,
                view = binding.root
            )
        }
        clShare.setOnClickListener {
            IntentManager.shareTextIntent(
                context = this@MainActivity,
                view = binding.root,
                title = getString(R.string.introduce_to_friends),
                description = getString(R.string.share_app_with_friends)
            )
        }
        clExit.setOnClickListener {
            finish()
        }
    }
    private fun configDetailsClickListeners() = with(binding) {

        english.setOnClickListener {
            HawkManager.saveLanguage(language = "en")
            chooseSelectedLanguage()
            widgetLanguageChange()
            showSnackbar(
                view = binding.root,
                message = getString(R.string.english)
            )
            changeLanguage(language = "en", isFirstTime = false)
        }
        arabic.setOnClickListener {
            HawkManager.saveLanguage(language = "ar")
            chooseSelectedLanguage()
            widgetLanguageChange()
            showSnackbar(
                view = binding.root,
                message = getString(R.string.arabic)
            )
            changeLanguage(language = "ar", isFirstTime = false)
        }
        bangla.setOnClickListener {
            HawkManager.saveLanguage(language = "bn")
            chooseSelectedLanguage()
            widgetLanguageChange()
            showSnackbar(
                view = binding.root,
                message = getString(R.string.bengali)
            )
            changeLanguage(language = "bn", isFirstTime = false)
        }

        imgZekrRefresh.setOnClickListener {
            vibratePhone()
            imgZekrRefresh.rotate(
                destinationRotate = if(imgZekrRefresh.rotation == 0f) 360f else 0f
            )
            HawkManager.saveZekr(zekr = 0)
            IntentManager.resetZekrIntent(context = this@MainActivity)
            showSnackbar(
                view = binding.root,
                message = getString(R.string.zikr_has_been_reset)
            )
        }
        imgSalavatRefresh.setOnClickListener {
            vibratePhone()
            imgSalavatRefresh.rotate(
                destinationRotate = if(imgSalavatRefresh.rotation == 0f) 360f else 0f
            )
            HawkManager.saveSalavat(salavat = 0)
            IntentManager.resetPrayerIntent(context = this@MainActivity)
            showSnackbar(
                view = binding.root,
                message = getString(R.string.prayers_reset)
            )
        }
        imgTasbihatRefresh.setOnClickListener {
            vibratePhone()
            imgTasbihatRefresh.rotate(
                destinationRotate = if(imgTasbihatRefresh.rotation == 0f) 360f else 0f
            )
            HawkManager.apply {
                saveTasbihatAA(tasbihatAA = 0)
                saveTasbihatSA(tasbihatSA = 0)
                saveTasbihatHA(tasbihatHA = 0)
            }
            IntentManager.resetTasbihatIntent(context = this@MainActivity)
            showSnackbar(
                view = binding.root,
                message = getString(R.string.tasbih_has_been_reset)
            )
        }
    }

    private fun widgetLanguageChange() {
        IntentManager.changeTasbhiLanguageIntent(context = this@MainActivity)
        IntentManager.changeZikrLanguageIntent(context = this@MainActivity)
        IntentManager.changePrayerLanguageIntent(context = this@MainActivity)
    }

    private fun configColorClickListeners() = with(binding) {
        rbTextWhite.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.WHITE)
            IntentManager.apply {
                changeSalavatColorIntent(context = this@MainActivity)
                changeZekrColorIntent(context = this@MainActivity)
                changeTasbihatColorIntent(context = this@MainActivity)
            }
        }
        rbTextBlack.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.BLACK)
            IntentManager.apply {
                changeSalavatColorIntent(context = this@MainActivity)
                changeZekrColorIntent(context = this@MainActivity)
                changeTasbihatColorIntent(context = this@MainActivity)
            }
        }
        rbTextGreen.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.GREEN)
            IntentManager.apply {
                changeSalavatColorIntent(context = this@MainActivity)
                changeZekrColorIntent(context = this@MainActivity)
                changeTasbihatColorIntent(context = this@MainActivity)
            }
        }
        rbTextRed.setOnClickListener {
            HawkManager.saveTextColor(color = ColorType.RED)
            IntentManager.apply {
                changeSalavatColorIntent(context = this@MainActivity)
                changeZekrColorIntent(context = this@MainActivity)
                changeTasbihatColorIntent(context = this@MainActivity)
            }
        }
    }

    private fun chooseSelectedTextColor() = with(binding) {
        HawkManager.getTextColor().let {
            rbTextWhite.isChecked = it == ColorType.WHITE
            rbTextBlack.isChecked = it == ColorType.BLACK
            rbTextGreen.isChecked = it == ColorType.GREEN
            rbTextRed.isChecked = it == ColorType.RED
        }
    }

    private fun chooseSelectedLanguage() = with(binding) {

        when(HawkManager.getLanguage()){
            "en" ->{
                english.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#4D08B681"))
                //arabic.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#08B681"))
                //bangla.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#08B681"))
            }
            "ar" ->{
                arabic.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#4D08B681"))
                //english.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#08B681"))
                //bangla.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#08B681"))
            }
            "bn" ->{
                bangla.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#4D08B681"))
                //english.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#08B681"))
                //arabic.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#08B681"))
            }
        }
    }

    private fun enableFullScreenMode(window: Window) {
        window.apply {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}