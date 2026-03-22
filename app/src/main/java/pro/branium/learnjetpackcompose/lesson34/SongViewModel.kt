package pro.branium.learnjetpackcompose.lesson34

import android.content.Context
import android.util.Log
import androidx.annotation.RawRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song

class SongViewModel : ViewModel() {
    private val _songs = mutableListOf<Song>()
    val songs = MutableStateFlow<List<Song>>(emptyList())
    private val db = Firebase.firestore

    fun readJsonFromFile(context: Context, @RawRes fileName: Int) { // read json from file
        _songs.clear()
        val jsonStr = readRawJson(context, fileName)
        val songList = parseCountries(jsonStr)
        _songs.addAll(songList.songs)
        songs.value = _songs
//        saveSongToFirestoreV1()
        saveSongUsingBatch()
    }

    private fun readRawJson(context: Context, @RawRes rawResId: Int): String {
        return context.resources
            .openRawResource(rawResId)
            .bufferedReader()
            .use { it.readText() }
    }

    fun parseCountries(json: String): SongList {
        val type = object : TypeToken<SongList>() {}.type
        return Gson().fromJson(json, type)
    }

    fun saveSongToFirestoreV1() { // save document use set
        viewModelScope.launch {
//            for (song in _songs) {
            val song = _songs.first()
            db.collection("songs3")
//                    .document(song.id)
                .add(song)
                .await()
//            }
//            Log.e("==>", "${_songs.size} record saved!")
        }
    }

    private fun saveSongUsingBatch() {
        viewModelScope.launch {
            val batch = db.batch()
            val collectionRef = db.collection("songs10")
            for (song in _songs) {
                val docRef = collectionRef.document(song.id)
                batch.set(docRef, song)
            }
            batch.commit().await()
        }
    }

    fun updateSongToFirestore(songId: String) { // update document use update
        viewModelScope.launch {
            db.collection("songs")
                .document(songId)
                .update(
                    "playCount",
                    777,
                    "favorite",
                    5,
                    "imageUrl",
                    "https://thantrieu.com/resources/arts/1073419268.webp"
                )
                .addOnSuccessListener {
                    Log.e("==>", "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e ->
                    Log.e("==>", "Error updating document", e)
                }
        }
    }

    fun deleteSongFromFirebase(songId: String) { // remove/delete
        db.collection("songs10")
            .document(songId)
            .delete()
            .addOnSuccessListener {
                Log.e("==>", "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                Log.e("==>", "Error delete document", e)
            }
    }

    fun deleteCollection(
        collectionPath: String,
        batchSize: Int = 500
    ) {
        val collectionRef = Firebase.firestore.collection(collectionPath)
        viewModelScope.launch {
            while (true) {
                val snapshot = collectionRef
                    .limit(batchSize.toLong())
                    .get()
                    .await()

                if (snapshot.isEmpty) break

                val batch = Firebase.firestore.batch()
                snapshot.documents.forEach { doc ->
                    batch.delete(doc.reference)
                }
                batch.commit().await()
            }
        }
    }
}