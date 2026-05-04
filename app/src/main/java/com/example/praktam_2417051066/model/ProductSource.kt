package com.example.praktam_2417051066.model

import android.content.Context

object ProductSource {
    // Fungsi untuk mencari ID drawable berdasarkan nama file (String) dari API
    fun getResourceId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}