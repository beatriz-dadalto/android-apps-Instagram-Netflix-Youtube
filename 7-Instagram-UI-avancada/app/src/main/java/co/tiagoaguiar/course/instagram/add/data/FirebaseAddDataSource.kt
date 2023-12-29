package co.tiagoaguiar.course.instagram.add.data

import android.net.Uri
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

class FirebaseAddDataSource : AddDataSource {

  override fun createPost(
    userUUID: String,
    uri: Uri,
    caption: String,
    callback: RequestCallback<Boolean>
  ) {
    val uriLastPath = uri.lastPathSegment ?: throw IllegalArgumentException("Invalid image!")

    // jogar a imagem no Storage
    val imgRef = FirebaseStorage.getInstance().reference
      .child("images/")
      .child(userUUID)
      .child(uriLastPath)

    // baixar a ref da imagem que esta no Storage
    imgRef.putFile(uri)
      .addOnSuccessListener { response ->
        imgRef.downloadUrl
          .addOnSuccessListener { responseDownload ->
            // buscar o user
            FirebaseFirestore.getInstance()
              .collection("/users")
              .document(userUUID)
              .get()
              .addOnSuccessListener { resMe ->
                val me = resMe.toObject(User::class.java)

                // criar uma coleções de posts
                val postRef = FirebaseFirestore.getInstance()
                  .collection("/posts")
                  .document(userUUID)
                  .collection("posts")
                  .document()

                val post = Post(
                  uuid = postRef.id,
                  photoUrl = responseDownload.toString(),
                  caption = caption,
                  timestamp = System.currentTimeMillis(),
                  publisher = me
                )

                postRef.set(post)
                  .addOnSuccessListener { resPost ->
                    FirebaseFirestore.getInstance()
                      .collection("/feeds")
                      .document(userUUID)
                      .collection("posts")
                      .document(postRef.id)
                      .set(post)
                      .addOnSuccessListener { resMyFeed ->
                        // feed dos meus seguidores
                        FirebaseFirestore.getInstance()
                          .collection("/followers")
                          .document(userUUID)
                          .collection("followers")
                          .get()
                          .addOnSuccessListener { resFollowers ->
                            val documents = resFollowers.documents
                            for (document in documents) {
                              val followerUUID =
                                document.toObject(String::class.java)
                                  ?: throw RuntimeException("Falha ao converter seguidor!")

                              FirebaseFirestore.getInstance()
                                .collection("/feeds")
                                .document(followerUUID)
                                .collection("posts")
                                .document(postRef.path)
                                .set(post)
                            }
                            callback.onSuccess(true)
                          }
                          .addOnFailureListener { exception ->
                            callback.onFailure(
                              exception.message
                                ?: "Falha ao buscar meus seguidores!"
                            )
                          }
                          .addOnCompleteListener {
                            callback.onComplete()
                          }
                      }
                  }
                  .addOnFailureListener { exeception ->
                    callback.onFailure(
                      exeception.message ?: "Falha ao inserir um post!"
                    )
                  }

              }
              .addOnFailureListener { exception ->
                callback.onFailure(
                  exception.message ?: "Falha ao buscar usuário logado!"
                )
              }
          }
          .addOnFailureListener { exception ->
            callback.onFailure(exception.message ?: "Falha ao baixar a foto!")
          }
      }
      .addOnFailureListener { exception ->
        callback.onFailure(exception.message ?: "Falha ao subir a foto!")
      }
  }
}