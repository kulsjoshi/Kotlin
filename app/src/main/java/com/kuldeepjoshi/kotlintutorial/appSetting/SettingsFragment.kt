package com.kuldeepjoshi.kotlintutorial.appSetting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.kuldeepjoshi.kotlintutorial.R

class SettingsFragment() : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}