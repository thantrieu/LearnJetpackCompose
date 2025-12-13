package pro.branium.learnjetpackcompose.lesson9

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.branium.learnjetpackcompose.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var showMenu by remember { mutableStateOf(false) }
    val isCollapsed = scrollBehavior.state.collapsedFraction > 0.5f

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { // top app bar

            LargeTopAppBar(
//            CenterAlignedTopAppBar(
                title = { Text(text = "Login") },
                actions = {
                    if (!isCollapsed) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.CopyAll,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.CopyAll,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.CopyAll,
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.CopyAll,
                                contentDescription = null
                            )
                        }
                    } else {
//                        Box {
                            IconButton(onClick = { showMenu = !showMenu }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = null
                                )
                            }
//                        }

                        DropdownMenu(expanded = showMenu, onDismissRequest = {
                            showMenu = false
                        }) {
                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = null
                                    )
                                },
                                text = { Text("Edit") },
                                onClick = {}
                            )
                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete icon"
                                    )
                                },
                                text = { Text("Delete") },
                                onClick = {}
                            )
                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null
                                    )
                                },
                                text = { Text("Detail") },
                                onClick = {}
                            )
                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.CopyAll,
                                        contentDescription = null
                                    )
                                },
                                text = { Text("Copy") },
                                onClick = {}
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        // navigate back to previous screen
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = { // bottom app bar
            BottomAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.NoteAlt, contentDescription = null)
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /*  */ },
                        containerColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding: PaddingValues ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ScrollContent()
//            var email by rememberSaveable { mutableStateOf("") }
//            var password by rememberSaveable { mutableStateOf("") }
//            var passwordVisible by remember { mutableStateOf(false) }
//            val snackbarScope = rememberCoroutineScope()
//
//            Text(
//                text = "Đăng Nhập",
//                textAlign = TextAlign.Center,
//                style = Typography.titleLarge
//            )
//
//            Spacer(modifier = Modifier.height(48.dp))
//
//            OutlinedTextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = email,
//                onValueChange = { email = it },
//                placeholder = { Text(text = "Email của bạn") },
//                label = { Text("Email") },
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            val transformation =
//                if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
//
//            OutlinedTextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = password,
//                placeholder = { Text(text = "Mật khẩu của bạn") },
//                onValueChange = { newText -> password = newText },
//                label = { Text("Mật khẩu") },
//                visualTransformation = transformation,
//                trailingIcon = {
//                    val imageIcon = if (passwordVisible) Icons.Default.Visibility
//                    else Icons.Default.VisibilityOff
//
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        Icon(
//                            imageVector = imageIcon,
//                            contentDescription = "Visibility"
//                        )
//                    }
//                }
//            )
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 48.dp, vertical = 8.dp),
//                onClick = {
//                    snackbarScope.launch {
//                        snackbarHostState.showSnackbar(
//                            message = "Email: $email\nPassword: $password",
//                            duration = SnackbarDuration.Short
//                        )
//                    }
//                }
//            ) {
//                Text(text = "Đăng nhập")
//            }
        }
    }
}

@Composable
fun ScrollContent() {
    val range = 1..100

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(range.count()) { index ->
            Text(text = "- List item number ${index + 1}")
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreen()
    }
}