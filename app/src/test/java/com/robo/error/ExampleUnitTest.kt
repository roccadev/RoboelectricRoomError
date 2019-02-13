package com.robo.error

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    private lateinit var appDatabase: AppDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @Test
    fun room_compiles() = runBlocking {
        val myEntity = MyEntity(0, "title", "desc")
        val ids = appDatabase.myDao.insert(myEntity)
        assertArrayEquals(longArrayOf(1L), ids)
        val entities = appDatabase.myDao.selectAll()
        assertEquals(myEntity, entities[0])
        val r = appDatabase.myDao.update(myEntity.apply { title = "title1" })
        assertEquals(1, r)
        appDatabase.myDao.delete(myEntity)
        assertEquals(0, appDatabase.myDao.selectAll().size)
        Unit
    }
}
