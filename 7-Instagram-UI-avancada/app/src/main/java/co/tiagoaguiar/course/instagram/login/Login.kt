package co.tiagoaguiar.course.instagram.login

interface Login {

    // camada de view
    interface View {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized()
    }
}