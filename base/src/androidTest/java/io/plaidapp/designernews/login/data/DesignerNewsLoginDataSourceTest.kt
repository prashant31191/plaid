/*
 * Copyright 2018 Google, Inc.
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

package io.plaidapp.designernews.login.data

import android.content.Context
import android.support.test.InstrumentationRegistry.getInstrumentation
import io.plaidapp.base.designernews.data.api.model.User
import io.plaidapp.base.designernews.login.data.DesignerNewsLoginLocalDataSource
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Tests for [DesignerNewsLoginLocalDataSource] using shared preferences from instrumentation
 * context
 */
class DesignerNewsLoginDataSourceTest {

    private var sharedPreferences = getInstrumentation().context
            .getSharedPreferences("test", Context.MODE_PRIVATE)

    private var dataSource = DesignerNewsLoginLocalDataSource(sharedPreferences)

    @After
    fun tearDown() {
        // cleanup the shared preferences after every test
        sharedPreferences.edit().clear().commit()
    }

    @Test
    fun user_default() {
        // When getting the default user from the data source
        // Then it should be null
        assertNull(dataSource.user)
    }

    @Test
    fun user_set() {
        // Given a user
        val user = User(id = 3, displayName = "Plaidinium Plaidescu", portraitUrl = "www")

        // When inserting it in the data source
        dataSource.user = user

        // Then it can then be retrieved
        assertEquals(user, dataSource.user)
    }

    @Test
    fun logout() {
        // Given a user set
        val user = User(id = 3, displayName = "Plaidy Plaidinski", portraitUrl = "www")
        dataSource.user = user

        // When logging out
        dataSource.logout()

        // Then the user is null
        assertNull(dataSource.user)
    }
}
