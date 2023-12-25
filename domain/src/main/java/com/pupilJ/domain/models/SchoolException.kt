package com.pupilJ.domain.models

open class SchoolException(message:String?):Exception(message)
open class AuthException(message: String?):SchoolException(message)
open class ValidationException(message: String?):SchoolException(message)
class UnAuthException(message: String?):AuthException(message)
class EmptyDataException(message: String?):SchoolException(message)
class NoInternetException(message: String?):SchoolException(message)
class InvalidData(message: String?):ValidationException(message)
class ServerException(message: String?):SchoolException(message)