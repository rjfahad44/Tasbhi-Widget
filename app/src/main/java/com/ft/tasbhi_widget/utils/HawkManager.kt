package com.ft.tasbhi_widget.utils

import com.ft.tasbhi_widget.utils.Constants.LANGUAGE
import com.ft.tasbhi_widget.utils.Constants.SALAVAT
import com.ft.tasbhi_widget.utils.Constants.SALAVAT_DAY
import com.ft.tasbhi_widget.utils.Constants.TASBIHAT_AA
import com.ft.tasbhi_widget.utils.Constants.TASBIHAT_HA
import com.ft.tasbhi_widget.utils.Constants.TASBIHAT_SA
import com.ft.tasbhi_widget.utils.Constants.TEXT_COLOR
import com.ft.tasbhi_widget.utils.Constants.ZEKR
import com.ft.tasbhi_widget.utils.Constants.ZEKR_DAY
import com.orhanobut.hawk.Hawk

object HawkManager {

    fun saveSalavat(salavat: Int) = Hawk.put(SALAVAT, salavat)

    fun getSalavat(): Int = Hawk.get(SALAVAT, 0).orZero()

    fun increaseSalavat(): Int {
        val currentSalavat = getSalavat()
        saveSalavat(salavat = currentSalavat + 1)
        return currentSalavat + 1
    }

    fun saveSalavatDay(day: String) = Hawk.put(SALAVAT_DAY, day)

    fun getSalavatDay(): String = Hawk.get(SALAVAT_DAY, "").orEmpty()

    fun saveZekr(zekr: Int) = Hawk.put(ZEKR, zekr)

    fun getZekr(): Int = Hawk.get(ZEKR, 0).orZero()

    fun saveLanguage(language: String) = Hawk.put(LANGUAGE, language)

    fun getLanguage(): String = Hawk.get(LANGUAGE, "en")

    fun increaseZekr(): Int {
        val currentZekr = getZekr()
        saveZekr(zekr = currentZekr + 1)
        return currentZekr + 1
    }

    fun saveZekrDay(day: String) = Hawk.put(ZEKR_DAY, day)

    fun getZekrDay(): String = Hawk.get(ZEKR_DAY, "").orEmpty()

    fun saveTasbihatAA(tasbihatAA: Int) = Hawk.put(TASBIHAT_AA, tasbihatAA)

    fun getTasbihatAA(): Int = Hawk.get(TASBIHAT_AA, 0).orZero()

    fun increaseTasbihatAA(): Int {
        val currentTasbihatAA = getTasbihatAA()
        return if(currentTasbihatAA < 34) {
            saveTasbihatAA(tasbihatAA = currentTasbihatAA + 1)
            currentTasbihatAA + 1
        } else {
            currentTasbihatAA
        }
    }

    fun saveTasbihatSA(tasbihatSA: Int) = Hawk.put(TASBIHAT_SA, tasbihatSA)

    fun getTasbihatSA(): Int = Hawk.get(TASBIHAT_SA, 0).orZero()

    fun increaseTasbihatSA(): Int {
        val currentTasbihatSA = getTasbihatSA()
        return if(currentTasbihatSA < 33) {
            saveTasbihatSA(tasbihatSA = currentTasbihatSA + 1)
            currentTasbihatSA + 1
        } else {
            currentTasbihatSA
        }
    }

    fun saveTasbihatHA(tasbihatHA: Int) = Hawk.put(TASBIHAT_HA, tasbihatHA)

    fun getTasbihatHA(): Int = Hawk.get(TASBIHAT_HA, 0).orZero()

    fun increaseTasbihatHA(): Int {
        val currentTasbihatHA = getTasbihatHA()
        return if(currentTasbihatHA < 33) {
            saveTasbihatHA(tasbihatHA = currentTasbihatHA + 1)
            currentTasbihatHA + 1
        } else {
            currentTasbihatHA
        }
    }

    fun saveTextColor(color: ColorType) = Hawk.put(TEXT_COLOR, color)

    fun getTextColor(): ColorType = Hawk.get<ColorType>(TEXT_COLOR, ColorType.WHITE) ?: ColorType.WHITE

}