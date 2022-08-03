package com.rikaimind.appexercise.ui.feature.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.rikaimind.appexercise.data.api.model.User
import com.rikaimind.appexercise.ui.theme.TextColorGrey


@Composable
fun UserListScreen(
    state: UsersContract.State,
    onNavigationRequested: (userName: String) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Box {
            UsersList(users = state.users) { itemId ->
                onNavigationRequested(itemId)//click item in list
            }
            if (state.isLoading)
                LoadingBar()
        }
    }

}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun UsersList(
    users: List<User>,
    onItemClicked: (userName: String) -> Unit = { }
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        items(users) { item ->
            UserItem(
                user = item,
                selectedItem = onItemClicked //click item in list
            )
        }
    }
}


@Composable
fun UserItem(user: User, selectedItem: (String) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { selectedItem(user.login) }
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(2.dp), elevation = 4.dp
    ) {
        Surface {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()

            ) {
                Image(
                    painter = rememberImagePainter(
                        data = user.avatar_url,
                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.notification_action_background)
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = user.login,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = user.login,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        color = TextColorGrey
                    )
                    if (user.site_admin){
                        Text(
                            color = Color.White,
                            text = "STAFF",
                            style = MaterialTheme.typography.caption,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .width(80.dp)
                                .height(30.dp)
                                .padding(4.dp)
                                .background(color = Color.Blue, shape = RoundedCornerShape(15.dp))
                        )
                    }
                }
            }
        }
    }

}