package pro.branium.learnjetpackcompose.lesson41.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.branium.learnjetpackcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onGoogleLoginClick: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    val primaryBlue = Color(0xFF4A89FF) // Màu xanh nút bấm
    val textFieldBg = Color(0xFFF3F4F6) // Màu xám nhạt của background text field

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Tiêu đề
        Text(
            text = "Đăng nhập",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Label Email
        Text(
            text = "Email của bạn",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        // Input Email
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            placeholder = { Text("Nhập email của bạn", color = Color.LightGray) },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Email,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = textFieldBg,
                unfocusedContainerColor = textFieldBg,
                disabledContainerColor = textFieldBg,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Label Mật khẩu
        Text(
            text = "Mật khẩu",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        // Input Mật khẩu
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            placeholder = { Text("Nhập mật khẩu", color = Color.LightGray) },
            trailingIcon = { Icon(Icons.Outlined.Visibility, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = textFieldBg,
                unfocusedContainerColor = textFieldBg,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Quên mật khẩu
        TextButton(
            onClick = { /* Handle Forgot Password */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Quên mật khẩu?", color = primaryBlue)
        }

        // Giữ tôi đăng nhập
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = CheckboxDefaults.colors(checkedColor = primaryBlue)
            )
            Text(text = "Giữ tôi đăng nhập", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Đăng nhập
        Button(
            onClick = { /* Handle Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryBlue)
        ) {
            Text(text = "Đăng nhập", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Divider Hoặc
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
            Text(
                text = "Hoặc",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Gray
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Social Buttons
        SocialLoginButtons(onGoogleLoginClick)

        Spacer(modifier = Modifier.height(24.dp))

        // Đăng ký ngay
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Bạn chưa có tài khoản? ", color = Color.Gray, fontSize = 14.sp)
            TextButton(onClick = { /* Go to Sign Up */ }, contentPadding = PaddingValues(0.dp)) {
                Text(
                    text = "Đăng ký ngay",
                    color = primaryBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun SocialLoginButtons(
    onGoogleLoginClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Nút Google
        OutlinedButton(
            onClick = {onGoogleLoginClick()},
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(width = 0.5.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google), // Thay bằng ID icon của bạn
                    contentDescription = "Google Logo",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Google")
            }
        }

        // Nút Facebook
        OutlinedButton(
            onClick = { /* Facebook Login */ },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            border = ButtonDefaults.outlinedButtonBorder(enabled = true).copy(width = 0.5.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook), // Thay bằng ID icon của bạn
                    contentDescription = "Facebook Logo",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Facebook")
            }
        }
    }
}