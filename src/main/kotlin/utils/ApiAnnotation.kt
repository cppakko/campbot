package utils

@Target(AnnotationTarget.CLASS)
annotation class ApiEndPoint(val path: String)

@Target(AnnotationTarget.CLASS)
annotation class NoReturnData()
