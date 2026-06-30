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
import io.openauth.authenticator.core.database.entity.IconEntity

@Dao
interface IconDao {

    @Query("SELECT * FROM icons WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): IconEntity?

    @Query("SELECT * FROM icons WHERE issuer LIKE '%' || :issuer || '%'")
    suspend fun searchByIssuer(issuer: String): List<IconEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(icon: IconEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(icons: List<IconEntity>)

    @Delete
    suspend fun delete(icon: IconEntity)

    @Query("DELETE FROM icons WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM icons WHERE source = :source")
    suspend fun deleteBySource(source: String)
}