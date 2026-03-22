package pro.branium.learnjetpackcompose.lesson12

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import pro.branium.learnjetpackcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerSample() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Mail",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start
                )

                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = { Text("Inbox") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Inbox, contentDescription = null)
                    },
                    selected = true,
                    onClick = {
                        Toast.makeText(context, "Inbox", Toast.LENGTH_SHORT).show()
                    }
                )

                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = { Text("Outbox") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Outbox, contentDescription = null)
                    },
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "Outbox", Toast.LENGTH_SHORT).show()
                    }
                )
                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = { Text("Favorite") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    },
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
                    }
                )

                HorizontalDivider()

                NavigationDrawerItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = { Text("Settings") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                    },
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        },
        scrimColor = Color.LightGray
    ) {
        var shouldShowDialog by remember { mutableStateOf(false) }

        if (shouldShowDialog) {
            DialogScreen(onDismiss = { shouldShowDialog = false })
        }

        Scaffold(
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
            floatingActionButton = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    ExtendedFloatingActionButton(
                        onClick = {},
                        shape = RoundedCornerShape(16.dp),
                        icon = {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        },
                        text = {
                            Text("Compose")
                        }
                    )
                }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Button(onClick = {
                    shouldShowDialog = true
                }) {
                    Text("Open Dialog")
                }
            }
        }
    }
}


@Composable
fun DialogScreen(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text("Attention")
        },
        text = {
            Text("Are you sure you want to do this?")
        },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Dismiss")
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.QuestionMark,
                contentDescription = "Question Mark",
                modifier = Modifier.size(64.dp)
            )
        },
        tonalElevation = 8.dp
    )
}