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

package io.openauth.authenticator.core.database.dao

import androidx.room.*
import io.openauth.authenticator.core.database.entity.BackupHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BackupHistoryDao {

    @Query("SELECT * FROM backup_history ORDER BY created_at DESC")
    fun observeAll(): Flow<List<BackupHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: BackupHistoryEntity)

    @Query("DELETE FROM backup_history WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM backup_history")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM backup_history")
    suspend fun count(): Int
}