# Assignment_1 - Simplified TikTok App

## Project Introduction
This is an Android application that simulates a simplified TikTok-style video feed. It features a staggered grid layout (waterfall flow) for the video list and allows users to click on a video to play it.

## Features
- **Waterfall Video List**: Displays videos in a staggered grid layout with 2 columns.
- **Video Details**: Shows video thumbnail, title (long titles truncated), author, and a "LIVE" badge if applicable.
- **Network Requests**: Fetches video data from a remote JSON API.
- **Video Playback**: Plays the selected video using ExoPlayer.
- **Image Loading**: Efficiently loads images using Coil with crossfade animations.

## Tech Stack
- **Language**: Kotlin
- **UI Toolkit**: Jetpack Compose (Material3)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Networking**: Retrofit + Gson
- **Image Loading**: Coil
- **Video Player**: Media3 ExoPlayer
- **Navigation**: Jetpack Navigation Compose

## Setup
1. Clone the repository.
2. Open in Android Studio (Ladybug or newer recommended).
3. Sync Gradle.
4. Run on an Emulator or Device (Internet connection required).

## API
- Data Source: `https://gist.githubusercontent.com/poudyalanil/ca84582cbeb4fc123a13290a586da925/raw/14a27bd0bcd0cd323b35ad79cf3b493dddf6216b/videos.json`
