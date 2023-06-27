package tiposdeclasses

enum class CreditCard(val label: String, val discountPercent: Int) {
    VISA("Visa", 40),
    MASTER("Mastercard", 0),
    ELO("Elo", 10),
    AMEX("American Card", 0)
}