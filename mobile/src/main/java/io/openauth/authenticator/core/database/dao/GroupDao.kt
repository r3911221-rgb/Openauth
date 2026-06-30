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
import io.openauth.authenticator.core.database.entity.AccountGroupCrossRef
import io.openauth.authenticator.core.database.entity.GroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {

    @Query("SELECT * FROM groups ORDER BY sort_position ASC")
    fun observeAll(): Flow<List<GroupEntity>>

    @Query("SELECT * FROM groups WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): GroupEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(group: GroupEntity)

    @Update
    suspend fun update(group: GroupEntity)

    @Delete
    suspend fun delete(group: GroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccountToGroup(crossRef: AccountGroupCrossRef)

    @Delete
    suspend fun removeAccountFromGroup(crossRef: AccountGroupCrossRef)

    @Query("DELETE FROM account_group_cross_ref WHERE account_id = :accountId")
    suspend fun removeAllFromAccount(accountId: String)

    @Query(
        """
        SELECT groups.* FROM groups 
        INNER JOIN account_group_cross_ref 
        ON groups.id = account_group_cross_ref.group_id 
        WHERE account_group_cross_ref.account_id = :accountId
        """
    )
    suspend fun getGroupsForAccount(accountId: String): List<GroupEntity>
}