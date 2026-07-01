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

package io.openauth.authenticator.core.datastore.preferences

import io.openauth.authenticator.core.model.AppLockMethod
import io.openauth.authenticator.core.model.SortOrder
import io.openauth.authenticator.core.model.ThemeMode
import io.openauth.authenticator.core.security.lock.AppLockTimeout

data class UserPreferences(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val sortOrder: SortOrder = SortOrder.CUSTOM,
    val appLockMethod: AppLockMethod = AppLockMethod.NONE,
    val autoLockTimeout: AppLockTimeout = AppLockTimeout.DEFAULT,
    val screenshotBlocked: Boolean = true,
    val clipboardClearDelayMs: Long = 30_000L,
    val showOtpDigitsGrouped: Boolean = true,
    val copyOnTap: Boolean = false,
    val hapticFeedback: Boolean = true,
    val onboardingComplete: Boolean = false,
    val lastSelectedGroupId: String? = null,
    val backupReminderIntervalDays: Int = 30,
)