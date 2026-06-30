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

package io.openauth.authenticator.core.security.clipboard

import io.openauth.authenticator.core.security.model.SecurityConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClipboardAutoClearScheduler @Inject constructor(
    private val clipboard: SecureClipboardManager
) {
    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate
    )

    private var clearJob: Job? = null

    fun schedule(
        delayMillis: Long = SecurityConstants.CLIPBOARD_CLEAR_DELAY_MS
    ) {
        clearJob?.cancel()
        clearJob = scope.launch {
            delay(delayMillis)
            clipboard.clear()
        }
    }

    fun cancel() {
        clearJob?.cancel()
        clearJob = null
    }

    fun destroy() {
        clearJob?.cancel()
    }
}