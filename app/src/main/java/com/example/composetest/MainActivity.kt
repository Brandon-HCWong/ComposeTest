package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetest.ui.theme.ComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                modifier = Modifier.statusBarsPadding(),
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    finish()
                                }
                            ) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
                            }
                        },
                        title = {
                            Text("Advance Setting")
                        }
                    )
                }
            ) { innerPadding ->
                GetContentView(
                    viewModel(),
                    innerPadding
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TitleWithStateItem(
    title: String = "Empty Title",
    state: String = "Empty State",
    onClick: () -> Unit = {}
) {
    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .defaultMinSize(minHeight = 58.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .padding(end = 16.dp),
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black,
                lineBreak = LineBreak.Simple
            )
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .width(IntrinsicSize.Max),
            text = state,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF8C8C8C),
                textAlign = TextAlign.End,
                lineBreak = LineBreak.Simple
            )
        )
    }
}

@Composable
fun GetContentView(
    viewmodel: BrandonViewModel,
    innerPadding : PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState()),
    ) {
        Surface {
            TitleWithStateItem(
                title = "Add testInt",
                // [Approach 1]
                state = viewmodel.uiState.value.testInt.toString(),
                // [Approach 2]
//                state = viewmodel.testInt.toString(),
                onClick = {
                    viewmodel.addTestInt()
                }
            )
        }
        Surface {
            TitleWithStateItem(
                title = "Add testIntState",
                // [Approach 1]
                state = viewmodel.uiState.value.testIntState.value.toString(),
                // [Approach 2]
//                state = viewmodel.testIntState.toString(),
                onClick = {
                    viewmodel.addTestIntState()
                }
            )
        }
        Surface {
            TitleWithStateItem(
                title = "Add testLongState",
                // [Approach 1]
                state = viewmodel.uiState.value.testLongState.value.toString(),
                // [Approach 2]
//                state = viewmodel.testLongState.toString(),
                onClick = {
                    viewmodel.addTestLongState()
                }
            )
        }
    }
}