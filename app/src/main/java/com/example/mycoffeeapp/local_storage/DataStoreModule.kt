package com.example.mycoffeeapp.local_storage

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(
  name = "app_preferences"
)