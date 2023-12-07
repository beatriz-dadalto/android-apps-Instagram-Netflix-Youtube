package co.tiagoaguiar.course.instagram.post.data

import android.net.Uri

interface PostDataSource {

    /*
        retorno assincrono e por isso nao tem um callback
        abrir um processo paralelo para nao travar a tela do user quando for buscar a foto
        e por isso eh declarada como SUSPEND que eh uma funcao disparada paralelamente
     */
    suspend fun fetchPictures(): List<Uri>
}