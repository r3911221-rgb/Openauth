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

import java.time.Duration
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LockoutPolicy @Inject constructor() {

    fun lockoutDurationFor(attempts: Int): Duration {
        if (attempts <= 3) return Duration.ZERO
        val exponent = (attempts - 4).coerceAtMost(10)
        val seconds = 5L * (1L shl exponent)
        return Duration.ofSeconds(seconds.coerceAtMost(1800))
    }
}