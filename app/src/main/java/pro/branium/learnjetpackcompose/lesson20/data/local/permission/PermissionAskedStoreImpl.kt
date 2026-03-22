package pro.branium.learnjetpackcompose.lesson20.data.local.permission

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import pro.branium.learnjetpackcompose.lesson20.data.PermissionAskedStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionAskedStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PermissionAskedStore {

    override suspend fun getAskedBefore(): Boolean {
        val prefs = dataStore.data.first()
        return prefs[KEY_ASKED_POST_NOTIFICATIONS] ?: false
    }

    override suspend fun setAskedBefore(value: Boolean) {
        dataStore.edit { prefs ->
            prefs[KEY_ASKED_POST_NOTIFICATIONS] = value
        }
    }
}
