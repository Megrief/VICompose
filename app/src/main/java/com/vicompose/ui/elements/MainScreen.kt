package com.vicompose.ui.elements

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.vicompose.presetation.SearchViewModel
import com.vicompose.ui.elements.grid.SearchScreenContainer
import com.vicompose.ui.elements.pager.ImagePagerContainer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen() {
    val viewModel: SearchViewModel = koinViewModel()

    SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {

        val state = viewModel.uiState.collectAsState()

        val navController = rememberNavController()
        val boundsTransform = { _: Rect, _: Rect -> tween<Rect>(500) }
        val images = state.value.imageFlow.collectAsLazyPagingItems()

        NavHost(navController = navController, startDestination = "search/{position}") {
            composable(
                route = "search/{position}",
                arguments = listOf(navArgument("position") { type = NavType.IntType})
            ) { backStackEntry  ->

                val position = backStackEntry.arguments?.getInt("position")
                    ?: state.value.position

                SearchScreenContainer(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "image-$position"),
                        animatedVisibilityScope = this@composable,
                        boundsTransform = boundsTransform
                    ),
                    navigate = { newPosition ->
                        viewModel.onNavigate(newPosition)
                        navController.navigate("pager/$newPosition")
                    },
                    images  = images,
                    searchState =
                    state.value.savedQuery,
                    onSearch = { query -> viewModel.search(query) },
                    position = position
                )
            }

            composable(
                route = "pager/{position}",
                arguments = listOf(navArgument("position") { type = NavType.IntType})
            )  { backStackEntry ->
                val position = backStackEntry.arguments?.getInt("position")
                    ?: state.value.position

                ImagePagerContainer(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "image-$position"),
                        animatedVisibilityScope = this@composable,
                        boundsTransform = boundsTransform
                    ),
                    position = position,
                    onOpenInWeb = { context, url -> viewModel.openInWeb(context, url) },
                    navigate = { newPosition ->
                        viewModel.onNavigate(newPosition)
                        navController.navigate("search/$newPosition")
                    },
                    images = images
                )
            }
        }
    }

}