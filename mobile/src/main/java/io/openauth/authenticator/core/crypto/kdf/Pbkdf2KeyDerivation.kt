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

package io.openauth.authenticator.core.crypto.kdf

import java.util.Arrays
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject

class Pbkdf2KeyDerivation @Inject constructor() : KeyDerivationFunction {

    override val algorithm: KdfAlgorithm = KdfAlgorithm.PBKDF2

    override fun derive(
        password: CharArray,
        salt: ByteArray,
        keyLengthBytes: Int
    ): ByteArray {
        require(keyLengthBytes in MIN_KEY_BYTES..MAX_KEY_BYTES) {
            "Invalid key length"
        }
        require(salt.size >= MIN_SALT_BYTES) { "Invalid salt" }

        val saltCopy = salt.copyOf()
        val spec = PBEKeySpec(
            password, saltCopy, ITERATIONS,
            keyLengthBytes * Byte.SIZE_BITS
        )

        return try {
            SecretKeyFactory
                .getInstance(ALGORITHM)
                .generateSecret(spec)
                .encoded
        } finally {
            spec.clearPassword()
            Arrays.fill(saltCopy, 0)
        }
    }

    private companion object {
        const val ALGORITHM = "PBKDF2WithHmacSHA256"
        const val ITERATIONS = 600_000
        const val MIN_KEY_BYTES = 16
        const val MAX_KEY_BYTES = 64
        const val MIN_SALT_BYTES = 16
    }
}