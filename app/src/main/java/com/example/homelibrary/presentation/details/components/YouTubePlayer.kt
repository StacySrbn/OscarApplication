package com.example.homelibrary.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.example.homelibrary.util.Dimens.MediumPadding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubePlayer(
    youTubeTrailerId: String,
    lifecycleOwner: LifecycleOwner,
    isLoading: Boolean
){

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            ShimmerYouTubePlayer(
                isLoading = isLoading,
                contentAfterLoading = {
                    AndroidView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MediumPadding)
                            .clip(RoundedCornerShape(16.dp)),
                        factory = { context ->
                            YouTubePlayerView(context).apply {
                                lifecycleOwner.lifecycle.addObserver(this)
                                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        super.onReady(youTubePlayer)
                                        youTubePlayer.cueVideo(youTubeTrailerId, 0f)
                                    }
                                })
                            }
                        },
                        update = { view ->
                            view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    super.onReady(youTubePlayer)
                                    youTubePlayer.cueVideo(youTubeTrailerId, 0f)
                                }
                            })
                        }

                    )
                }
            )

        }
    }
}