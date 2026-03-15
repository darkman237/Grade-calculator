import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

// ViewModel
class ProductViewModel : ViewModel() {
    val products = listOf(
        "Laptop", "Smartphone", "Headphones",
        "Keyboard", "Mouse", "Monitor"
    )
    var selectedProduct by mutableStateOf<String?>(null)
}

// Navigation state
enum class Screen { LIST, DETAIL }

@Composable
fun ProductApp(viewModel: ProductViewModel = ProductViewModel()) {
    var currentScreen by remember { mutableStateOf(Screen.LIST) }

    when (currentScreen) {
        Screen.LIST -> ProductListScreen(
            products = viewModel.products,
            onProductClick = { product ->
                viewModel.selectedProduct = product
                currentScreen = Screen.DETAIL
            }
        )
        Screen.DETAIL -> ProductDetailScreen(
            productName = viewModel.selectedProduct ?: "",
            onBack = { currentScreen = Screen.LIST }
        )
    }
}

@Composable
fun ProductListScreen(products: List<String>, onProductClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Products", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onProductClick(product) },
                    elevation = 4.dp
                ) {
                    Text(
                        text = product,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

@Composable
fun ProductDetailScreen(productName: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Product Details", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(productName, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { println("$productName added to cart!") }) {
            Text("Add to Cart")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = onBack) {
            Text("← Back")
        }
    }
}