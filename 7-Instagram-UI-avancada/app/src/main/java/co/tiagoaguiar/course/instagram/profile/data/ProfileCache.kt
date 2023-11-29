package co.tiagoaguiar.course.instagram.profile.data


interface ProfileCache<T> {
    fun isCached(): Boolean
    fun get(key: String): T?
    fun put(data: T)
}