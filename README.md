# Assignment_1

一个使用 Jetpack Compose 构建的视频信息流 App：
- 首页瀑布流展示视频卡片（标题、缩略图、作者、Live 标识）
- 点击普通视频进入详情页
- 点击 Live 视频进入播放器页面（Media3 ExoPlayer）

## Tech Stack
- Kotlin + Jetpack Compose
- Retrofit2 + Gson（网络请求）
- Coil（加载缩略图/头像）
- Navigation Compose（页面跳转）
- Media3 ExoPlayer（视频播放）

## Features
- ✅ 视频列表：LazyVerticalStaggeredGrid 双列瀑布流
- ✅ 网络加载：加载中/错误状态展示
- ✅ 图片加载：Coil 异步加载缩略图
- ✅ Live 播放：ExoPlayer 播放 HLS / MP4（取决于数据源）
- ✅ 本地头像：drawable 内置头像，按作者稳定分配

## Requirements
- Android Studio (建议最新版稳定版)
- minSdk: 26
- targetSdk: 36
- 需要联网权限：INTERNET

## Setup & Run
1. 打开项目并 Sync Gradle
2. 确保 Manifest 有网络权限：
   - `<uses-permission android:name="android.permission.INTERNET" />`
3. 运行 app

## Important Dependency Notes (Live 播放黑屏修复)
如果 Live 视频是 `.m3u8`（HLS），必须添加 HLS 模块：

- Version Catalog (gradle/libs.versions.toml)：
  - 添加：
    `androidx-media3-exoplayer-hls = { group="androidx.media3", name="media3-exoplayer-hls", version.ref="media3" }`

- app/build.gradle.kts：
  - 添加：
    `implementation(libs.androidx.media3.exoplayer.hls)`

或者直接：
`implementation("androidx.media3:media3-exoplayer-hls:1.2.0")`

## Local Avatar (drawable) 使用说明
由于数据源不包含头像 URL，项目使用本地头像资源作为作者头像。

### 放置路径
将头像图片放入：
- `app/src/main/res/drawable/`

### 命名规则（必须）
- 全小写
- 只能包含 a-z / 0-9 / _
- 不能以数字开头
示例：
- avatar_01.jpg
- avatar_02.jpg
- avatar_03.jpg

### 分配逻辑
在 VideoListScreen 中对 author 做 distinct 后按顺序分配头像：
- `authorToAvatar = authors.distinct().mapIndexed { index, author -> author to avatarResList[index % size] }`

然后将 `avatarResId` 传入 `VideoItem(video, avatarResId, onClick)`。

## 项目结构（简单版）
- data/
  - Video.kt            数据模型
  - ApiService.kt       Retrofit API
  - NetworkModule.kt    Retrofit 实例
- viewmodel/
  - VideoViewModel.kt   拉取视频、状态管理
- ui/
  - VideoListScreen.kt  首页列表 & VideoItem
  - VideoPlayerScreen.kt Live 播放页（Media3）
  - PostDetailScreen.kt 详情页（非 Live）

