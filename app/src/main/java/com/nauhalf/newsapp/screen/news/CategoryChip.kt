package com.nauhalf.newsapp.screen.news

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nauhalf.newsapp.theme.NewsAppTheme

@ExperimentalMaterial3Api
@Composable
fun CategoryChip(
    modifier: Modifier = Modifier,
    category: Category,
    isSelected: Boolean = false,
    onClick: (Category) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            percent = 50,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) {
                Color(0xFFFFB3B6)
            } else {
                Color(0xFFF0F1FA)
            }
        ),
        colors = CardDefaults.cardColors(
            if (isSelected) {
                Color(0xFFFF3A44)
            } else {
                Color.White
            }
        ),
        onClick = {
            onClick.invoke(category)
        }
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            text = category.label,
            color = if (isSelected) {
                Color.White
            } else {
                Color.Black
            },
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun CategoryChipPreview() {
    NewsAppTheme {
        CategoryChip(
            category = Category(label = "Sport", value = "sport")
        ) {

        }
    }
}
