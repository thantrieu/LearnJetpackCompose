package pro.branium.learnjetpackcompose.lesson20.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ThemeLocalDataSource {
    override val isDarkMode: Flow<Boolean>
        get() = context.dataStore.data
            .map { preferences ->
                preferences[IS_DARK_MODE_KEY] ?: false // Mặc định là Light Mode
            }

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { settings ->
            settings[IS_DARK_MODE_KEY] = isDarkMode
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
private val IS_DARK_MODE_KEY = booleanPreferencesKey("is_dark_mode")
