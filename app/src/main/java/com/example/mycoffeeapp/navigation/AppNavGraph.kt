package com.example.mycoffeeapp.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycoffeeapp.data.local.LocalStorageService
import com.example.mycoffeeapp.local_storage.CartStorage
import com.example.mycoffeeapp.mock.MockData
import com.example.mycoffeeapp.screens.home.HomeScreen
import com.example.mycoffeeapp.screens.order.OrderScreen
import com.example.mycoffeeapp.screens.product.ProductDetailScreen
import kotlinx.coroutines.launch

// Tên các route trong app
object Routes {
  const val HOME = "home"
  const val CART = "cart"
  const val PRODUCT_DETAIL = "product_detail/{productId}"

  fun productDetail(productId: Int) = "product_detail/$productId"
}

@Composable
fun AppNavGraph(
  localStorage: LocalStorageService,
  navController: NavHostController = rememberNavController(),
) {
  val cartStorage = remember(localStorage) { CartStorage(localStorage) }
  val scope = rememberCoroutineScope()
  val context = LocalContext.current

  // Chuyển tab ở bottom bar. Favorites và Profile chưa có màn hình nên bỏ qua,
  // vì navigate tới route chưa khai báo sẽ làm app crash.
  val onBottomNavClick: (String) -> Unit = { route ->
    if (route == Routes.HOME || route == Routes.CART) {
      navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
      }
    }
  }

  NavHost(navController = navController, startDestination = Routes.HOME) {

    composable(Routes.HOME) {
      HomeScreen(
        onProductClick = { product ->
          navController.navigate(Routes.productDetail(product.id))
        },
        onBottomNavClick = onBottomNavClick,
      )
    }

    composable(Routes.CART) {
      val cartItems by cartStorage.cartItems.collectAsState(initial = null)
      // Chờ đọc xong dữ liệu để không hiện nhầm "giỏ trống" trong giây lát
      cartItems?.let { items ->
        OrderScreen(
          // Lấy lại Product mới nhất theo id: id ảnh trong R.drawable đã lưu
          // có thể không còn đúng sau khi build lại app
          items = items.mapNotNull { item ->
            MockData.productMockData.find { it.id == item.product.id }
              ?.let { product -> item.copy(product = product) }
          },
          onBackClick = { navController.popBackStack() },
          onChangeCount = { item, delta ->
            scope.launch { cartStorage.changeCount(item, delta) }
          },
          onBottomNavClick = onBottomNavClick,
        )
      }
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
          onAddToCart = { selectedProduct, selectedSize ->
            scope.launch {
              cartStorage.addToCart(selectedProduct, selectedSize)
              // Chỉ chạy tới đây khi đã lưu xong
              Toast.makeText(
                context,
                "Added ${selectedProduct.name} (${selectedSize.label}) to cart",
                Toast.LENGTH_SHORT,
              ).show()
            }
          },
        )
      }
    }
  }
}
