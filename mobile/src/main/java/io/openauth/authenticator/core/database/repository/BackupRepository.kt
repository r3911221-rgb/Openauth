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

package io.openauth.authenticator.core.database.repository

import io.openauth.authenticator.core.database.dao.BackupHistoryDao
import io.openauth.authenticator.core.database.entity.BackupHistoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackupRepository @Inject constructor(
    private val dao: BackupHistoryDao
) {
    fun observeHistory(): Flow<List<BackupHistoryEntity>> = dao.observeAll()

    suspend fun add(entity: BackupHistoryEntity) {
        dao.insert(entity)
    }

    suspend fun clear() {
        dao.deleteAll()
    }
}