## 项目结构（完整版）
|   .gitignore
|   build.gradle.kts
|   gradle.properties
|   gradlew
|   gradlew.bat
|   local.properties
|   README.md
|   settings.gradle.kts
|   
+---.gradle
|   |   config.properties
|   |   file-system.probe
|   |   
|   +---8.13
|   |   |   gc.properties
|   |   |   
|   |   +---checksums
|   |   |       checksums.lock
|   |   |       md5-checksums.bin
|   |   |       sha1-checksums.bin
|   |   |       
|   |   +---executionHistory
|   |   |       executionHistory.bin
|   |   |       executionHistory.lock
|   |   |       
|   |   +---expanded
|   |   +---fileChanges
|   |   |       last-build.bin
|   |   |       
|   |   +---fileHashes
|   |   |       fileHashes.bin
|   |   |       fileHashes.lock
|   |   |       resourceHashesCache.bin
|   |   |       
|   |   \---vcsMetadata
|   +---buildOutputCleanup
|   |       buildOutputCleanup.lock
|   |       cache.properties
|   |       outputFiles.bin
|   |       
|   \---vcs-1
|           gc.properties
|           
+---.idea
|   |   .gitignore
|   |   AndroidProjectSystem.xml
|   |   compiler.xml
|   |   deploymentTargetSelector.xml
|   |   deviceManager.xml
|   |   gradle.xml
|   |   migrations.xml
|   |   misc.xml
|   |   runConfigurations.xml
|   |   vcs.xml
|   |   workspace.xml
|   |   
|   +---caches
|   \---inspectionProfiles
|           Project_Default.xml
|           
+---.kotlin
|   \---sessions
+---app
|   |   .gitignore
|   |   build.gradle.kts
|   |   proguard-rules.pro
|   |   
|   +---build
|   |   +---generated
|   |   |   +---res
|   |   |   |   +---pngs
|   |   |   |   |   \---debug
|   |   |   |   \---resValues
|   |   |   |       \---debug
|   |   |   \---updated_navigation_xml
|   |   |       \---debug
|   |   +---intermediates
|   |   |   +---aar_metadata_check
|   |   |   |   \---debug
|   |   |   |       \---checkDebugAarMetadata
|   |   |   +---annotation_processor_list
|   |   |   |   \---debug
|   |   |   |       \---javaPreCompileDebug
|   |   |   |               annotationProcessors.json
|   |   |   |               
|   |   |   +---apk
|   |   |   |   \---debug
|   |   |   |           app-debug.apk
|   |   |   |           output-metadata.json
|   |   |   |           
|   |   |   +---apk_ide_redirect_file
|   |   |   |   \---debug
|   |   |   |       \---createDebugApkListingFileRedirect
|   |   |   |               redirect.txt
|   |   |   |               
|   |   |   +---app_metadata
|   |   |   |   \---debug
|   |   |   |       \---writeDebugAppMetadata
|   |   |   |               app-metadata.properties
|   |   |   |               
|   |   |   +---assets
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugAssets
|   |   |   +---compatible_screen_manifest
|   |   |   |   \---debug
|   |   |   |       \---createDebugCompatibleScreenManifests
|   |   |   |               output-metadata.json
|   |   |   |               
|   |   |   +---compiled_navigation_res
|   |   |   |   \---debug
|   |   |   |       \---compileDebugNavigationResources
|   |   |   +---compile_and_runtime_not_namespaced_r_class_jar
|   |   |   |   \---debug
|   |   |   |       \---processDebugResources
|   |   |   |               R.jar
|   |   |   |               
|   |   |   +---compressed_assets
|   |   |   |   \---debug
|   |   |   |       \---compressDebugAssets
|   |   |   |           \---out
|   |   |   +---data_binding_layout_info_type_merge
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugResources
|   |   |   |           \---out
|   |   |   +---data_binding_layout_info_type_package
|   |   |   |   \---debug
|   |   |   |       \---packageDebugResources
|   |   |   |           \---out
|   |   |   +---desugar_graph
|   |   |   |   \---debug
|   |   |   |       \---dexBuilderDebug
|   |   |   |           \---out
|   |   |   |               +---currentProject
|   |   |   |               |   +---dirs_bucket_0
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---dirs_bucket_1
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---dirs_bucket_2
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---dirs_bucket_3
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---dirs_bucket_4
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---dirs_bucket_5
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---jar_a50f9c7a63780112d22d7258482afcf059c0794722d1a9201cab875ed77c981d_bucket_0
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---jar_a50f9c7a63780112d22d7258482afcf059c0794722d1a9201cab875ed77c981d_bucket_1
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---jar_a50f9c7a63780112d22d7258482afcf059c0794722d1a9201cab875ed77c981d_bucket_2
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---jar_a50f9c7a63780112d22d7258482afcf059c0794722d1a9201cab875ed77c981d_bucket_3
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   +---jar_a50f9c7a63780112d22d7258482afcf059c0794722d1a9201cab875ed77c981d_bucket_4
|   |   |   |               |   |       graph.bin
|   |   |   |               |   |       
|   |   |   |               |   \---jar_a50f9c7a63780112d22d7258482afcf059c0794722d1a9201cab875ed77c981d_bucket_5
|   |   |   |               |           graph.bin
|   |   |   |               |           
|   |   |   |               +---externalLibs
|   |   |   |               +---mixedScopes
|   |   |   |               \---otherProjects
|   |   |   +---dex
|   |   |   |   \---debug
|   |   |   |       +---mergeExtDexDebug
|   |   |   |       |       classes.dex
|   |   |   |       |       classes2.dex
|   |   |   |       |       classes3.dex
|   |   |   |       |       
|   |   |   |       +---mergeLibDexDebug
|   |   |   |       |   +---0
|   |   |   |       |   +---1
|   |   |   |       |   +---10
|   |   |   |       |   +---11
|   |   |   |       |   +---12
|   |   |   |       |   +---13
|   |   |   |       |   +---14
|   |   |   |       |   +---15
|   |   |   |       |   +---2
|   |   |   |       |   +---3
|   |   |   |       |   +---4
|   |   |   |       |   +---5
|   |   |   |       |   +---6
|   |   |   |       |   +---7
|   |   |   |       |   +---8
|   |   |   |       |   \---9
|   |   |   |       \---mergeProjectDexDebug
|   |   |   |           +---0
|   |   |   |           |       classes.dex
|   |   |   |           |       
|   |   |   |           +---1
|   |   |   |           |       classes.dex
|   |   |   |           |       
|   |   |   |           +---10
|   |   |   |           +---11
|   |   |   |           +---12
|   |   |   |           +---13
|   |   |   |           |       classes.dex
|   |   |   |           |       
|   |   |   |           +---14
|   |   |   |           +---15
|   |   |   |           +---2
|   |   |   |           +---3
|   |   |   |           +---4
|   |   |   |           +---5
|   |   |   |           +---6
|   |   |   |           +---7
|   |   |   |           |       classes.dex
|   |   |   |           |       
|   |   |   |           +---8
|   |   |   |           |       classes.dex
|   |   |   |           |       
|   |   |   |           \---9
|   |   |   |                   classes.dex
|   |   |   |                   
|   |   |   +---dex_archive_input_jar_hashes
|   |   |   |   \---debug
|   |   |   |       \---dexBuilderDebug
|   |   |   |               out
|   |   |   |               
|   |   |   +---dex_number_of_buckets_file
|   |   |   |   \---debug
|   |   |   |       \---dexBuilderDebug
|   |   |   |               out
|   |   |   |               
|   |   |   +---duplicate_classes_check
|   |   |   |   \---debug
|   |   |   |       \---checkDebugDuplicateClasses
|   |   |   +---external_file_lib_dex_archives
|   |   |   |   \---debug
|   |   |   |       \---desugarDebugFileDependencies
|   |   |   +---external_libs_dex_archive
|   |   |   |   \---debug
|   |   |   |       \---dexBuilderDebug
|   |   |   |           \---out
|   |   |   +---external_libs_dex_archive_with_artifact_transforms
|   |   |   |   \---debug
|   |   |   |       \---dexBuilderDebug
|   |   |   |           \---out
|   |   |   +---incremental
|   |   |   |   +---debug
|   |   |   |   |   +---mergeDebugResources
|   |   |   |   |   |   |   compile-file-map.properties
|   |   |   |   |   |   |   merger.xml
|   |   |   |   |   |   |   
|   |   |   |   |   |   +---merged.dir
|   |   |   |   |   |   \---stripped.dir
|   |   |   |   |   \---packageDebugResources
|   |   |   |   |       |   compile-file-map.properties
|   |   |   |   |       |   merger.xml
|   |   |   |   |       |   
|   |   |   |   |       +---merged.dir
|   |   |   |   |       \---stripped.dir
|   |   |   |   +---debug-mergeJavaRes
|   |   |   |   |   |   merge-state
|   |   |   |   |   |   
|   |   |   |   |   \---zip-cache
|   |   |   |   |           +arUdN1tnAPNGUBbeg+BBA==
|   |   |   |   |           +LAy_TAb9yG_zPh73qFCig==
|   |   |   |   |           +olIAN3pebw2U6KSr8zotw==
|   |   |   |   |           +sUutMu5DdTjf3khoqN7cg==
|   |   |   |   |           02kQFcYFrGnspehB9Tj+DQ==
|   |   |   |   |           0KTsTAmX_loJHzs5mf6QRA==
|   |   |   |   |           1mFjzq2gaFtfOFvk3lNvqQ==
|   |   |   |   |           1QHpMJNIZe0Xyi0R0nFwvA==
|   |   |   |   |           1u8bgluewSpUGxIdl+I6ww==
|   |   |   |   |           2nzY5SIbRFgYR6CCzBz2hA==
|   |   |   |   |           2YTYJNb8YS0DY2hnclVLsA==
|   |   |   |   |           3DvQdp9hyVsKxAJnziuabQ==
|   |   |   |   |           3tx2Ihn7lt0qPR1XGphfCA==
|   |   |   |   |           5FnZBknJWKJzELHouNbeJA==
|   |   |   |   |           5vYsmyrJDRN4kFb1poJ+1w==
|   |   |   |   |           5wmmirG5VWMZ+SZgQ5nKUw==
|   |   |   |   |           5xSv0jRtv51b4rDeP2wkbw==
|   |   |   |   |           6GonxnQV9QdgDnruxnLDsQ==
|   |   |   |   |           8wLBraKyqFeZz+X4ke3lmg==
|   |   |   |   |           9BeIPhh3UP0GX78GL3qccg==
|   |   |   |   |           9cs1xXe_CA3BMSMwRO4EkA==
|   |   |   |   |           9KMoWq_Vvgc5W04q0X2nSA==
|   |   |   |   |           a3cpoyQw5mNoLKIIFAqPng==
|   |   |   |   |           a3wxDjd3RNkwxR587k3S0g==
|   |   |   |   |           A5b_eM4+cCNtc0pIEAveuQ==
|   |   |   |   |           Aiw5x2ry634DckxlhoK+rg==
|   |   |   |   |           au+6VuOAGBOjd2s6EM4mMA==
|   |   |   |   |           bXS+PxCIU1RveTigixj5cA==
|   |   |   |   |           C5+KBdv_CErqLUOUoOyhlQ==
|   |   |   |   |           ccY2Zzs02+Hsc1YRAEsHiQ==
|   |   |   |   |           cX2YV52ZfjTlurnogCIruw==
|   |   |   |   |           cY+lcu93cfFrcqvcChuqlw==
|   |   |   |   |           D1t1tPRphMcow20HQWexsQ==
|   |   |   |   |           dklbcttsyfpjmwjkMbVPew==
|   |   |   |   |           dR6gHj9iiFNa6jyvvhmOOQ==
|   |   |   |   |           DU3SQUfu_jaSIfLW0h2Wtw==
|   |   |   |   |           e49oVuiAyK7pfEXHxIWoIg==
|   |   |   |   |           evvJkFmK0QmfvarOhBmgAA==
|   |   |   |   |           FAhuHmRR+xxIDihmtkLZTQ==
|   |   |   |   |           FiJW_e_Mn7hnq5cDUQvcZw==
|   |   |   |   |           fpaK17kN+NHYbzyCw7OIHA==
|   |   |   |   |           Frii3PnyRoU55u0hi8xOBg==
|   |   |   |   |           g8G9n8Piy0gGtmzr2M7+3A==
|   |   |   |   |           gEmubnppKsCD9WtQ6Qwsfg==
|   |   |   |   |           gLotIgp6mQ2TGRBpdfcrkQ==
|   |   |   |   |           gZT+_2HqdvCYRrNGgsLK4g==
|   |   |   |   |           HmI64P7hNBeOyrq3jmya+A==
|   |   |   |   |           HO+AXf9a0UG7x3GnC7ZfHg==
|   |   |   |   |           hWCRg+Y4LqaUF9CXtocrZg==
|   |   |   |   |           J9F9W3tupfep+J7AHK792w==
|   |   |   |   |           Jkyqvm7xf_IODi4NSr_DfQ==
|   |   |   |   |           JpySxHuutZW8SDEvljY3yw==
|   |   |   |   |           K9J+iZnJg1mzX_k9IKDL+Q==
|   |   |   |   |           kcTC9mYFcJOFWanjPn3Zhg==
|   |   |   |   |           KVIgH+KfIAQrM7cTcdDGgQ==
|   |   |   |   |           kvPTWhZf_nivI_sGRz8DYA==
|   |   |   |   |           L3UH5REtGjVyCmo8pa0sRw==
|   |   |   |   |           lCfWkGllaGqcF4ZhzQ9zLg==
|   |   |   |   |           lOZGtCvozB0FUdaOmdmw+A==
|   |   |   |   |           LsViBHNXX0s4GRgixALi1Q==
|   |   |   |   |           MA8zk7SLSPgX0PfXHCnpxg==
|   |   |   |   |           nb7GYsdEM9mvJGzqD5toXA==
|   |   |   |   |           nonZ6mBR3O0xtYKxa1aLRA==
|   |   |   |   |           O4MrtWS++V2FryKkgQkEYA==
|   |   |   |   |           OLAEHtC_EGAcANpakCOKeg==
|   |   |   |   |           PcS4PijkoIunGcRuDEWtWA==
|   |   |   |   |           pha7W+QKCR_1T3ygGrD8CQ==
|   |   |   |   |           pMG4RnvH8bxsptxPWyPFIw==
|   |   |   |   |           pSwgHdK0xX0UgRuyWIDoHQ==
|   |   |   |   |           PVFetPdfpHG9qd9JXQrL8A==
|   |   |   |   |           pxIM51+Y+dHcJOEnfH2sVA==
|   |   |   |   |           PzqoajRY457y6PT0weNy2A==
|   |   |   |   |           q3k9ahKZfmEjy1TeSSNezA==
|   |   |   |   |           qTfq_tgstD6ic_8XUeP2MQ==
|   |   |   |   |           Qyq7CDAgH8NboLTx+fX0OQ==
|   |   |   |   |           rasIprxCbUNIQxLN9SMDug==
|   |   |   |   |           RdY0dm2K0NuwPR79J1Ok6A==
|   |   |   |   |           rkw5tS7rb8pWLpS5R3nvwA==
|   |   |   |   |           SaEqg9nBhmvRtZhPuB+6CQ==
|   |   |   |   |           SBkNnXJ0OMXvvqLBOdQ72g==
|   |   |   |   |           sw2xLqRMCDWyoVlo_UJVQA==
|   |   |   |   |           T1PVNfo07IMPSHez+lftVg==
|   |   |   |   |           tCEmxq+AWftXzJKBBwnLQw==
|   |   |   |   |           V0fCVbX_z4uvYEgtATNHdQ==
|   |   |   |   |           VQ+Yv31auAZwXEkYPlZ2ww==
|   |   |   |   |           W3CbA5ftjM54iUH0M__6vA==
|   |   |   |   |           WnCou7rPHyxlNNl1yXiTEw==
|   |   |   |   |           wQIsjeqzhE5dCNJMdSzAYA==
|   |   |   |   |           XY72amYq1mWhmVgeEzyUAg==
|   |   |   |   |           YVYG1HhQst+eW24Poi026Q==
|   |   |   |   |           zCMBpLCFElfCtoC_mGqfng==
|   |   |   |   |           ZoN998TlJW64nKvkDuQbSA==
|   |   |   |   |           zOVZsX6JANSa1TtyjxdV6w==
|   |   |   |   |           _ABJ9TGxRKEY5eEXw9TC_w==
|   |   |   |   |           _S3zwuTeHyE+d6igIQWkUA==
|   |   |   |   |           
|   |   |   |   +---mergeDebugAssets
|   |   |   |   |       merger.xml
|   |   |   |   |       
|   |   |   |   +---mergeDebugJniLibFolders
|   |   |   |   |       merger.xml
|   |   |   |   |       
|   |   |   |   +---mergeDebugShaders
|   |   |   |   |       merger.xml
|   |   |   |   |       
|   |   |   |   \---packageDebug
|   |   |   |       \---tmp
|   |   |   |           \---debug
|   |   |   |               |   dex-renamer-state.txt
|   |   |   |               |   
|   |   |   |               \---zip-cache
|   |   |   |                       androidResources
|   |   |   |                       javaResources0
|   |   |   |                       
|   |   |   +---java_res
|   |   |   |   \---debug
|   |   |   |       \---processDebugJavaRes
|   |   |   |           \---out
|   |   |   |               +---com
|   |   |   |               |   \---example
|   |   |   |               |       \---assignment_1
|   |   |   |               |           +---data
|   |   |   |               |           +---ui
|   |   |   |               |           |   \---theme
|   |   |   |               |           \---viewmodel
|   |   |   |               \---META-INF
|   |   |   |                       app_debug.kotlin_module
|   |   |   |                       
|   |   |   +---linked_resources_binary_format
|   |   |   |   \---debug
|   |   |   |       \---processDebugResources
|   |   |   |               linked-resources-binary-format-debug.ap_
|   |   |   |               output-metadata.json
|   |   |   |               
|   |   |   +---local_only_symbol_list
|   |   |   |   \---debug
|   |   |   |       \---parseDebugLocalResources
|   |   |   |               R-def.txt
|   |   |   |               
|   |   |   +---manifest_merge_blame_file
|   |   |   |   \---debug
|   |   |   |       \---processDebugMainManifest
|   |   |   |               manifest-merger-blame-debug-report.txt
|   |   |   |               
|   |   |   +---merged_java_res
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugJavaResource
|   |   |   |               base.jar
|   |   |   |               
|   |   |   +---merged_jni_libs
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugJniLibFolders
|   |   |   |           \---out
|   |   |   +---merged_manifest
|   |   |   |   \---debug
|   |   |   |       \---processDebugMainManifest
|   |   |   |               AndroidManifest.xml
|   |   |   |               
|   |   |   +---merged_manifests
|   |   |   |   \---debug
|   |   |   |       \---processDebugManifest
|   |   |   |               AndroidManifest.xml
|   |   |   |               output-metadata.json
|   |   |   |               
|   |   |   +---merged_native_libs
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugNativeLibs
|   |   |   |           \---out
|   |   |   |               \---lib
|   |   |   |                   +---arm64-v8a
|   |   |   |                   |       libandroidx.graphics.path.so
|   |   |   |                   |       
|   |   |   |                   +---armeabi-v7a
|   |   |   |                   |       libandroidx.graphics.path.so
|   |   |   |                   |       
|   |   |   |                   +---x86
|   |   |   |                   |       libandroidx.graphics.path.so
|   |   |   |                   |       
|   |   |   |                   \---x86_64
|   |   |   |                           libandroidx.graphics.path.so
|   |   |   |                           
|   |   |   +---merged_res
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugResources
|   |   |   |               drawable_avatar_01.jpg.flat
|   |   |   |               drawable_avatar_02.jpg.flat
|   |   |   |               drawable_avatar_03.jpg.flat
|   |   |   |               drawable_avatar_04.jpg.flat
|   |   |   |               drawable_avatar_05.jpg.flat
|   |   |   |               drawable_avatar_06.jpg.flat
|   |   |   |               drawable_avatar_07.jpg.flat
|   |   |   |               drawable_avatar_08.jpg.flat
|   |   |   |               drawable_avatar_09.jpg.flat
|   |   |   |               drawable_avatar_woman_12.jpg.flat
|   |   |   |               drawable_ic_launcher_background.xml.flat
|   |   |   |               drawable_ic_launcher_foreground.xml.flat
|   |   |   |               mipmap-anydpi_ic_launcher.xml.flat
|   |   |   |               mipmap-anydpi_ic_launcher_round.xml.flat
|   |   |   |               mipmap-hdpi_ic_launcher.webp.flat
|   |   |   |               mipmap-hdpi_ic_launcher_round.webp.flat
|   |   |   |               mipmap-mdpi_ic_launcher.webp.flat
|   |   |   |               mipmap-mdpi_ic_launcher_round.webp.flat
|   |   |   |               mipmap-xhdpi_ic_launcher.webp.flat
|   |   |   |               mipmap-xhdpi_ic_launcher_round.webp.flat
|   |   |   |               mipmap-xxhdpi_ic_launcher.webp.flat
|   |   |   |               mipmap-xxhdpi_ic_launcher_round.webp.flat
|   |   |   |               mipmap-xxxhdpi_ic_launcher.webp.flat
|   |   |   |               mipmap-xxxhdpi_ic_launcher_round.webp.flat
|   |   |   |               values-af_values-af.arsc.flat
|   |   |   |               values-am_values-am.arsc.flat
|   |   |   |               values-ar_values-ar.arsc.flat
|   |   |   |               values-as_values-as.arsc.flat
|   |   |   |               values-az_values-az.arsc.flat
|   |   |   |               values-b+sr+Latn_values-b+sr+Latn.arsc.flat
|   |   |   |               values-be_values-be.arsc.flat
|   |   |   |               values-bg_values-bg.arsc.flat
|   |   |   |               values-bn_values-bn.arsc.flat
|   |   |   |               values-bs_values-bs.arsc.flat
|   |   |   |               values-ca_values-ca.arsc.flat
|   |   |   |               values-cs_values-cs.arsc.flat
|   |   |   |               values-da_values-da.arsc.flat
|   |   |   |               values-de_values-de.arsc.flat
|   |   |   |               values-el_values-el.arsc.flat
|   |   |   |               values-en-rAU_values-en-rAU.arsc.flat
|   |   |   |               values-en-rCA_values-en-rCA.arsc.flat
|   |   |   |               values-en-rGB_values-en-rGB.arsc.flat
|   |   |   |               values-en-rIN_values-en-rIN.arsc.flat
|   |   |   |               values-en-rXC_values-en-rXC.arsc.flat
|   |   |   |               values-es-rUS_values-es-rUS.arsc.flat
|   |   |   |               values-es_values-es.arsc.flat
|   |   |   |               values-et_values-et.arsc.flat
|   |   |   |               values-eu_values-eu.arsc.flat
|   |   |   |               values-fa_values-fa.arsc.flat
|   |   |   |               values-fi_values-fi.arsc.flat
|   |   |   |               values-fr-rCA_values-fr-rCA.arsc.flat
|   |   |   |               values-fr_values-fr.arsc.flat
|   |   |   |               values-gl_values-gl.arsc.flat
|   |   |   |               values-gu_values-gu.arsc.flat
|   |   |   |               values-hi_values-hi.arsc.flat
|   |   |   |               values-hr_values-hr.arsc.flat
|   |   |   |               values-hu_values-hu.arsc.flat
|   |   |   |               values-hy_values-hy.arsc.flat
|   |   |   |               values-in_values-in.arsc.flat
|   |   |   |               values-is_values-is.arsc.flat
|   |   |   |               values-it_values-it.arsc.flat
|   |   |   |               values-iw_values-iw.arsc.flat
|   |   |   |               values-ja_values-ja.arsc.flat
|   |   |   |               values-ka_values-ka.arsc.flat
|   |   |   |               values-kk_values-kk.arsc.flat
|   |   |   |               values-km_values-km.arsc.flat
|   |   |   |               values-kn_values-kn.arsc.flat
|   |   |   |               values-ko_values-ko.arsc.flat
|   |   |   |               values-ky_values-ky.arsc.flat
|   |   |   |               values-lo_values-lo.arsc.flat
|   |   |   |               values-lt_values-lt.arsc.flat
|   |   |   |               values-lv_values-lv.arsc.flat
|   |   |   |               values-mk_values-mk.arsc.flat
|   |   |   |               values-ml_values-ml.arsc.flat
|   |   |   |               values-mn_values-mn.arsc.flat
|   |   |   |               values-mr_values-mr.arsc.flat
|   |   |   |               values-ms_values-ms.arsc.flat
|   |   |   |               values-my_values-my.arsc.flat
|   |   |   |               values-nb_values-nb.arsc.flat
|   |   |   |               values-ne_values-ne.arsc.flat
|   |   |   |               values-nl_values-nl.arsc.flat
|   |   |   |               values-or_values-or.arsc.flat
|   |   |   |               values-pa_values-pa.arsc.flat
|   |   |   |               values-pl_values-pl.arsc.flat
|   |   |   |               values-pt-rBR_values-pt-rBR.arsc.flat
|   |   |   |               values-pt-rPT_values-pt-rPT.arsc.flat
|   |   |   |               values-pt_values-pt.arsc.flat
|   |   |   |               values-ro_values-ro.arsc.flat
|   |   |   |               values-ru_values-ru.arsc.flat
|   |   |   |               values-si_values-si.arsc.flat
|   |   |   |               values-sk_values-sk.arsc.flat
|   |   |   |               values-sl_values-sl.arsc.flat
|   |   |   |               values-sq_values-sq.arsc.flat
|   |   |   |               values-sr_values-sr.arsc.flat
|   |   |   |               values-sv_values-sv.arsc.flat
|   |   |   |               values-sw_values-sw.arsc.flat
|   |   |   |               values-ta_values-ta.arsc.flat
|   |   |   |               values-te_values-te.arsc.flat
|   |   |   |               values-th_values-th.arsc.flat
|   |   |   |               values-tl_values-tl.arsc.flat
|   |   |   |               values-tr_values-tr.arsc.flat
|   |   |   |               values-uk_values-uk.arsc.flat
|   |   |   |               values-ur_values-ur.arsc.flat
|   |   |   |               values-uz_values-uz.arsc.flat
|   |   |   |               values-v21_values-v21.arsc.flat
|   |   |   |               values-v24_values-v24.arsc.flat
|   |   |   |               values-v30_values-v30.arsc.flat
|   |   |   |               values-vi_values-vi.arsc.flat
|   |   |   |               values-zh-rCN_values-zh-rCN.arsc.flat
|   |   |   |               values-zh-rHK_values-zh-rHK.arsc.flat
|   |   |   |               values-zh-rTW_values-zh-rTW.arsc.flat
|   |   |   |               values-zu_values-zu.arsc.flat
|   |   |   |               values_values.arsc.flat
|   |   |   |               xml_backup_rules.xml.flat
|   |   |   |               xml_data_extraction_rules.xml.flat
|   |   |   |               
|   |   |   +---merged_res_blame_folder
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugResources
|   |   |   |           \---out
|   |   |   |               +---multi-v2
|   |   |   |               |       mergeDebugResources.json
|   |   |   |               |       values-af.json
|   |   |   |               |       values-am.json
|   |   |   |               |       values-ar.json
|   |   |   |               |       values-as.json
|   |   |   |               |       values-az.json
|   |   |   |               |       values-b+sr+Latn.json
|   |   |   |               |       values-be.json
|   |   |   |               |       values-bg.json
|   |   |   |               |       values-bn.json
|   |   |   |               |       values-bs.json
|   |   |   |               |       values-ca.json
|   |   |   |               |       values-cs.json
|   |   |   |               |       values-da.json
|   |   |   |               |       values-de.json
|   |   |   |               |       values-el.json
|   |   |   |               |       values-en-rAU.json
|   |   |   |               |       values-en-rCA.json
|   |   |   |               |       values-en-rGB.json
|   |   |   |               |       values-en-rIN.json
|   |   |   |               |       values-en-rXC.json
|   |   |   |               |       values-es-rUS.json
|   |   |   |               |       values-es.json
|   |   |   |               |       values-et.json
|   |   |   |               |       values-eu.json
|   |   |   |               |       values-fa.json
|   |   |   |               |       values-fi.json
|   |   |   |               |       values-fr-rCA.json
|   |   |   |               |       values-fr.json
|   |   |   |               |       values-gl.json
|   |   |   |               |       values-gu.json
|   |   |   |               |       values-hi.json
|   |   |   |               |       values-hr.json
|   |   |   |               |       values-hu.json
|   |   |   |               |       values-hy.json
|   |   |   |               |       values-in.json
|   |   |   |               |       values-is.json
|   |   |   |               |       values-it.json
|   |   |   |               |       values-iw.json
|   |   |   |               |       values-ja.json
|   |   |   |               |       values-ka.json
|   |   |   |               |       values-kk.json
|   |   |   |               |       values-km.json
|   |   |   |               |       values-kn.json
|   |   |   |               |       values-ko.json
|   |   |   |               |       values-ky.json
|   |   |   |               |       values-lo.json
|   |   |   |               |       values-lt.json
|   |   |   |               |       values-lv.json
|   |   |   |               |       values-mk.json
|   |   |   |               |       values-ml.json
|   |   |   |               |       values-mn.json
|   |   |   |               |       values-mr.json
|   |   |   |               |       values-ms.json
|   |   |   |               |       values-my.json
|   |   |   |               |       values-nb.json
|   |   |   |               |       values-ne.json
|   |   |   |               |       values-nl.json
|   |   |   |               |       values-or.json
|   |   |   |               |       values-pa.json
|   |   |   |               |       values-pl.json
|   |   |   |               |       values-pt-rBR.json
|   |   |   |               |       values-pt-rPT.json
|   |   |   |               |       values-pt.json
|   |   |   |               |       values-ro.json
|   |   |   |               |       values-ru.json
|   |   |   |               |       values-si.json
|   |   |   |               |       values-sk.json
|   |   |   |               |       values-sl.json
|   |   |   |               |       values-sq.json
|   |   |   |               |       values-sr.json
|   |   |   |               |       values-sv.json
|   |   |   |               |       values-sw.json
|   |   |   |               |       values-ta.json
|   |   |   |               |       values-te.json
|   |   |   |               |       values-th.json
|   |   |   |               |       values-tl.json
|   |   |   |               |       values-tr.json
|   |   |   |               |       values-uk.json
|   |   |   |               |       values-ur.json
|   |   |   |               |       values-uz.json
|   |   |   |               |       values-v21.json
|   |   |   |               |       values-v24.json
|   |   |   |               |       values-v30.json
|   |   |   |               |       values-vi.json
|   |   |   |               |       values-zh-rCN.json
|   |   |   |               |       values-zh-rHK.json
|   |   |   |               |       values-zh-rTW.json
|   |   |   |               |       values-zu.json
|   |   |   |               |       values.json
|   |   |   |               |       
|   |   |   |               \---single
|   |   |   |                       mergeDebugResources.json
|   |   |   |                       
|   |   |   +---merged_shaders
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugShaders
|   |   |   |           \---out
|   |   |   +---merged_test_only_native_libs
|   |   |   |   \---debug
|   |   |   |       \---mergeDebugNativeLibs
|   |   |   |           \---out
|   |   |   +---mixed_scope_dex_archive
|   |   |   |   \---debug
|   |   |   |       \---dexBuilderDebug
|   |   |   |           \---out
|   |   |   +---navigation_json
|   |   |   |   \---debug
|   |   |   |       \---extractDeepLinksDebug
|   |   |   |               navigation.json
|   |   |   |               
|   |   |   +---nested_resources_validation_report
|   |   |   |   \---debug
|   |   |   |       \---generateDebugResources
|   |   |   |               nestedResourcesValidationReport.txt
|   |   |   |               
|   |   |   +---packaged_manifests
|   |   |   |   \---debug
|   |   |   |       \---processDebugManifestForPackage
|   |   |   |               AndroidManifest.xml
|   |   |   |               output-metadata.json
|   |   |   |               
|   |   |   +---packaged_res
|   |   |   |   \---debug
|   |   |   |       \---packageDebugResources
|   |   |   |           +---drawable
|   |   |   |           |       avatar_01.jpg
|   |   |   |           |       avatar_02.jpg
|   |   |   |           |       avatar_03.jpg
|   |   |   |           |       avatar_04.jpg
|   |   |   |           |       avatar_05.jpg
|   |   |   |           |       avatar_06.jpg
|   |   |   |           |       avatar_07.jpg
|   |   |   |           |       avatar_08.jpg
|   |   |   |           |       avatar_09.jpg
|   |   |   |           |       avatar_woman_12.jpg
|   |   |   |           |       ic_launcher_background.xml
|   |   |   |           |       ic_launcher_foreground.xml
|   |   |   |           |       
|   |   |   |           +---mipmap-anydpi-v4
|   |   |   |           |       ic_launcher.xml
|   |   |   |           |       ic_launcher_round.xml
|   |   |   |           |       
|   |   |   |           +---mipmap-hdpi-v4
|   |   |   |           |       ic_launcher.webp
|   |   |   |           |       ic_launcher_round.webp
|   |   |   |           |       
|   |   |   |           +---mipmap-mdpi-v4
|   |   |   |           |       ic_launcher.webp
|   |   |   |           |       ic_launcher_round.webp
|   |   |   |           |       
|   |   |   |           +---mipmap-xhdpi-v4
|   |   |   |           |       ic_launcher.webp
|   |   |   |           |       ic_launcher_round.webp
|   |   |   |           |       
|   |   |   |           +---mipmap-xxhdpi-v4
|   |   |   |           |       ic_launcher.webp
|   |   |   |           |       ic_launcher_round.webp
|   |   |   |           |       
|   |   |   |           +---mipmap-xxxhdpi-v4
|   |   |   |           |       ic_launcher.webp
|   |   |   |           |       ic_launcher_round.webp
|   |   |   |           |       
|   |   |   |           +---values
|   |   |   |           |       values.xml
|   |   |   |           |       
|   |   |   |           \---xml
|   |   |   |                   backup_rules.xml
|   |   |   |                   data_extraction_rules.xml
|   |   |   |                   
|   |   |   +---project_dex_archive
|   |   |   |   \---debug
|   |   |   |       \---dexBuilderDebug
|   |   |   |           \---out
|   |   |   |               |   6fd277a643aef7859fe686604650e210951d5ad93fed63865c387aec06d2e5e5_0.jar
|   |   |   |               |   6fd277a643aef7859fe686604650e210951d5ad93fed63865c387aec06d2e5e5_1.jar
|   |   |   |               |   6fd277a643aef7859fe686604650e210951d5ad93fed63865c387aec06d2e5e5_2.jar
|   |   |   |               |   6fd277a643aef7859fe686604650e210951d5ad93fed63865c387aec06d2e5e5_3.jar
|   |   |   |               |   6fd277a643aef7859fe686604650e210951d5ad93fed63865c387aec06d2e5e5_4.jar
|   |   |   |               |   6fd277a643aef7859fe686604650e210951d5ad93fed63865c387aec06d2e5e5_5.jar
|   |   |   |               |   
|   |   |   |               \---com
|   |   |   |                   \---example
|   |   |   |                       \---assignment_1
|   |   |   |                           |   ComposableSingletons$MainActivityKt$lambda-1$1.dex
|   |   |   |                           |   ComposableSingletons$MainActivityKt$lambda-2$1.dex
|   |   |   |                           |   ComposableSingletons$MainActivityKt$lambda-3$1.dex
|   |   |   |                           |   ComposableSingletons$MainActivityKt.dex
|   |   |   |                           |   MainActivity.dex
|   |   |   |                           |   MainActivityKt$AppNavigation$1$1$1.dex
|   |   |   |                           |   MainActivityKt$AppNavigation$1$1$4.dex
|   |   |   |                           |   MainActivityKt.dex
|   |   |   |                           |   
|   |   |   |                           +---data
|   |   |   |                           |       ApiService$Companion.dex
|   |   |   |                           |       ApiService.dex
|   |   |   |                           |       NetworkModule.dex
|   |   |   |                           |       Video.dex
|   |   |   |                           |       
|   |   |   |                           +---ui
|   |   |   |                           |   |   ComposableSingletons$PostDetailScreenKt$lambda-1$1.dex
|   |   |   |                           |   |   ComposableSingletons$PostDetailScreenKt.dex
|   |   |   |                           |   |   ComposableSingletons$VideoListScreenKt$lambda-1$1.dex
|   |   |   |                           |   |   ComposableSingletons$VideoListScreenKt.dex
|   |   |   |                           |   |   PostDetailScreenKt$PostDetailScreen$1$1.dex
|   |   |   |                           |   |   PostDetailScreenKt$PostDetailScreen$1$2.dex
|   |   |   |                           |   |   PostDetailScreenKt$PostDetailScreen$1.dex
|   |   |   |                           |   |   PostDetailScreenKt$PostDetailScreen$2.dex
|   |   |   |                           |   |   PostDetailScreenKt.dex
|   |   |   |                           |   |   VideoListScreenKt$VideoItem$1.dex
|   |   |   |                           |   |   VideoListScreenKt$VideoListScreen$1$1$1$1$1$1$1.dex
|   |   |   |                           |   |   VideoListScreenKt$VideoListScreen$1$invoke$lambda$4$lambda$3$lambda$2$$inlined$items$default$1.dex
|   |   |   |                           |   |   VideoListScreenKt$VideoListScreen$1$invoke$lambda$4$lambda$3$lambda$2$$inlined$items$default$2.dex
|   |   |   |                           |   |   VideoListScreenKt$VideoListScreen$1$invoke$lambda$4$lambda$3$lambda$2$$inlined$items$default$3.dex
|   |   |   |                           |   |   VideoListScreenKt$VideoListScreen$1$invoke$lambda$4$lambda$3$lambda$2$$inlined$items$default$4.dex
|   |   |   |                           |   |   VideoListScreenKt$VideoListScreen$1.dex
|   |   |   |                           |   |   VideoListScreenKt.dex
|   |   |   |                           |   |   VideoPlayerScreenKt$VideoPlayerScreen$1$1.dex
|   |   |   |                           |   |   VideoPlayerScreenKt$VideoPlayerScreen$2$1$listener$1.dex
|   |   |   |                           |   |   VideoPlayerScreenKt$VideoPlayerScreen$lambda$5$lambda$4$$inlined$onDispose$1.dex
|   |   |   |                           |   |   VideoPlayerScreenKt.dex
|   |   |   |                           |   |   
|   |   |   |                           |   \---theme
|   |   |   |                           |           ColorKt.dex
|   |   |   |                           |           ThemeKt.dex
|   |   |   |                           |           TypeKt.dex
|   |   |   |                           |           
|   |   |   |                           \---viewmodel
|   |   |   |                                   VideoViewModel$fetchVideos$1.dex
|   |   |   |                                   VideoViewModel.dex
|   |   |   |                                   
|   |   |   +---runtime_symbol_list
|   |   |   |   \---debug
|   |   |   |       \---processDebugResources
|   |   |   |               R.txt
|   |   |   |               
|   |   |   +---signing_config_versions
|   |   |   |   \---debug
|   |   |   |       \---writeDebugSigningConfigVersions
|   |   |   |               signing-config-versions.json
|   |   |   |               
|   |   |   +---source_set_path_map
|   |   |   |   \---debug
|   |   |   |       \---mapDebugSourceSetPaths
|   |   |   |               file-map.txt
|   |   |   |               
|   |   |   +---stable_resource_ids_file
|   |   |   |   \---debug
|   |   |   |       \---processDebugResources
|   |   |   |               stableIds.txt
|   |   |   |               
|   |   |   +---stripped_native_libs
|   |   |   |   \---debug
|   |   |   |       \---stripDebugDebugSymbols
|   |   |   |           \---out
|   |   |   |               \---lib
|   |   |   |                   +---arm64-v8a
|   |   |   |                   |       libandroidx.graphics.path.so
|   |   |   |                   |       
|   |   |   |                   +---armeabi-v7a
|   |   |   |                   |       libandroidx.graphics.path.so
|   |   |   |                   |       
|   |   |   |                   +---x86
|   |   |   |                   |       libandroidx.graphics.path.so
|   |   |   |                   |       
|   |   |   |                   \---x86_64
|   |   |   |                           libandroidx.graphics.path.so
|   |   |   |                           
|   |   |   +---sub_project_dex_archive
|   |   |   |   \---debug
|   |   |   |       \---dexBuilderDebug
|   |   |   |           \---out
|   |   |   +---symbol_list_with_package_name
|   |   |   |   \---debug
|   |   |   |       \---processDebugResources
|   |   |   |               package-aware-r.txt
|   |   |   |               
|   |   |   \---validate_signing_config
|   |   |       \---debug
|   |   |           \---validateSigningDebug
|   |   +---kotlin
|   |   |   \---compileDebugKotlin
|   |   |       +---cacheable
|   |   |       |   |   last-build.bin
|   |   |       |   |   
|   |   |       |   \---caches-jvm
|   |   |       |       +---inputs
|   |   |       |       |       source-to-output.tab
|   |   |       |       |       source-to-output.tab.keystream
|   |   |       |       |       source-to-output.tab.keystream.len
|   |   |       |       |       source-to-output.tab.len
|   |   |       |       |       source-to-output.tab.values.at
|   |   |       |       |       source-to-output.tab_i
|   |   |       |       |       source-to-output.tab_i.len
|   |   |       |       |       
|   |   |       |       +---jvm
|   |   |       |       |   \---kotlin
|   |   |       |       |           class-attributes.tab
|   |   |       |       |           class-attributes.tab.keystream
|   |   |       |       |           class-attributes.tab.keystream.len
|   |   |       |       |           class-attributes.tab.len
|   |   |       |       |           class-attributes.tab.values.at
|   |   |       |       |           class-attributes.tab_i
|   |   |       |       |           class-attributes.tab_i.len
|   |   |       |       |           class-fq-name-to-source.tab
|   |   |       |       |           class-fq-name-to-source.tab.keystream
|   |   |       |       |           class-fq-name-to-source.tab.keystream.len
|   |   |       |       |           class-fq-name-to-source.tab.len
|   |   |       |       |           class-fq-name-to-source.tab.values.at
|   |   |       |       |           class-fq-name-to-source.tab_i
|   |   |       |       |           class-fq-name-to-source.tab_i.len
|   |   |       |       |           constants.tab
|   |   |       |       |           constants.tab.keystream
|   |   |       |       |           constants.tab.keystream.len
|   |   |       |       |           constants.tab.len
|   |   |       |       |           constants.tab.values.at
|   |   |       |       |           constants.tab_i
|   |   |       |       |           constants.tab_i.len
|   |   |       |       |           internal-name-to-source.tab
|   |   |       |       |           internal-name-to-source.tab.keystream
|   |   |       |       |           internal-name-to-source.tab.keystream.len
|   |   |       |       |           internal-name-to-source.tab.len
|   |   |       |       |           internal-name-to-source.tab.values.at
|   |   |       |       |           internal-name-to-source.tab_i
|   |   |       |       |           internal-name-to-source.tab_i.len
|   |   |       |       |           package-parts.tab
|   |   |       |       |           package-parts.tab.keystream
|   |   |       |       |           package-parts.tab.keystream.len
|   |   |       |       |           package-parts.tab.len
|   |   |       |       |           package-parts.tab.values.at
|   |   |       |       |           package-parts.tab_i
|   |   |       |       |           package-parts.tab_i.len
|   |   |       |       |           proto.tab
|   |   |       |       |           proto.tab.keystream
|   |   |       |       |           proto.tab.keystream.len
|   |   |       |       |           proto.tab.len
|   |   |       |       |           proto.tab.values.at
|   |   |       |       |           proto.tab_i
|   |   |       |       |           proto.tab_i.len
|   |   |       |       |           source-to-classes.tab
|   |   |       |       |           source-to-classes.tab.keystream
|   |   |       |       |           source-to-classes.tab.keystream.len
|   |   |       |       |           source-to-classes.tab.len
|   |   |       |       |           source-to-classes.tab.values.at
|   |   |       |       |           source-to-classes.tab_i
|   |   |       |       |           source-to-classes.tab_i.len
|   |   |       |       |           subtypes.tab
|   |   |       |       |           subtypes.tab.keystream
|   |   |       |       |           subtypes.tab.keystream.len
|   |   |       |       |           subtypes.tab.len
|   |   |       |       |           subtypes.tab.values.at
|   |   |       |       |           subtypes.tab_i
|   |   |       |       |           subtypes.tab_i.len
|   |   |       |       |           supertypes.tab
|   |   |       |       |           supertypes.tab.keystream
|   |   |       |       |           supertypes.tab.keystream.len
|   |   |       |       |           supertypes.tab.len
|   |   |       |       |           supertypes.tab.values.at
|   |   |       |       |           supertypes.tab_i
|   |   |       |       |           supertypes.tab_i.len
|   |   |       |       |           
|   |   |       |       \---lookups
|   |   |       |               counters.tab
|   |   |       |               file-to-id.tab
|   |   |       |               file-to-id.tab.keystream
|   |   |       |               file-to-id.tab.keystream.len
|   |   |       |               file-to-id.tab.len
|   |   |       |               file-to-id.tab.values.at
|   |   |       |               file-to-id.tab_i
|   |   |       |               file-to-id.tab_i.len
|   |   |       |               id-to-file.tab
|   |   |       |               id-to-file.tab.keystream
|   |   |       |               id-to-file.tab.keystream.len
|   |   |       |               id-to-file.tab.len
|   |   |       |               id-to-file.tab.values.at
|   |   |       |               id-to-file.tab_i
|   |   |       |               id-to-file.tab_i.len
|   |   |       |               lookups.tab
|   |   |       |               lookups.tab.keystream
|   |   |       |               lookups.tab.keystream.len
|   |   |       |               lookups.tab.len
|   |   |       |               lookups.tab.values.at
|   |   |       |               lookups.tab_i
|   |   |       |               lookups.tab_i.len
|   |   |       |               
|   |   |       +---classpath-snapshot
|   |   |       |       shrunk-classpath-snapshot.bin
|   |   |       |       
|   |   |       \---local-state
|   |   |               build-history.bin
|   |   |               
|   |   +---outputs
|   |   |   \---logs
|   |   |           manifest-merger-debug-report.txt
|   |   |           
|   |   \---tmp
|   |       \---kotlin-classes
|   |           \---debug
|   |               +---com
|   |               |   \---example
|   |               |       \---assignment_1
|   |               |           |   ComposableSingletons$MainActivityKt$lambda-1$1.class
|   |               |           |   ComposableSingletons$MainActivityKt$lambda-2$1.class
|   |               |           |   ComposableSingletons$MainActivityKt$lambda-3$1.class
|   |               |           |   ComposableSingletons$MainActivityKt.class
|   |               |           |   MainActivity.class
|   |               |           |   MainActivityKt$AppNavigation$1$1$1.class
|   |               |           |   MainActivityKt$AppNavigation$1$1$4.class
|   |               |           |   MainActivityKt.class
|   |               |           |   
|   |               |           +---data
|   |               |           |       ApiService$Companion.class
|   |               |           |       ApiService.class
|   |               |           |       NetworkModule.class
|   |               |           |       Video.class
|   |               |           |       
|   |               |           +---ui
|   |               |           |   |   ComposableSingletons$PostDetailScreenKt$lambda-1$1.class
|   |               |           |   |   ComposableSingletons$PostDetailScreenKt.class
|   |               |           |   |   ComposableSingletons$VideoListScreenKt$lambda-1$1.class
|   |               |           |   |   ComposableSingletons$VideoListScreenKt.class
|   |               |           |   |   PostDetailScreenKt$PostDetailScreen$1$1.class
|   |               |           |   |   PostDetailScreenKt$PostDetailScreen$1$2.class
|   |               |           |   |   PostDetailScreenKt$PostDetailScreen$1.class
|   |               |           |   |   PostDetailScreenKt$PostDetailScreen$2.class
|   |               |           |   |   PostDetailScreenKt.class
|   |               |           |   |   VideoListScreenKt$VideoItem$1.class
|   |               |           |   |   VideoListScreenKt$VideoListScreen$1$1$1$1$1$1$1.class
|   |               |           |   |   VideoListScreenKt$VideoListScreen$1$invoke$lambda$4$lambda$3$lambda$2$$inlined$items$default$1.class
|   |               |           |   |   VideoListScreenKt$VideoListScreen$1$invoke$lambda$4$lambda$3$lambda$2$$inlined$items$default$2.class
|   |               |           |   |   VideoListScreenKt$VideoListScreen$1$invoke$lambda$4$lambda$3$lambda$2$$inlined$items$default$3.class
|   |               |           |   |   VideoListScreenKt$VideoListScreen$1$invoke$lambda$4$lambda$3$lambda$2$$inlined$items$default$4.class
|   |               |           |   |   VideoListScreenKt$VideoListScreen$1.class
|   |               |           |   |   VideoListScreenKt.class
|   |               |           |   |   VideoPlayerScreenKt$VideoPlayerScreen$1$1.class
|   |               |           |   |   VideoPlayerScreenKt$VideoPlayerScreen$2$1$listener$1.class
|   |               |           |   |   VideoPlayerScreenKt$VideoPlayerScreen$lambda$5$lambda$4$$inlined$onDispose$1.class
|   |               |           |   |   VideoPlayerScreenKt.class
|   |               |           |   |   
|   |               |           |   \---theme
|   |               |           |           ColorKt.class
|   |               |           |           ThemeKt.class
|   |               |           |           TypeKt.class
|   |               |           |           
|   |               |           \---viewmodel
|   |               |                   VideoViewModel$fetchVideos$1.class
|   |               |                   VideoViewModel.class
|   |               |                   
|   |               \---META-INF
|   |                       app_debug.kotlin_module
|   |                       
|   \---src
|       +---androidTest
|       |   \---java
|       |       \---com
|       |           \---example
|       |               \---assignment_1
|       |                       ExampleInstrumentedTest.kt
|       |                       
|       +---main
|       |   |   AndroidManifest.xml
|       |   |   
|       |   +---java
|       |   |   \---com
|       |   |       \---example
|       |   |           \---assignment_1
|       |   |               |   MainActivity.kt
|       |   |               |   
|       |   |               +---data
|       |   |               |       ApiService.kt
|       |   |               |       NetworkModule.kt
|       |   |               |       Video.kt
|       |   |               |       
|       |   |               +---ui
|       |   |               |   |   PostDetailScreen.kt
|       |   |               |   |   VideoListScreen.kt
|       |   |               |   |   VideoPlayerScreen.kt
|       |   |               |   |   
|       |   |               |   \---theme
|       |   |               |           Color.kt
|       |   |               |           Theme.kt
|       |   |               |           Type.kt
|       |   |               |           
|       |   |               \---viewmodel
|       |   |                       VideoViewModel.kt
|       |   |                       
|       |   \---res
|       |       +---drawable
|       |       |       avatar_01.jpg
|       |       |       avatar_02.jpg
|       |       |       avatar_03.jpg
|       |       |       avatar_04.jpg
|       |       |       avatar_05.jpg
|       |       |       avatar_06.jpg
|       |       |       avatar_07.jpg
|       |       |       avatar_08.jpg
|       |       |       avatar_09.jpg
|       |       |       avatar_woman_12.jpg
|       |       |       ic_launcher_background.xml
|       |       |       ic_launcher_foreground.xml
|       |       |       
|       |       +---mipmap-anydpi
|       |       |       ic_launcher.xml
|       |       |       ic_launcher_round.xml
|       |       |       
|       |       +---mipmap-hdpi
|       |       |       ic_launcher.webp
|       |       |       ic_launcher_round.webp
|       |       |       
|       |       +---mipmap-mdpi
|       |       |       ic_launcher.webp
|       |       |       ic_launcher_round.webp
|       |       |       
|       |       +---mipmap-xhdpi
|       |       |       ic_launcher.webp
|       |       |       ic_launcher_round.webp
|       |       |       
|       |       +---mipmap-xxhdpi
|       |       |       ic_launcher.webp
|       |       |       ic_launcher_round.webp
|       |       |       
|       |       +---mipmap-xxxhdpi
|       |       |       ic_launcher.webp
|       |       |       ic_launcher_round.webp
|       |       |       
|       |       +---values
|       |       |       colors.xml
|       |       |       strings.xml
|       |       |       themes.xml
|       |       |       
|       |       \---xml
|       |               backup_rules.xml
|       |               data_extraction_rules.xml
|       |               
|       \---test
|           \---java
|               \---com
|                   \---example
|                       \---assignment_1
|                               ExampleUnitTest.kt
|                               
\---gradle
    |   libs.versions.toml
    |   
    \---wrapper
            gradle-wrapper.jar
            gradle-wrapper.properties
            



