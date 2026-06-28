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

package io.openauth.authenticator.core.crypto.hashing

import io.openauth.authenticator.core.crypto.otp.OtpAlgorithm
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class HmacProvider @Inject constructor() {

    fun compute(
        algorithm: OtpAlgorithm,
        key: ByteArray,
        message: ByteArray
    ): ByteArray {
        require(key.isNotEmpty()) { "HMAC key cannot be empty" }

        val mac = Mac.getInstance(algorithm.macAlgorithmName)
        val keyCopy = key.copyOf()

        try {
            mac.init(SecretKeySpec(keyCopy, algorithm.macAlgorithmName))
            return mac.doFinal(message)
        } finally {
            keyCopy.fill(0)
        }
    }
}