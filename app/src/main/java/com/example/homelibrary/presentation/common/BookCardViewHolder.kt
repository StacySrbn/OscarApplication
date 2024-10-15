package com.example.homelibrary.presentation.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import com.example.homelibrary.domain.model.Book
import com.example.homelibrary.R
import com.example.homelibrary.presentation.Dimens

@Composable
fun BookCardViewHolder(
    book: Book,
    lastItemEndPadding: Dp
){
    Column {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = Dimens.MediumPadding, end = lastItemEndPadding)
                .clickable { }
        ) {
            Image(
                modifier = Modifier
                    .height(184.dp)
                    .width(130.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = rememberAsyncImagePainter(book.coverUrl),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
            Text(
                text = book.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = book.author.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.teal_main),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}