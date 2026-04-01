package pro.branium.learnjetpackcompose.lesson41.data.remote.user

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pro.branium.learnjetpackcompose.lesson41.domain.model.User

class FriendRepository {
    fun getFriends(userId: String): Flow<List<User>> = callbackFlow {
        val listener = FirebaseFirestore.getInstance()
            .collection("users")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val users = snapshot?.documents?.mapNotNull {
                    it.toObject(User::class.java)
                }?.filter { user ->
                    user.userId != userId
                } ?: emptyList()

                trySend(users)
            }

        awaitClose { listener.remove() }
    }
}