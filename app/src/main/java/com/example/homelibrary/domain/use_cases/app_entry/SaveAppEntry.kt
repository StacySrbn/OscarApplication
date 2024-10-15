package com.example.homelibrary.domain.use_cases.app_entry

import com.example.homelibrary.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor (
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }

}