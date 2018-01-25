package com.ammyt.domain.interactor

import com.ammyt.domain.model.Shops

typealias CodeClosure = () -> Unit
typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
