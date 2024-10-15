package com.example.homelibrary.domain.use_cases.app_entry

import com.example.homelibrary.domain.manager.LocalUserManager
import javax.inject.Inject

class ReadAppEntry @Inject constructor (
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() {
        localUserManager.readAppEntry()
    }

}