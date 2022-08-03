package com.rikaimind.appexercise.ui.feature.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.rikaimind.appexercise.ui.theme.TextColorBlog
import com.rikaimind.appexercise.ui.theme.TextColorGrey


@Composable
fun UserDetailScreen(
    state: UserDetailsContract.State,
    navController: NavHostController
) {
    val userDetails = state.userDetail

    val scrollState = rememberScrollState()

    if (userDetails != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //button close
            Icon(
                imageVector = Icons.Filled.Close,
                modifier =
                Modifier
                    .size(64.dp)
                    .align(Alignment.Start)
                    .clickable {
                        navController.popBackStack()//process back to previous screen when click button close
                    },
                contentDescription = null,
            )

            //avatar
            Image(
                painter = rememberImagePainter(
                    data = userDetails.avatar_url,

                    builder = {
                        scale(Scale.FILL)
                        placeholder(R.drawable.notification_action_background)
                        transformations(CircleCropTransformation())

                    }
                ),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(30.dp))
            userDetails.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h3,
                    fontSize = 30.sp

                )
            }

            userDetails.bio?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h3,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp
            )

            //information
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(
                    imageVector = Icons.Filled.Person,
                    modifier = Modifier.size(56.dp),
                    contentDescription = null
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    userDetails.login?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            color = TextColorGrey
                        )
                    }

                    if (userDetails.site_admin){
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
            Spacer(modifier = Modifier.height(30.dp))
            //location
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    modifier = Modifier.size(56.dp),
                    contentDescription = null
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    userDetails.location?.let {
                        Text(
                            //                                text = user.login,
                            text = it,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            color = TextColorGrey
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            //blog
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    modifier = Modifier.size(56.dp),
                    contentDescription = null
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    userDetails.blog?.let {
                        Text(
                            //                                text = user.login,
                            text = it,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            color = TextColorBlog
                        )
                    }

                }
            }
        }
    } else{
        LoadingBar()
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
