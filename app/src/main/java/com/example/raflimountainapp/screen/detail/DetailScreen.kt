package com.example.raflimountainapp.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raflimountainapp.ViewModelFactory
import com.example.raflimountainapp.di.Injection

@Composable
fun DetailScreen(
    id: Long,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository()))
){
    val mountainData = viewModel.getMountainId(id)
    DetailContent(
        image = mountainData.image,
        name = mountainData.name,
        location = mountainData.famous,
        famous = mountainData.location,
        description = mountainData.descriptions,
        onBackClick = navigateBack,
    )
}

@Composable
fun DetailContent(
    image: Int,
    name: String,
    location: String,
    famous: String,
    description: String,
    onBackClick: () -> Unit,
    modifier : Modifier = Modifier
){
    Column (modifier = modifier){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.height(350.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.padding(16.dp).clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                Text(
                    text = famous,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
                Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
                Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                Text(
                    text = location,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
}