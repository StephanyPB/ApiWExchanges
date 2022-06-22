package com.example.apiwexchanges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apiwexchanges.data.remote.dto.Exchange
import com.example.apiwexchanges.model.ExchangeViewModel
import com.example.apiwexchanges.ui.theme.ApiWExchangesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiWExchangesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ConsultaExchangeScreen()
                }
            }
        }
    }
}

@Composable
fun ConsultaExchangeScreen(viewModel: ExchangeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val ScaffoldState = rememberScaffoldState()

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Consulta Exchanges API") })
                },
        scaffoldState = ScaffoldState
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.exchange) { exchange ->
                    ExchangeItem(exchange = exchange, {})
                }
            }
            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Composable
fun ExchangeItem(
    exchange: Exchange, onClick : (Exchange) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(exchange) }
        .padding(16.dp)
    ) {
        Text(
            "${exchange.name}",
            style = MaterialTheme.typography.h5,
            overflow = TextOverflow.Ellipsis)

        Text("${exchange.description}" ,
            style = MaterialTheme.typography.body2,)

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                if(exchange.active) "Activa" else "Inactiva",
                color = if(exchange.active) Color.Green else Color.Red ,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2,
            )
            Text ("${exchange.last_updated}")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ApiWExchangesTheme {
        ConsultaExchangeScreen()
    }
}