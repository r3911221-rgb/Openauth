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

import io.openauth.authenticator.core.database.dao.AccountDao
import io.openauth.authenticator.core.database.entity.AccountEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val dao: AccountDao
) {
    fun observeAccounts(): Flow<List<AccountEntity>> = dao.observeAll()

    suspend fun getAccount(id: String): AccountEntity? = dao.getById(id)

    suspend fun save(account: AccountEntity) {
        dao.insert(account)
    }

    suspend fun delete(id: String) {
        dao.deleteById(id)
    }

    suspend fun updateFavorite(id: String, favorite: Boolean, timestamp: Long) {
        dao.setFavorite(id, favorite, timestamp)
    }
}