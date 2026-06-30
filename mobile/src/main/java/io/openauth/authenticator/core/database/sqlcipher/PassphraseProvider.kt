/*
 * Copyright (c) 2026 OpenAuth Authenticator
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openauth.authenticator.core.database.sqlcipher

import io.openauth.authenticator.core.crypto.keystore.MasterKeyProvider
import javax.crypto.Cipher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PassphraseProvider @Inject constructor(
    private val masterKeyProvider: MasterKeyProvider,
) {
    fun getPassphrase(): ByteArray {
        val key = masterKeyProvider.getOrCreate()
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        return cipher.doFinal(DATABASE_KEY)
    }

    companion object {
        private val DATABASE_KEY = "openauth_secure_database_v2"
            .encodeToByteArray()
    }
}