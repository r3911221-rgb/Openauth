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

enum class AppLockTimeout(val duration: Duration?) {
    IMMEDIATELY(Duration.ZERO),
    AFTER_30_SECONDS(Duration.ofSeconds(30)),
    AFTER_1_MINUTE(Duration.ofMinutes(1)),
    AFTER_5_MINUTES(Duration.ofMinutes(5)),
    AFTER_15_MINUTES(Duration.ofMinutes(15)),
    NEVER(null);

    companion object {
        val DEFAULT: AppLockTimeout = AFTER_1_MINUTE
    }
}