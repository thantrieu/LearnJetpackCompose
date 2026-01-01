package pro.branium.learnjetpackcompose.lesson20.data.local.permission

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_NAME = "permission_prefs"

// Extension tạo DataStore singleton theo Context
val Context.permissionDataStore: DataStore<Preferences> by preferencesDataStore(
    name = DATASTORE_NAME
)

// Key lưu cờ "đã từng xin POST_NOTIFICATIONS"
val KEY_ASKED_POST_NOTIFICATIONS = booleanPreferencesKey("asked_post_notifications")
