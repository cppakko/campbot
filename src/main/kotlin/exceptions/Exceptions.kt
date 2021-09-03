package exceptions

class GroupNotFoundException : Exception()

class GroupUserNotFoundException : Exception()

class FriendNotFoundException : Exception()

class ApiCallFailedException(private val msg: String, private val wording: String) : Exception() {
    override fun printStackTrace() {
        println("$msg     $wording")
        super.printStackTrace()
    }
}