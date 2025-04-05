package com.example.homeworktbc.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homeworktbc.R

@Composable
fun LoginScreen() {

    Column (modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier.fillMaxSize()
        ) {
            Image(modifier = Modifier.width(115.dp).height(125.dp),
                painter = painterResource(id = R.drawable.circle_top),
                contentDescription = null,
                contentScale = ContentScale.Crop

            )


            Image(modifier = Modifier.width(100.dp).height(90.dp).align(Alignment.BottomStart),
                painter = painterResource(id = R.drawable.circle_bot),
                contentDescription = null,
                contentScale = ContentScale.Crop

            )

            Text(
                text = "Login",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopCenter).padding(90.dp)
            )


            Column(modifier = Modifier.fillMaxSize().padding(top = 180.dp),
                ) {
                Image(modifier = Modifier.width(140.dp).height(230.dp).align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.loginman)
                )


                var text by remember { mutableStateOf("UserName") }

                TextField(
                    value = text,
                    onValueChange = {newText ->
                        text = newText
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.iconman),
                            contentDescription = "Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    },
//                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color(0xFFD4B6FB),
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        cursorColor = Color.Black
//                    ),

                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 20.dp)

                )

            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}

