@file:OptIn(androidx.media3.common.util.UnstableApi::class)

package com.example.assignment_1.ui

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun VideoPlayerScreen(videoUrl: String) {
    val context = LocalContext.current
    val decodedUrl = remember(videoUrl) {
        URLDecoder.decode(videoUrl, StandardCharsets.UTF_8.toString())
    }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }

    LaunchedEffect(decodedUrl) {
        Log.d("PLAYER", "play url = $decodedUrl")
        exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(decodedUrl)))
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                Log.e("PLAYER", "playback error", error)
            }
        }
        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = true
            }
        }
    )
}



















//@file:OptIn(androidx.media3.common.util.UnstableApi::class)
//package com.example.assignment_1.ui
//import androidx.media3.common.util.UnstableApi
//import android.net.Uri
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.media3.common.MediaItem
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.media3.exoplayer.ExoPlayer
//import androidx.media3.ui.PlayerView
//import java.net.URLDecoder
//import java.nio.charset.StandardCharsets
//
//
//@Composable
//fun VideoPlayerScreen(videoUrl: String) {
//    val context = LocalContext.current
//
//    // ✅ 解码 URL（核心修复点）
//    val decodedUrl = remember(videoUrl) {
//        URLDecoder.decode(videoUrl, StandardCharsets.UTF_8.toString())
//    }
//
//    // ✅ ExoPlayer 绑定 videoUrl
//    val exoPlayer = remember(decodedUrl) {
//        ExoPlayer.Builder(context).build().apply {
//            val mediaItem = MediaItem.Builder()
//                .setUri(Uri.parse(decodedUrl))
//                .build()
//
//            setMediaItem(mediaItem)
//            prepare()
//            playWhenReady = true
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    AndroidView(
//        modifier = Modifier.fillMaxSize(),
//        factory = {
//            PlayerView(context).apply {
//                player = exoPlayer
//                useController = true
//            }
//        }
//    )
//}











//package com.example.assignment_1.ui
//
//import android.net.Uri
//import androidx.annotation.OptIn
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.media3.common.MediaItem
//import androidx.media3.common.util.UnstableApi
//import androidx.media3.exoplayer.ExoPlayer
//import androidx.media3.ui.PlayerView
//
//@OptIn(UnstableApi::class)
//@Composable
//fun VideoPlayerScreen(videoUrl: String) {
//    val context = LocalContext.current
//
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
//            setMediaItem(mediaItem)
//            prepare()
//            playWhenReady = true
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    AndroidView(
//        factory = {
//            PlayerView(context).apply {
//                player = exoPlayer
//            }
//        },
//        modifier = Modifier.fillMaxSize()
//    )
//}
