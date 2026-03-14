package com.example.praktam_2417051066

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.vectorResource
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.praktam_2417051066.model.Product
import com.example.praktam_2417051066.model.ProductSource
import com.example.praktam_2417051066.ui.theme.PrakTAM_2417051066Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2417051066Theme {
                DaftarProdukScreen()
            }
        }
    }
}

@Composable
fun DaftarProdukScreen() {

    val produk = ProductSource.dummyProduct

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        produk.forEach { item ->

            DetailScreen(product = item)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DetailScreen(product: Product) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Box {

                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.nama,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
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
                            if (isFavorite) Color.Red
                            else Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.nama,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(text = product.deskripsi)

            Text(text = "Rp ${product.harga}")

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pesan Sekarang")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DaftarProdukPreview() {
    PrakTAM_2417051066Theme {
        DaftarProdukScreen()
    }
}