package com.tomergoldst.mediaapp.data

import com.tomergoldst.mediaapp.models.Entry
import org.junit.Assert
import org.junit.Test

class RepositoryUnitTest {

    private val mRemoteDataSource: DataSource = RemoteDataSourceMock()
    private val mRepository = Repository.getInstance(mRemoteDataSource)

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
