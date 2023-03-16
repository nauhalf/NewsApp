@file:OptIn(
    ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.nauhalf.newsapp.screen.news

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.nauhalf.newsapp.component.ErrorPlaceholder
import com.nauhalf.newsapp.data.news.api.model.News
import com.nauhalf.newsapp.screen.NewsRoute

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
    navController: NavController,
) {
    val paging = viewModel.newsPaging.collectAsLazyPagingItems()
    val categories by viewModel.categories.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        NewsCategory(
            categories = categories,
            selectedCategory = selectedCategory,
            onSelectedCategoryChanged = viewModel::setSelectedCategory,
        )
        NewsList(
            pagingItems = paging,
            onNewsClicked = {
                if (it.url.isNotEmpty()) {
                    navController.navigate("${NewsRoute.DETAIL_NEWS.path}?title=${it.title}&url=${it.url}")
                } else {
                    Toast.makeText(context, "Artikel tidak tersedia", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

}

@Composable
fun NewsCategory(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    selectedCategory: Category,
    onSelectedCategoryChanged: (Category) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) {
            CategoryChip(
                category = it,
                isSelected = it.value == selectedCategory.value,
                onClick = onSelectedCategoryChanged
            )
        }
    }
}

@Composable
fun NewsList(
    pagingItems: LazyPagingItems<News>,
    modifier: Modifier = Modifier,
    onNewsClicked: (News) -> Unit,
) {
    Box(modifier = modifier) {
        when (val state = pagingItems.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ErrorPlaceholder(
                        modifier = Modifier.fillMaxSize(),
                        text = state.error.message ?: "General Error",
                    ) {
                        pagingItems.refresh()
                    }
                }
            }
            is LoadState.Loading -> { // Loading UI
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    CircularProgressIndicator(color = Color.Black)
                }
            }
            is LoadState.NotLoading -> {
                LazyColumn {
                    itemsIndexed(pagingItems) { index, item ->
                        item?.let {
                            NewsCard(
                                news = item,
                                modifier = Modifier.padding(horizontal = 16.dp),
                                onNewsClicked = onNewsClicked
                            )
                            if (index < pagingItems.itemCount - 1) {
                                Divider(
                                    modifier = Modifier.height(8.dp),
                                    color = Color.Transparent
                                )
                            }
                        }
                    }

                    when (val loadState = pagingItems.loadState.append) { // Pagination
                        is LoadState.Error -> {
                            item {
                                ErrorPlaceholder(
                                    text = loadState.error.message ?: "General Error",
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                        .padding(
                                            vertical = 16.dp,
                                            horizontal = 16.dp
                                        )
                                ) {
                                    pagingItems.retry()
                                }
                            }
                        }
                        is LoadState.Loading -> { // Pagination Loading UI
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(all = 16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    CircularProgressIndicator(color = Color.Black)
                                }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}
