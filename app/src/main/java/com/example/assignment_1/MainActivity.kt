package com.example.assignment_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment_1.ui.PostDetailScreen
import com.example.assignment_1.ui.VideoListScreen
import com.example.assignment_1.ui.VideoPlayerScreen
import com.example.assignment_1.ui.theme.Assignment_1Theme
import com.example.assignment_1.viewmodel.VideoViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment_1Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: VideoViewModel = viewModel()
    val videos by viewModel.videos.collectAsState()

    NavHost(navController = navController, startDestination = "video_list") {
        composable("video_list") {
            VideoListScreen(
                viewModel = viewModel,
                onVideoClick = { video ->
                    if (video.isLive) {
                        // 使用查询参数传递 URL，避免路径匹配问题
                        val encodedUrl = URLEncoder.encode(video.videoUrl, StandardCharsets.UTF_8.toString())
                        navController.navigate("video_player?videoUrl=$encodedUrl")
                    } else {
                        navController.navigate("post_detail/${video.id}")
                    }
                }
            )
        }
        
        // 修改路由定义，使用查询参数格式: ?videoUrl={videoUrl}
        composable(
            route = "video_player?videoUrl={videoUrl}",
            arguments = listOf(
                navArgument("videoUrl") { 
                    type = NavType.StringType 
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val videoUrl = backStackEntry.arguments?.getString("videoUrl") ?: ""
            VideoPlayerScreen(videoUrl = videoUrl)
        }
        
        composable(
            route = "post_detail/{videoId}",
            arguments = listOf(navArgument("videoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId")
            val video = videos.find { it.id == videoId }
            
            if (video != null) {
                PostDetailScreen(
                    video = video,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
