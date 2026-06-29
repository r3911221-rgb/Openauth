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

package io.openauth.authenticator.core.security.lock

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PinValidator @Inject constructor() {

    fun validate(pin: CharArray): PinValidationResult {
        return when {
            pin.size !in 4..12 -> PinValidationResult.InvalidLength(4, 12)
            pin.any { !it.isDigit() } -> PinValidationResult.NonDigitCharacter
            else -> PinValidationResult.Valid
        }
    }
}

sealed interface PinValidationResult {
    data object Valid : PinValidationResult
    data class InvalidLength(val min: Int, val max: Int) : PinValidationResult
    data object NonDigitCharacter : PinValidationResult
}