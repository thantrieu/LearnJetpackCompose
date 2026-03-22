package pro.branium.learnjetpackcompose.lesson17.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import pro.branium.learnjetpackcompose.lesson17.db.UserEntity
import pro.branium.learnjetpackcompose.lesson17.viewmodel.UserViewModel

// data : cac lop database, dao, data source, repository, retrofit

@Composable
fun NewUserScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val myViewModel: UserViewModel = hiltViewModel()
            val userFlow by myViewModel.userFlow.collectAsState()

            var fullName by remember { mutableStateOf("") }
            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var isMale by remember { mutableStateOf(false) }

            val users by myViewModel.getAllUsers().collectAsState(initial = emptyList())

            Text("Add New User", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = username,
                placeholder = { Text("Tên đăng nhập") },
                onValueChange = { username = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = fullName,
                placeholder = { Text("Họ và tên") },
                onValueChange = { fullName = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                placeholder = { Text("Email") },
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Checkbox(
                checked = isMale,
                onCheckedChange = { isMale = it }
            )

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                myViewModel.insertUser(
                    UserEntity(
                        id = 0,
                        username = username,
                        fullName = fullName,
                        email = email,
                        isMale = isMale
                    )
                )
            }) {
                Text("Thêm mới user")
            }

            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        users.size,
                        key = { index -> users[index].id }
                    ) { index ->
                        Text(
                            "${users[index].fullName}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NewUserScreenPreview() {
    NewUserScreen()
}