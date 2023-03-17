package com.nauhalf.newsapp.screen.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nauhalf.newsapp.R
import com.nauhalf.newsapp.data.news.api.model.News
import com.nauhalf.newsapp.theme.NewsAppTheme
import com.nauhalf.newsapp.util.toDate
import com.nauhalf.newsapp.util.toFormat

@ExperimentalMaterial3Api
@ExperimentalGlideComposeApi
@Composable
fun NewsCard(
    news: News,
    modifier: Modifier = Modifier,
    onNewsClicked: (News) -> Unit,
) {
    Card(modifier = modifier.fillMaxWidth(),
        onClick = {
            onNewsClicked.invoke(news)
        }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
        ) {
            val gradient = Brush.verticalGradient(
                colors = listOf(
                    Color(0x80626262),
                    Color.Black.copy(alpha = 0.7f),
                ),
            )
            if (news.thumbnail.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            all = 16.dp
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_not_supported),
                        contentDescription = news.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    )
                    Text(
                        text = "Thumbnail Not Available",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                    )
                }

            } else {
                GlideImage(
                    model = news.thumbnail,
                    contentDescription = news.description,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
            )
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                    ),
                    text = news.title,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 8.dp,
                        ),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        text = news.author,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        text = news.publishedAt.toDate().toFormat(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End,
                    )
                }
            }
        }

    }
}

@ExperimentalMaterial3Api
@ExperimentalGlideComposeApi
@Preview(showBackground = false)
@Composable
fun NewsCardPreview() {
    NewsAppTheme {
        NewsCard(
            news = News(
                title = "European retailer spoils every last Samsung Galaxy A54 5G surprise - PhoneArena",
                description = "PhoneArena is the premium website for new phone information such as full specifications, in-depth reviews, latest news, carrier available and upcoming phones. It features advanced phone filter, visual size comparison and 360 degree views of all hot phones.",
                author = "PhoneArena is the premium website for new phone information",
                publishedAt = "2023-03-13T14:30:59Z"
            )
        ) {

        }
    }
}
