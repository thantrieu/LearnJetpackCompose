package pro.branium.learnjetpackcompose.lesson20.ui

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.HideSource
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.R
import pro.branium.learnjetpackcompose.lesson20.data.remote.ApiResult
import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.viewmodel.SongViewModel

// checkbox
// radio button
// radio group
// save key-value với data store <== done
// dropdown trong settings

@Composable
fun SetStatusBarColor(
    color: Color,
    darkIcons: Boolean = true // True: icon màu đen (cho nền sáng), False: icon màu trắng (cho nền tối)
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Đổi màu nền status bar
            window.statusBarColor = color.toArgb()

            // Đổi màu icon (đen hoặc trắng)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkIcons
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListScreen(
    isDark: Boolean = false,
    onDarkThemeChanged: (Boolean) -> Unit = {}
) {
    val songViewModel: SongViewModel = hiltViewModel()
    val songResult by songViewModel.songsState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var selectedLanguage by remember { mutableStateOf("Tiếng Việt") }
    val onLanguageSelected: (String) -> Unit = { language ->
        selectedLanguage = language
    }

    // todo: update layout theo ngôn ngữ đã chọn

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
                drawerContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start
                )

                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = { Text(stringResource(R.string.menu_item_settings)) },
                    icon = {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                    },
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                    }
                )
                NavigationDrawerItem(
                    label = {
                        Text(text = stringResource(R.string.menu_item_dark_mode))
                    },
                    selected = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    icon = {
                        Icon(imageVector = Icons.Filled.DarkMode, contentDescription = null)
                    },
                    onClick = {

                    },
                    badge = {
                        Switch(
                            checked = isDark,
                            onCheckedChange = { onDarkThemeChanged(it) }
                        )
                    }
                )
                LanguageDrawerItem(
                    selectedLanguage,
                    onLanguageSelected
                )
            }
        },
    ) {
        Scaffold(
            snackbarHost = {},
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Navigation Drawer",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }

//                                if(drawerState.isClosed) {
//                                    drawerState.open()
//                                } else {
//                                    drawerState.close()
//                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (songResult) {
                    is ApiResult.Loading -> { // loading
                        CircularProgressIndicator()
                    }

                    is ApiResult.Success -> { // success
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            val songs = (songResult as ApiResult.Success<List<Song>>).data
                            items(
                                count = songs.size,
                                key = { index -> songs[index].id }
                            ) { index ->
                                val song = songs[index]
                                SongItem(song)
                                if (index < songs.size - 1) {
                                    HorizontalDivider()
                                }
                            }
                        }
                    }

                    is ApiResult.Error -> { // error
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            val error = (songResult as ApiResult.Error).message
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = error,
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

//            Button(onClick = { shouldShowBottomSheet = true }) {
//                Text(text = "Show Bottom Sheet")
//            }
//
//            if (shouldShowBottomSheet) {
//                ModalBottomSheet(
//                    modifier = Modifier.fillMaxSize(),
//                    onDismissRequest = {
//                        shouldShowBottomSheet = false
//                    },
//                    sheetState = sheetState
//                ) {
//                    BottomSheetContent { menuItemName ->
//                        Toast.makeText(context, menuItemName, Toast.LENGTH_SHORT).show()
//                        sheetScope.launch {
//                            sheetState.hide()
//                        }.invokeOnCompletion {
//                            if (!sheetState.isVisible) {
//                                shouldShowBottomSheet = false
//                            }
//                        }
//                    }
//                }
//            }
            }
        }
    }
}

@Composable
fun LanguageDrawerItem(
    selected: String,
    onSelected: (String) -> Unit
) {
    val languages = listOf("Tiếng Việt", "Tiếng Anh")
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(start = 32.dp)) {
        // Main clickable drawer item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp),
        ) {
            Icon(imageVector = Icons.Default.Language, contentDescription = null)
            Text(
                "Language",
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                selected,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth(),
                color = Color.Gray,
                textAlign = TextAlign.End
            )
        }

        // Sub-menu items (show only when expanded)
        AnimatedVisibility(visible = expanded) {
            Column(modifier = Modifier.padding(start = 32.dp)) {
                languages.forEach { lang ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelected(lang)
                                expanded = false
                            }
                            .padding(start = 32.dp, top = 8.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = lang,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (lang == selected) FontWeight.Bold else FontWeight.Normal,
                            color = if (lang == selected) Color(0xFFffffff) else Color(0xffaaaaaa)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongItem(song: Song) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {
            }) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            model = song.imageUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(maxLines = 1, text = song.title, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = song.artist ?: "-")
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                showBottomSheet = true
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = null)
        }
    }
    if (showBottomSheet) {
        SongBottomSheet({ showBottomSheet = false }, song.title)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SongBottomSheet(onDismiss: () -> Unit, songTitle: String) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val sheetScope = rememberCoroutineScope()
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        BottomSheetContent { menuItemName ->
            Toast.makeText(context, songTitle, Toast.LENGTH_SHORT).show()
            sheetScope.launch {
                sheetState.hide()
            }
        }
    }
}

data class BottomSheetItem(
    val id: Int,
    val icon: ImageVector,
    val text: String,
    val onClick: (String) -> Unit
)

@Composable
fun BottomSheetContent(onclick: (String) -> Unit) {
    val action: (String) -> Unit = {}

    val menuItems = listOf(
        BottomSheetItem(1, Icons.Default.Settings, "Settings", action),
        BottomSheetItem(2, Icons.Default.Share, "Share", action),
        BottomSheetItem(3, Icons.Default.Edit, "Edit", action),
        BottomSheetItem(4, Icons.Default.Report, "Report", action),
        BottomSheetItem(5, Icons.Default.Delete, "Delete", action),
        BottomSheetItem(6, Icons.Default.ContentCopy, "Copy", action),
        BottomSheetItem(7, Icons.Default.Cancel, "Cancel", action),
        BottomSheetItem(8, Icons.Default.HideSource, "Hide", action),
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Option Menus", style = MaterialTheme.typography.titleMedium
        )

        HorizontalDivider(modifier = Modifier.padding(bottom = 12.dp))

        menuItems.forEach { menuItem ->
            MenuItem(icon = menuItem.icon, text = menuItem.text) {
                onclick(menuItem.text)
            }
        }
    }
}

/*

Icon   text description
 */
@Composable
fun MenuItem(icon: ImageVector, text: String, onclick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable {
                onclick()
            }) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }
}