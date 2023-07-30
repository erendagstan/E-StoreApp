package com.erendagstan.storeapp_v1

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erendagstan.storeapp_v1.ui.theme.*

data class Comment(val author :String, val msg_body:String, val resId :Int)


@Composable
fun DetailScreen (navController: NavController,sharedViewModel: SharedViewModel){
    val product = sharedViewModel.product
    Log.i("Popular Product : ", product!!.title)

    Scaffold(bottomBar = {
        BottomAppBar( modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            , contentColor = Color.White, backgroundColor = Color.Black) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), contentAlignment = Alignment.Center){
                Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, bottom = 10.dp, top = 10.dp, end = 18.dp)) {
                    Icon(painter = painterResource(id = R.drawable.homeicon4), contentDescription = "homeicon",
                        modifier = Modifier.size(40.dp).clickable {
                            navController.navigate(Screen.Home.route)
                        })
                    Icon(painter = painterResource(id = R.drawable.wallet), contentDescription = "walleticon", modifier = Modifier.size(40.dp))
                    Icon(painter = painterResource(id = R.drawable.account), contentDescription = "accounticon", modifier = Modifier.size(40.dp))
                }
            }
        }
    }) {
        val scrollState = rememberScrollState()
        val bottomPading = it.calculateBottomPadding()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPading)
        ) {
            Column(modifier = Modifier.verticalScroll(state = scrollState)) {

                DetailHeader(navController = navController)

                Spacer(modifier = Modifier.height(5.dp))

                PopularPhoto(popularData = product)

                Spacer(modifier = Modifier.height(5.dp))

                PopularDataTitle(popularData = product)
                
                Spacer(modifier = Modifier.height(5.dp))

                DetailContent(popularData = product)

                Spacer(modifier = Modifier.height(5.dp))

                AddCard()

                Spacer(modifier = Modifier.height(2.dp))

                ShowCommentsText()

                PopularDataComments()


            }


        }

    }
}

@Composable
fun ShowCommentsText(){
    Column(horizontalAlignment =Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween
        , modifier = Modifier
            .padding(start = 18.dp, bottom = 10.dp, end = 18.dp)
            .fillMaxWidth()) {
        Text(text ="Comments", style = Typography.body2, fontSize = 15.sp, color = ShowCommentsColor)
        Icon(painter = painterResource(id = R.drawable.bottomicon), contentDescription = "show comments icon",
        tint = ShowCommentsColor, modifier = Modifier.size(10.dp))

    }
}

@Composable
fun PopularDataComments(){
    val erenComment=Comment("Eren Dagistan","Perfect",R.drawable.person)
    val iremComment= Comment("Irem Dagistan","Awesome!!",R.drawable.person2)
    val kateComment = Comment("Kate Amber","The fit and fabric of the jacket is very good. Its size is not perfect for me. I said S, it was like an M on me. I think order one size smaller." ,R.drawable.person3)
    CommentCard(comment = erenComment)
    CommentCard(comment = iremComment)
    CommentCard(comment = kateComment)

}


