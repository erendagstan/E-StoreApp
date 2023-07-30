@file:OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)

package com.erendagstan.storeapp_v1

import android.annotation.SuppressLint
import android.service.controls.templates.ThumbnailTemplate
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

/*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
*/

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.erendagstan.storeapp_v1.ui.theme.BlackTextColor
import com.erendagstan.storeapp_v1.ui.theme.OrderNowBackground
import com.erendagstan.storeapp_v1.ui.theme.Typography
import java.security.Signature


@Composable
fun TshirtsScreen (navController: NavController,sharedViewModel: SharedViewModel){

    var products = sharedViewModel.products

    //var products_api = sharedViewModel.products_api


    val category = sharedViewModel.category
    LaunchedEffect(key1 = category, block = {
        if(category!=null){
            Log.i("CATEGORY:" ,category)
        }
    })

//    var productsFromAPI : List<Model?>


/*  for API
    if(category=="Jewelery"){
        products_api=sharedViewModel.products_api
        LaunchedEffect(key1 = products, block = {
            if(products_api!=null){
              for (index in products_api){
                  Log.i("PRODUCT-API PRO:","${index?.title}")
              }
            }
        })
    }
    if(category=="Electronics"){
        products_api=sharedViewModel.products_api
        LaunchedEffect(key1 = products, block = {
            if(products_api!=null){
                for (index in products_api){
                    Log.i("PRODUCT-API PRO:","${index?.title}")
                }
            }
        })
    }
*/
    /*productsFromAPI= products_api
    for (index in productsFromAPI){
        Log.i("HEY JEWE", "${index?.title}")
    }

    Log.i("HEY YOU","${productsFromAPI[0]?.title}")
  // }
  */


    if(category=="Jumpers"){
        products=sharedViewModel.productsJumpers
        LaunchedEffect(key1 = products, block = {
            if(products!=null){
                Log.i("Products:","${products[0]?.title}")
                Log.i("Products:","${products[1]?.title}")
            }
        })
    }


    if(category=="T-Shirts"){
        //get tshirts products from Home Screen via Shared View Model
        products = sharedViewModel.productsTshirt
        LaunchedEffect(key1 = products, block = {
            if(products!=null){
                Log.i("Products:","${products[0]?.title}")
                Log.i("Products:","${products[1]?.title}")
            }
        })
    }

    if(category=="Hoodies"){
        products=  sharedViewModel.productsHoodies
        LaunchedEffect(key1 = products, block = {
            if(products!=null){
                Log.i("Products:","${products[0]?.title}")
                Log.i("Products:","${products[1]?.title}")
            }
        })
    }
    val categoryData = category

    Scaffold(bottomBar = {
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), contentColor = Color.White, backgroundColor = Color.Black
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, bottom = 10.dp, top = 10.dp, end = 18.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.homeicon4),
                        contentDescription = "homeicon",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                navController.navigate(Screen.Home.route)
                            })
                    Icon(
                        painter = painterResource(id = R.drawable.wallet),
                        contentDescription = "walleticon",
                        modifier = Modifier.size(40.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.account),
                        contentDescription = "accounticon",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
    , topBar = {
        TopAppBar(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp), backgroundColor = Color.White, contentColor = Color.White) {
            DetailHeader(navController = navController)
        }
        }) {
        val bottomPading = it.calculateBottomPadding()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPading)
        ) {

                Text(text = categoryData, style = Typography.body1, fontSize = 22.sp, color = BlackTextColor,
                    modifier = Modifier.padding(start = 18.dp))
                Spacer(modifier = Modifier.height(5.dp))

                val topPadding = it.calculateTopPadding()
                val totalTopPadding = topPadding+28.dp
                Box(modifier = Modifier.fillMaxWidth()){
                    LazyVerticalGrid(GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(24.dp) //cells = Grid
                        , verticalArrangement = Arrangement.spacedBy(24.dp), modifier = Modifier
                            .absolutePadding(top = 30.dp)
                            .padding(start = 18.dp, bottom = 10.dp, end = 18.dp))//, modifier = Modifier.padding(top =totalTopPadding ))
                    {
                        this.items(products.size){
                            index -> BoxTshirt(
                            navController = navController,
                            productsData = products[index]!!,
                            sharedViewModel = sharedViewModel
                        )
                        }

                    }
                }
            }
        }
}



@Composable
fun BoxTshirt(navController: NavController,productsData: Products,sharedViewModel: SharedViewModel){
    val textStyleBody = Typography.body1
    var textStyle by remember {
        mutableStateOf(textStyleBody)
    }
    var readyToDraw by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier
        .height(360.dp)
        .clip(RoundedCornerShape(10.dp))
        .clickable {
            sharedViewModel.setProduct(productsData)
            navController.navigate(Screen.Detail.route)
        }
        .background(OrderNowBackground)
        , contentAlignment = Alignment.TopCenter){
        Column() {
            Box(modifier = Modifier
                .height(270.dp)
                .fillMaxWidth(), contentAlignment = Alignment.TopCenter){


                //using GLIDE
                //GlideImage(model = productsData.image.toString(), contentDescription ="image")
                Image(painter = painterResource(id = productsData.resId.toInt()), contentDescription = "tshirt")
            }
            Spacer(modifier = Modifier.height(2.dp))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 5.dp), contentAlignment = Alignment.Center){
                Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier
                        .height(40.dp)
                        .padding(start = 10.dp, end = 10.dp)){
                        Text(text = productsData.title, style = textStyle, modifier = Modifier.drawWithContent { if(readyToDraw) drawContent() }, onTextLayout = {
                            if(it.didOverflowHeight){
                                textStyle=textStyle.copy(fontSize = textStyle.fontSize*0.9)
                            }else{
                                readyToDraw=true
                            }
                        } )
                    }


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text ="$ "+ productsData.price.toString())
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(painter = painterResource(id = R.drawable.star), modifier = Modifier.size(15.dp), contentDescription = "star")
                        Text(text = productsData.rate.toString())
                    }
                    }
                }
                
            }
        }
      

}

@Preview(showBackground = true)
@Composable
fun TshirtsScreenPreview(){
    TshirtsScreen(navController = rememberNavController(), sharedViewModel = SharedViewModel())
}