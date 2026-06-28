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

package io.openauth.authenticator.core.crypto.model

object CryptoConstants {
    const val AES_ALGORITHM = "AES"
    const val AES_GCM_TRANSFORMATION = "AES/GCM/NoPadding"
    const val AES_KEY_SIZE_BYTES = 32
    const val GCM_IV_SIZE_BYTES = 12
    const val GCM_TAG_SIZE_BITS = 128
    const val ANDROID_KEYSTORE_PROVIDER = "AndroidKeyStore"
    const val KDF_SALT_SIZE_BYTES = 32
}