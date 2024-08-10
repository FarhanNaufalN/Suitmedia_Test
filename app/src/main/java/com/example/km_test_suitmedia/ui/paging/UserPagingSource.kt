// UserPagingSource.kt
package com.example.km_test_suitmedia.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.km_test_suitmedia.core.response.Data
import com.example.km_test_suitmedia.core.setup.API
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val api: API) : PagingSource<Int, Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: 1
        return try {
            val response = api.getUsers(page)
            val data = response.data ?: emptyList()
            val nextPage = if (page >= (response.total_pages ?: 1)) null else page + 1
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }
}
