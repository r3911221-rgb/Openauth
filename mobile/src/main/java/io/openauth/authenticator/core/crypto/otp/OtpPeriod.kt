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

package io.openauth.authenticator.core.crypto.otp

import java.time.Duration

@JvmInline
value class OtpPeriod(
    val duration: Duration
) {
    init {
        require(duration.seconds in MIN_SECONDS..MAX_SECONDS) {
            "OTP period must be between $MIN_SECONDS and $MAX_SECONDS seconds"
        }
    }

    val seconds: Long get() = duration.seconds

    companion object {
        private const val MIN_SECONDS = 1L
        private const val MAX_SECONDS = 300L
        val DEFAULT = OtpPeriod(Duration.ofSeconds(30))

        fun ofSeconds(seconds: Long): OtpPeriod {
            return OtpPeriod(Duration.ofSeconds(seconds))
        }
    }
}