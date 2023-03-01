package com.example.acerestaurantmenucompose.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acerestaurantmenucompose.MainMenu


@Composable
fun PlayWithFormatting(name: String) {
    Box(
        modifier = Modifier
            .size(400.dp)
        //.background(androidx.compose.ui.graphics.Color.Gray)
    ) {
        Text(
            text = "Hello $name!",
            fontSize = 30.sp,
            color = Color.Cyan,
            modifier = Modifier
                .background(color = Color.Blue)
                .padding(16.dp)
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(500.dp)
        )
        Text(
            text = "Bottom Right Button",
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )

    }
}


@Composable
fun MenuListLazy()
{
    LazyColumn {
        // Add a single item
        item {
            Text(text = "First item")
        }

        // Add 5 items
        items(5) { index ->
            Text(text = "Item: $index")
        }

        // Add another single item
        item {
            Text(text = "Last item")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AceRestaurantMenuComposeTheme {
        PlayWithFormatting("Testing")
    }
}