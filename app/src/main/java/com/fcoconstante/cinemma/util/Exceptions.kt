package com.fcoconstante.cinemma.util

import java.io.IOException

class ApiException(message: String) : IOException(message)

class NoInternetExeption(message: String) : IOException(message)