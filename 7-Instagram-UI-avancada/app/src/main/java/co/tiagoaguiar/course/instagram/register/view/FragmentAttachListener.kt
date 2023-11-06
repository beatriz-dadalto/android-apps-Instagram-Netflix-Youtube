package co.tiagoaguiar.course.instagram.register.view

/*
    Toda vez que um fragmento for anexado dentro de uma Activity essa
    Activity escutará todas mudanças do fragment.
    FragmentAttachListener eh para fazer a ligação entre uma Activity e um Fragment assim
    pode haver troca de dados e informações
 */
interface FragmentAttachListener {

    fun goToNameAndPasswordScreen(email: String)
    fun goToWelcomeScreen(name: String)
    fun goToPhotoScreen()
    fun goToMainScreen()
    fun goToGalleryScreen()
}