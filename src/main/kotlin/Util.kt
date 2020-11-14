fun getResource(filename: String): String {
    return Exchanger::class.java.getResource(filename).toString()
}
