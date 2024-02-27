package com.example.raflimountainapp.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raflimountainapp.R
import com.example.raflimountainapp.ViewModelFactory
import com.example.raflimountainapp.di.Injection

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RafliMountainApp(
    viewModel: RafliMountainAppViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
){
    val sortmountain by viewModel.sortedMountain.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier){
        LazyColumn {
            item{
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colorScheme.tertiary)
                )
            }
            sortmountain.forEach{ (_, mountains) ->
                items(mountains, key = { it.id }){ mountain ->
                    MountainListItem(
                        id = mountain.id,
                        name = mountain.name,
                        image = mountain.image,
                        description = mountain.famous,
                        navigateToDetail = navigateToDetail,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 10))
                    )
                }
            }
        }
    }
}

@Composable
fun MountainListItem(
    id: Long,
    name: String,
    image: Int,
    description: String,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                navigateToDetail(id)
            }
            .padding(15.dp)
    ){
        Image(
            painter = painterResource(id = image), contentDescription = name, contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(150.dp))
        Column (modifier = Modifier.padding(10.dp)){
            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 20.sp
            )
            Text(
                text = description,
                fontSize = 15.sp,
                minLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query:String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    androidx.compose.material3.SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = { Text(text = stringResource(id = R.string.mountain_seach)) },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 5.dp)
    ){

    }
}