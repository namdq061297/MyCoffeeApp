package com.example.mycoffeeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycoffeeapp.mock.MockData
import com.example.mycoffeeapp.screens.home.HomeScreen
import com.example.mycoffeeapp.screens.product.ProductDetailScreen

// Tên các route trong app
object Routes {
    const val HOME = "home"
    const val PRODUCT_DETAIL = "product_detail/{productId}"

    fun productDetail(productId: Int) = "product_detail/$productId"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.HOME) {

        composable(Routes.HOME) {
            HomeScreen(
                onProductClick = { product ->
                    navController.navigate(Routes.productDetail(product.id))
                },
            )
        }

        composable(
            route = Routes.PRODUCT_DETAIL,
            arguments = listOf(navArgument("productId") { type = NavType.IntType }),
        ) { backStackEntry ->
            // Chỉ truyền id qua route, rồi tìm lại sản phẩm từ nguồn dữ liệu
            val productId = backStackEntry.arguments?.getInt("productId")
            val product = MockData.productMockData.find { it.id == productId }

            if (product == null) {
                // Không tìm thấy sản phẩm → quay về màn trước (side effect nên đặt trong LaunchedEffect)
                LaunchedEffect(Unit) { navController.popBackStack() }
            } else {
                ProductDetailScreen(
                    product = product,
                    onBackClick = { navController.popBackStack() },
                )
            }
        }
    }
}
