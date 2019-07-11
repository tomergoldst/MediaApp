package com.tomergoldst.mediaapp.data

import com.tomergoldst.mediaapp.appModules
import com.tomergoldst.mediaapp.models.Entry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

class RepositoryUnitTest: KoinTest {

    private val mRemoteDataSource: DataSource = RemoteDataSourceMock()
    private val mRepository = Repository(mRemoteDataSource)

    @Before
    fun before() {
        startKoin {
            modules(appModules)
        }
    }

    @After
    fun after() {
        stopKoin()
    }


    /**
     * Very simple test to check that the repository logic works
     */
    @Test
    fun getEntries_fromRemote_returnListOfEntries() {
        mRepository.getMediaEntries(object : DataSource.GetMediaEntriesCallback{
            override fun onEntriesLoaded(entries: List<Entry>) {
                assert(entries.isNotEmpty())
            }

            override fun onDataNotAvailable() {
                Assert.fail("Should have call onEntriesLoaded")
            }
        })
    }


}
