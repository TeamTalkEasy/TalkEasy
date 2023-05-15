package com.ssafy.talkeasy.feature.follow.ui.mobile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.ssafy.talkeasy.core.domain.entity.response.Follow
import com.ssafy.talkeasy.feature.common.component.NoContentLogoMessage
import com.ssafy.talkeasy.feature.common.component.Profile
import com.ssafy.talkeasy.feature.common.ui.theme.cabbage_pont
import com.ssafy.talkeasy.feature.common.ui.theme.delta
import com.ssafy.talkeasy.feature.common.ui.theme.md_theme_light_background
import com.ssafy.talkeasy.feature.common.ui.theme.sunset_orange
import com.ssafy.talkeasy.feature.common.ui.theme.typography
import com.ssafy.talkeasy.feature.common.util.ChatMode
import com.ssafy.talkeasy.feature.common.util.toTimeString
import com.ssafy.talkeasy.feature.follow.FollowViewModel
import com.ssafy.talkeasy.feature.follow.R

@Composable
internal fun FollowListRoute(
    modifier: Modifier = Modifier,
    navBackStackEntry: NavBackStackEntry,
    viewModel: FollowViewModel = hiltViewModel(navBackStackEntry),
    onClickedAddFollow: () -> Unit = {},
    onClickedNotification: () -> Unit = {},
    onClickedSettings: () -> Unit = {},
    onSelectedItem: () -> Unit,
) {
    val followList by rememberUpdatedState(newValue = viewModel.followList.collectAsState().value)
    FollowLisScreen(
        modifier = modifier,
        followList = followList ?: arrayListOf(),
        onClickedAddFollow = onClickedAddFollow,
        onClickedNotification = onClickedNotification,
        onClickedSettings = onClickedSettings,
        onSelectedItem = { follow ->
            viewModel.setSelectFollow(follow)
            onSelectedItem()
        }
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
internal fun FollowLisScreen(
    modifier: Modifier = Modifier,
    onClickedAddFollow: () -> Unit = {},
    onClickedNotification: () -> Unit = {},
    onClickedSettings: () -> Unit = {},
    followList: List<Follow> = arrayListOf(),
    onSelectedItem: (Follow) -> Unit = {},
) {
    Column() {
        FollowListHeader(
            modifier = modifier,
            onClickedAddFollow = onClickedAddFollow,
            onClickedNotification = onClickedNotification,
            onClickedSettings = onClickedSettings
        )

        FollowListContent(
            modifier = modifier,
            followList = followList,
            onSelectedItem = onSelectedItem
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FollowListHeader(
    modifier: Modifier = Modifier,
    onClickedAddFollow: () -> Unit = {},
    onClickedNotification: () -> Unit = {},
    onClickedSettings: () -> Unit = {},
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Text(
                modifier = modifier.padding(18.dp),
                text = stringResource(id = R.string.title_follow_list),
                style = typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(modifier = modifier.padding(end = 18.dp)) {
                IconButton(modifier = modifier, onClick = onClickedAddFollow) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_friend),
                        contentDescription = stringResource(
                            R.string.ic_add_user_text
                        ),
                        modifier = modifier.size(24.dp)
                    )
                }

                IconButton(modifier = modifier, onClick = onClickedNotification) {
                    Icon(
                        painter = painterResource(R.drawable.ic_notification_off),
                        contentDescription = stringResource(
                            R.string.ic_notification_text
                        ),
                        modifier = modifier.size(24.dp)
                    )
                }

                IconButton(modifier = modifier, onClick = onClickedSettings) {
                    Icon(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = stringResource(
                            R.string.ic_settings_text
                        ),
                        modifier = modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FollowListContent(
    modifier: Modifier = Modifier,
    onSelectedItem: (Follow) -> Unit,
    followList: List<Follow> = arrayListOf(),
) {
    if (followList.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(items = followList) { _, item ->
                FollowListItem(
                    item = item,
                    time = "",
                    newMessageCount = 0,
                    onSelectedItem = onSelectedItem
                )
            }
        }
    } else {
        NoContentLogoMessage(
            modifier = modifier,
            message = stringResource(id = R.string.content_no_follow_content),
            textStyle = typography.titleMedium,
            width = 156,
            height = 72,
            betweenValue = 20
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowListItem(
    modifier: Modifier = Modifier,
    item: Follow,
    time: String,
    newMessageCount: Int,
    onSelectedItem: (Follow) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelectedItem(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Profile(item.imageUrl, 56, ChatMode.CHAT)

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.userName,
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(modifier = modifier.width(10.dp))

                if (time.isNotEmpty()) {
                    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = time.toTimeString(),
                            style = typography.bodySmall,
                            color = delta
                        )
                    }
                }
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row() {
                    Text(
                        text = String.format(
                            stringResource(
                                id = R.string.content_gender_age,
                                if (item.gender == 0) {
                                    stringResource(id = R.string.content_man)
                                } else {
                                    stringResource(id = R.string.content_woman)
                                },
                                item.age ?: 0
                            )
                        ),
                        style = typography.bodyLarge,
                        color = cabbage_pont
                    )
                }

                if (newMessageCount > 0) {
                    Badge(
                        containerColor = sunset_orange,
                        contentColor = md_theme_light_background
                    ) {
                        Text(
                            text = if (newMessageCount >= 99) {
                                "+99"
                            } else {
                                newMessageCount.toString()
                            }
                        )
                    }
                }
            }
        }
    }
}