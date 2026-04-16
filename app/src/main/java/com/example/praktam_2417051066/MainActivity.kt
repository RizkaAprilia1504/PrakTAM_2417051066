package com.example.praktam_2417051066

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktam_2417051066.model.Product
import com.example.praktam_2417051066.model.ProductSource
import com.example.praktam_2417051066.ui.theme.PrakTAM_2417051066Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2417051066Theme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavController) {
    NavHost(
        navController = navController as androidx.navigation.NavHostController,
        startDestination = "home"
    ) {
        composable("home") {
            DaftarProdukScreen(navController)
        }
        composable("detail/{nama}") { backStackEntry ->
            val nama = backStackEntry.arguments?.getString("nama")
            val product = ProductSource.dummyProduct.find { it.nama == nama }

            if (product != null) {
                DetailScreen(product = product, navController = navController, isFullScreen = true)
            }
        }
    }
}

@Composable
fun DaftarProdukScreen(navController: NavController) {
    val produk = ProductSource.dummyProduct

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Rekomendasi",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(produk) { item ->
                    ProductRowItem(product = item, navController = navController)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Semua Produk",
                style = MaterialTheme.typography.titleLarge
            )
        }

        items(produk) { item ->
            ProductItem(product = item, navController = navController)
        }
    }
}

@Composable
fun ProductRowItem(product: Product, navController: NavController) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .clickable {
                navController.navigate("detail/${product.nama}")
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.nama,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = product.nama,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Rp ${product.harga}",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail/${product.nama}")
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.nama,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = product.nama,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Rp ${product.harga}",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun DetailScreen(product: Product, navController: NavController, isFullScreen: Boolean = false) {
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var isFavorite by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column {
                Box {
                    Image(
                        painter = painterResource(id = product.imageRes),
                        contentDescription = product.nama,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )

                    IconButton(
                        onClick = { isFavorite = !isFavorite },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector =
                                if (isFavorite) Icons.Filled.Favorite
                                else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint =
                                if (isFavorite) MaterialTheme.colorScheme.primary
                                else Color.White
                        )
                    }
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = product.nama,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = product.deskripsi,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Rp ${product.harga}",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (isFullScreen) {
                                coroutineScope.launch {
                                    isLoading = true
                                    delay(2000)
                                    snackbarHostState.showSnackbar(
                                        "Pesanan ${product.nama} berhasil diproses!"
                                    )
                                    isLoading = false
                                }
                            } else {
                                navController.navigate("detail/${product.nama}")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Memproses...")
                        } else {
                            Text(if (isFullScreen) "Pesan Sekarang" else "Lihat Detail")
                        }
                    }

                    if (isFullScreen) {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isLoading
                        ) {
                            Text("Kembali")
                        }
                    }
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    PrakTAM_2417051066Theme {
        val navController = rememberNavController()
        DaftarProdukScreen(navController)
    }
}