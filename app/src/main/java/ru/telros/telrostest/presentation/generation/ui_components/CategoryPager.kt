package ru.telros.telrostest.presentation.generation.ui_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import ru.telros.telrostest.R
import ru.telros.telrostest.presentation.generation.ui_components.pages.CharsPage
import ru.telros.telrostest.presentation.generation.ui_components.pages.NumbersPage
import ru.telros.telrostest.presentation.generation.ui_components.pages.SymbolPage
import ru.telros.telrostest.theme.Resources.background
import ru.telros.telrostest.theme.Resources.typography

@Composable
fun CategoryPager(
    onAddChar: (Char) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = background.primary
    ) {
        Tab(
            text = {
                Text(
                    stringResource(R.string.letters),
                    color = background.textColor,
                    style = typography.title
                )
            },
            selected = pagerState.currentPage == 0,
            onClick = {
                scope.launch {
                    pagerState.scrollToPage(page = 0)
                }
            },
        )
        Tab(
            text = {
                Text(
                    stringResource(R.string.numbers),
                    color = background.textColor,
                    style = typography.title
                )
            },
            selected = pagerState.currentPage == 1,
            onClick = {
                scope.launch {
                    pagerState.scrollToPage(page = 1)
                }
            },
        )
        Tab(
            text = {
                Text(
                    stringResource(R.string.selectedSymbols),
                    color = background.textColor,
                    style = typography.title
                )
            },
            selected = pagerState.currentPage == 2,
            onClick = {
                scope.launch {
                    pagerState.scrollToPage(page = 2)
                }
            },
        )
    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth(),
    ) { page ->
        when (page) {
            0 -> CharsPage(onAddChar)
            1 -> NumbersPage(onAddChar)
            2 -> SymbolPage(onAddChar)
        }
    }
}