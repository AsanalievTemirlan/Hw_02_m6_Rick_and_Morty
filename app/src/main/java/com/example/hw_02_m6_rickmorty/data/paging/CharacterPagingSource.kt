package com.example.hw_02_m6_rickmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.hw_02_m6_rickmorty.api.ApiService
import com.example.hw_02_m6_rickmorty.data.model.Character
import java.io.IOException

const val START_INDEX = 1

class CharacterPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        return try {
            val current = params.key ?: START_INDEX
            val previousKey = if (current == START_INDEX) null else current.minus(1)
            val response = (apiService.getCharacters(current).body()?.results) ?: emptyList()
            LoadResult.Page(
                data = response,
                prevKey = previousKey,
                nextKey = current.plus(1)
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition!!)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
