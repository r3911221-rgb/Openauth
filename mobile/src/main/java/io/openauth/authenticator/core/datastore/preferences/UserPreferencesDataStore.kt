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

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import io.openauth.authenticator.core.model.AppLockMethod
import io.openauth.authenticator.core.model.SortOrder
import io.openauth.authenticator.core.model.ThemeMode
import io.openauth.authenticator.core.security.lock.AppLockTimeout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    val userPreferences: Flow<UserPreferences> = dataStore.data.map { prefs ->
        UserPreferences(
            themeMode = prefs[PreferencesKeys.THEME_MODE]?.let { ThemeMode.valueOf(it) } ?: ThemeMode.SYSTEM,
            sortOrder = prefs[PreferencesKeys.SORT_ORDER]?.let { SortOrder.valueOf(it) } ?: SortOrder.CUSTOM,
            appLockMethod = prefs[PreferencesKeys.APP_LOCK_METHOD]?.let { AppLockMethod.valueOf(it) } ?: AppLockMethod.NONE,
            autoLockTimeout = prefs[PreferencesKeys.AUTO_LOCK_TIMEOUT]?.let { AppLockTimeout.valueOf(it) } ?: AppLockTimeout.DEFAULT,
            screenshotBlocked = prefs[PreferencesKeys.SCREENSHOT_BLOCKED] ?: true,
            clipboardClearDelayMs = prefs[PreferencesKeys.CLIPBOARD_CLEAR_DELAY_MS] ?: 30_000L,
            showOtpDigitsGrouped = prefs[PreferencesKeys.SHOW_OTP_DIGITS_GROUPED] ?: true,
            copyOnTap = prefs[PreferencesKeys.COPY_ON_TAP] ?: false,
            hapticFeedback = prefs[PreferencesKeys.HAPTIC_FEEDBACK] ?: true,
            onboardingComplete = prefs[PreferencesKeys.ONBOARDING_COMPLETE] ?: false,
            lastSelectedGroupId = prefs[PreferencesKeys.LAST_SELECTED_GROUP_ID],
            backupReminderIntervalDays = prefs[PreferencesKeys.BACKUP_REMINDER_INTERVAL_DAYS] ?: 30,
        )
    }

    suspend fun setThemeMode(mode: ThemeMode) =
        dataStore.edit { it[PreferencesKeys.THEME_MODE] = mode.name }

    suspend fun setSortOrder(order: SortOrder) =
        dataStore.edit { it[PreferencesKeys.SORT_ORDER] = order.name }

    suspend fun setAppLockMethod(method: AppLockMethod) =
        dataStore.edit { it[PreferencesKeys.APP_LOCK_METHOD] = method.name }

    suspend fun setAutoLockTimeout(timeout: AppLockTimeout) =
        dataStore.edit { it[PreferencesKeys.AUTO_LOCK_TIMEOUT] = timeout.name }

    suspend fun setScreenshotBlocked(blocked: Boolean) =
        dataStore.edit { it[PreferencesKeys.SCREENSHOT_BLOCKED] = blocked }

    suspend fun setCopyOnTap(enabled: Boolean) =
        dataStore.edit { it[PreferencesKeys.COPY_ON_TAP] = enabled }

    suspend fun setHapticFeedback(enabled: Boolean) =
        dataStore.edit { it[PreferencesKeys.HAPTIC_FEEDBACK] = enabled }

    suspend fun setOnboardingComplete() =
        dataStore.edit { it[PreferencesKeys.ONBOARDING_COMPLETE] = true }

    suspend fun setLastSelectedGroupId(groupId: String?) = dataStore.edit {
        if (groupId != null) it[PreferencesKeys.LAST_SELECTED_GROUP_ID] = groupId
        else it.remove(PreferencesKeys.LAST_SELECTED_GROUP_ID)
    }
}