@Composable
fun CommentCard(comment : Comment){
    Row(modifier = Modifier.padding(start = 18.dp, bottom = 2.dp, top = 2.dp, end = 18.dp)) {
        Image(painter = painterResource(id = comment.resId), contentDescription = "person photo",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this variable
        var isExpanded by remember {
            mutableStateOf(false)
        }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) OrderNowBackground else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable { isExpanded=!isExpanded }) {
            Text(text = comment.author, color = BlackTextColor, style = Typography.body1, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(1.dp))
            Surface(shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
                ) {
                Text(text = comment.msg_body, modifier = Modifier.padding(start = 3.dp),
                maxLines = if(isExpanded) Int.MAX_VALUE else 1, style = Typography.body2, fontSize = 16.sp)
            }

        }



    }
}
@Composable
fun ColorPickerBox(color: Color){
    Box(modifier = Modifier
        .width(40.dp)
        .height(40.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(color)

    )
}

@Composable
fun DetailContent(popularData: Products){
    Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 18.dp, end = 18.dp)) {

        Box(modifier = Modifier.padding(10.dp)){
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Color", fontSize = 18.sp, style = Typography.body1, color = BlackTextColor)
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    ColorPickerBox(color = Color.Black)
                    Spacer(modifier = Modifier.width(2.dp))
                    ColorPickerBox(color= Color.Blue)
                    Spacer(modifier = Modifier.width(2.dp))
                    ColorPickerBox(color= Color.Gray)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Price", fontSize = 18.sp, style = Typography.body1, color = BlackTextColor)
                Text(text = "$ ${popularData.price}", fontSize = 15.sp, style = Typography.body1, color = DetailsTextColor)
            }
        }
        Box(modifier = Modifier.padding(10.dp)){
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Size", fontSize = 18.sp, style = Typography.body1, color = BlackTextColor)
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    SizePickerBox(size = "S")
                    Spacer(modifier = Modifier.width(2.dp))
                    SizePickerBox(size = "M")
                    Spacer(modifier = Modifier.width(2.dp))
                    SizePickerBox(size = "L")
                    Spacer(modifier = Modifier.width(2.dp))
                    SizePickerBox(size = "XL")
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Rate", fontSize = 18.sp, style = Typography.body1, color = BlackTextColor)
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.star), contentDescription = "star", modifier = Modifier.size(12.dp))
                    Text(text = popularData.rate.toString(), fontSize = 15.sp, style = Typography.body1,color= DetailsTextColor)
                }
            }
        }
    }
}

@Composable
fun AddCard(){
    val selectedAddCard = remember {
        mutableStateOf(0)
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 18.dp, bottom = 10.dp, end = 18.dp, top = 10.dp)
        .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .background(
            if (selectedAddCard.value == 1) {
                Color.Black
            } else {
                Color.White
            }
        )
        .height(50.dp)
        .clickable {
            selectedAddCard.value = 1
        }, contentAlignment = Alignment.Center){
        Text(text = "Add to Card", style = Typography.body1
            , fontSize = 22.sp
            , color = if(selectedAddCard.value==1) Color.White else BlackTextColor)
    }
}

@Composable
fun PopularDataTitle(popularData:Products){
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = popularData.title,
            fontSize = 22.sp,
            style = Typography.body1,
            color = BlackTextColor, modifier = Modifier.padding(start = 18.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Icon(painter = painterResource(id = R.drawable.i_icon), contentDescription = "information icon", modifier = Modifier
            .size(20.dp)
            .clickable {
                //show popular data description
            })
    }
}


@Composable
fun SizePickerBox(size:String){
    Box(modifier = Modifier
        .width(40.dp)
        .height(40.dp)
        .border(2.dp, color = Color.Black, RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .background(Color.White), contentAlignment = Alignment.Center
    ){
        Text(text = size, style = Typography.body1, fontSize = 15.sp, color = DetailsTextColor)
    }
}



@Composable
fun PopularPhoto(popularData: Products){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 18.dp, bottom = 10.dp, top = 10.dp, end = 18.dp)
        .height(300.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(OrderNowBackground)){
        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            BoxWithRes(resId = R.drawable.backpop, description = "backicon", bgColor = OrderNowBackground)
            Image(painter = painterResource(id = popularData.resId), contentDescription = "popular item", modifier = Modifier
                .height(300.dp)
                .width(200.dp))
            BoxWithRes(resId = R.drawable.nextpop, description = "backicon", bgColor = OrderNowBackground)
        }

    }
}
@Composable
fun DetailHeader(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            , modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 10.dp, end = 18.dp)) {
            BoxWithRes(resId = R.drawable.arrow_left, description = "backicon", navController = navController, bgColor = OrderNowBackground)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "GLORY",
                    style = Typography.body1,
                    fontSize = 30.sp,
                    color= Color.Black)
            }
            BoxWithRes(resId = R.drawable.bag, description = "backicon", navController = navController, bgColor = OrderNowBackground)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    DetailScreen(navController = rememberNavController(), sharedViewModel = SharedViewModel())
}