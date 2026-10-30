package com.example.playlist_maker_android_

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                PanelHeader()
                Menu()
            }
        }
    }
}
@Preview
@Composable
fun PanelHeader() {
    Row (
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp, bottom = 30.dp)
    ){
        Text(
            "Playlist Maker",
            fontWeight = FontWeight.Medium,
            style = TextStyle(color = Color.White, fontSize = 22.sp,),
        )
    }

}

@Composable
fun MenuItem(
    icon: Painter,
    text: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            style = TextStyle(fontSize = 22.sp),
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.arrow_light),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
@Preview
@Composable
fun Menu() {
    val context = LocalContext.current

    val items = listOf(
        Triple(R.drawable.search_light, R.string.search) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        },
        Triple(R.drawable.playlists_light, R.string.playlists) {},
        Triple(R.drawable.heart_light, R.string.favorite) {},
        Triple(R.drawable.settings_light, R.string.settings) {
            val intent = Intent(context, SettingsActivity::class.java)
            context.startActivity(intent)
        }
    )

    Column  {
        items.forEach { (icon, label, action) ->
            MenuItem(
                icon = painterResource(icon),
                text = stringResource(id = label),
                onClick = action
            )
        }
    }
}

