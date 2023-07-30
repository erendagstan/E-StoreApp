package com.erendagstan.storeapp_v1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erendagstan.storeapp_v1.ui.theme.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel , storeAPIService: StoreAPIService) {
/*  FOR API

    sharedViewModel.getProductsFromAPI()
    sharedViewModel.getCategoriesFromAPI()
    val getElectronicsData = sharedViewModel.getElectronicsDataFromAPI("")
    sharedViewModel.getElectronicsDataFromAPI("electronics")

    val getJeweleryData =sharedViewModel.getJewelryDataFromAPI("")
    val listofcato : List<String> = sharedViewModel.categoryFromAPI
    for (index in listofcato){
        println(index)
    }
*/
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
                    Icon(painter = painterResource(id = R.drawable.homeicon4), contentDescription = "homeicon", modifier = Modifier.size(40.dp))
                    Icon(painter = painterResource(id = R.drawable.wallet), contentDescription = "homeicon", modifier = Modifier.size(40.dp))
                    Icon(painter = painterResource(id = R.drawable.account), contentDescription = "homeicon", modifier = Modifier.size(40.dp))
                }
            }
        }
    }) {
        val scrollState = rememberScrollState()
        val bottomPading=it.calculateBottomPadding()
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPading)){
            Column(modifier = Modifier.verticalScroll(state = scrollState)){

                Header()

                Spacer(modifier = Modifier.height(5.dp))

                OrderNowBox()

                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Categories",
                    fontSize = 22.sp,
                    style = Typography.body1,
                    color = BlackTextColor, modifier = Modifier.padding(start = 18.dp))


                Spacer(modifier = Modifier.height(20.dp))

                val tshirtsData : List<Products>
                tshirtsData= listOf(
                    Products("Oversize Tshirt",23.52,"Basic Oversize Tshirt",4.5,R.drawable.oversizetshirt,CategoryData(title = "Tshirt", resId = null)),
                    Products("Regular Tshirt",26.22,"Basic Regular Tshirt",4.3,R.drawable.regulartshirt,CategoryData(title = "Tshirt", resId = null))
                )

                val hoodiesData : List<Products>
                hoodiesData= listOf(
                    Products("Oversize Hoodie",23.52,"Hoodie 1",4.5,R.drawable.hoodie_1,CategoryData(title = "Hoodie", resId = null)),
                    Products("Regular Fit Hoodie",26.22,"Hoodie 2",4.3,R.drawable.hoodie_2,CategoryData(title = "Hoodie", resId = null)),
                    Products("Audere Hoodie",23.52,"Hoodie 3",4.5,R.drawable.hoodie_3,CategoryData(title = "Hoodie", resId = null)),
                    Products("Super Oversize Hoodie",26.22,"Hoodie 4",4.3,R.drawable.hoodie_4,CategoryData(title = "Hoodie", resId = null)),
                    Products("Hotoss Hoodie",26.22,"Hoodie 5",4.3,R.drawable.hoodie_5,CategoryData(title = "Hoodie", resId = null))
                )

                val jumpersData : List<Products>
                jumpersData= listOf(
                    Products("Brown Jumper",23.52,"Jumper 1",4.5,R.drawable.jumper1,CategoryData(title = "Jumper", resId = null)),
                    Products("Turtleneck Thick Jumper",26.22,"Jumper 2",4.3,R.drawable.jumper2,CategoryData(title = "Jumper", resId = null)),
                    Products("Turtleneck Thin Jumper",23.52,"Jumper 3",4.5,R.drawable.jumper3,CategoryData(title = "Jumper", resId = null)),
                    Products("Oversize Jumper",26.22,"Jumper 4",4.3,R.drawable.jumper4,CategoryData(title = "Jumper", resId = null)),
                    Products("Patterned Jumper",26.22,"Jumper 5",4.3,R.drawable.jumper5,CategoryData(title = "Jumper", resId = null))
                )


                //RETROFIT CATEGORY LIST
                //CategoryListFromAPI(categoryList = listofcato,sharedViewModel,navController)

//              Default Category List
                CategoryList(categoryData = listOf(CategoryData(resId = R.drawable.sweatshirt,"Hoodies"),
                    CategoryData(resId = R.drawable.jumper,"Jumpers"),
                    CategoryData(resId = R.drawable.tshirt,"T-Shirts"),
                    CategoryData(resId = R.drawable.jacket,"Jackets"),
                    CategoryData(resId = R.drawable.pant,"Pants"),
                    CategoryData(resId = R.drawable.cap,"Caps"),
                    CategoryData(resId = R.drawable.skirt,"Skirts"),
                    CategoryData(resId = R.drawable.dress,"Dresses"),
                    CategoryData(resId = R.drawable.dress,sharedViewModel.categoryFromAPI.toString())),navController,tshirtsData,hoodiesData,jumpersData,sharedViewModel)


                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Popular",
                    fontSize = 22.sp,
                    style = Typography.body1,
                    color = BlackTextColor, modifier = Modifier.padding(start = 18.dp))

                Spacer(modifier = Modifier.height(5.dp))

                PopularList(
                    popularList = listOf(
                    Products(
                        "Black Bomber Jacket",
                        price = 34.99,
                        "The style attributes of a bomber jacket (generally) include a cropped length, looser arms and shoulders, and ribbed collars, waistbands and cuffed sleeves.",
                        4.7,
                        R.drawable.blackbomberjacket,
                    ),
                    Products("Slim Fit Pant", 23.99, "Slim Fit Pant", 4.3, R.drawable.slimfitpant,),
                    Products(
                        "Basic Oversize T-shirt",
                        18.00,
                        "Basic Oversize T-shirt",
                        4.8,
                        R.drawable.oversizetshirt, 
                    ),
                    Products(
                        "Chicago Bulls Cap",
                        15.00,
                        "Chicago Bulls Cap",
                        5.0,
                        R.drawable.bullscap,
                    ),
                    Products(
                        "Metallica T-shirt",
                        20.00,
                        "Metallica T-shirt",
                        4.2,
                        R.drawable.metallicatshirt,
                    )
                ),navController,sharedViewModel)

            }

        }
    }

}

/*
@Composable
fun CategoryListFromAPI(categoryList: List<String>,sharedViewModel: SharedViewModel,navController: NavController){
    val selectedIndex = remember{
        mutableStateOf(value = 0)
    }
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 18.dp, end = 18.dp), horizontalArrangement = Arrangement.SpaceBetween){
        items(categoryList.size){
                index-> CategoryItemFromAPI(categoryList[index],selectedIndex, index = index,sharedViewModel,navController)

        }
    }
}

@Composable
fun CategoryItemFromAPI(categoryItemAPI : String,selectedIndex :MutableState<Int>, index: Int,sharedViewModel: SharedViewModel,navController: NavController){
    var products_api : List<Model?>


    Box(modifier = Modifier
        .size(width = 106.dp, height = 146.dp)
        .padding(end = 5.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable {

            selectedIndex.value = index

            if(categoryItemAPI=="electronics"){
                sharedViewModel.setCategory(categoryItemAPI.capitalize())
                sharedViewModel.getElectronicsDataFromAPI("electronics")
                products_api=sharedViewModel.products_api
                navController.navigate(route = Screen.Tshirts.route)
            }


            else if(categoryItemAPI=="jewelery"){
                sharedViewModel.setCategory(categoryItemAPI.capitalize())
                sharedViewModel.getJewelryDataFromAPI("jewelery")
                products_api= sharedViewModel.products_api
                navController.navigate(route = Screen.Tshirts.route)
            }


        }
        .background(
            if (selectedIndex.value == index) OrderNowBackground else CardItemBg
        )){
        Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))

            Icon(painter = painterResource(
                if (categoryItemAPI=="electronics") R.drawable.laptop
                else if (categoryItemAPI=="jewelery") R.drawable.jewelryicon
                else if (categoryItemAPI=="men's clothing") R.drawable.menclothes
                else  R.drawable.womenclothing), contentDescription = categoryItemAPI,
                modifier = Modifier.size(48.dp), tint = if(selectedIndex.value==index) Color.White else BlackTextColor)

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = categoryItemAPI.capitalize(), style = Typography.body2, fontSize = 18.sp, textAlign = TextAlign.Center)
            }

        }
    }
}
*/


@Composable
fun CategoryList(categoryData: List<CategoryData>,navController: NavController,tshirtsData : List<Products>,hoodiesData : List<Products>,jumpersData:List<Products>,sharedViewModel: SharedViewModel){
    val selectedIndex = remember{
        mutableStateOf(value = 0)
    }

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 18.dp, end = 18.dp), horizontalArrangement = Arrangement.SpaceBetween){
        items(categoryData.size){
            index-> CategoryItem(categoryData[index],selectedIndex,index, navController = navController,tshirtsData,hoodiesData,jumpersData,sharedViewModel)

        }
    }
}
@Composable
fun CategoryItem(categoryData: CategoryData, selectedIndex :MutableState<Int>, index: Int, navController: NavController, tshirtData:List<Products>, hoodiesData: List<Products>, jumpersData: List<Products>, sharedViewModel: SharedViewModel){
    Box(modifier = Modifier
        .size(width = 106.dp, height = 146.dp)
        .padding(end = 5.dp)
        .clip(RoundedCornerShape(16.dp))
        .clickable {
            selectedIndex.value = index

            /*
            if (categoryData.title == "Caps") {
                sharedViewModel.setCategory("Caps")
                sharedViewModel.getJewelryDataFromAPI()
                navController.navigate(route = Screen.Tshirts.route)
            }
            */

            if (categoryData.title == "Jumpers") {
                sharedViewModel.setCategory("Jumpers")
                sharedViewModel.addJumpersProducts(jumpersData)
                navController.navigate(route = Screen.Tshirts.route)
            }

            if (categoryData.title == "Hoodies") {
                sharedViewModel.setCategory("Hoodies")
                sharedViewModel.addHoodiesProducts(hoodiesData)
                navController.navigate(route = Screen.Tshirts.route)
            }

            if (categoryData.title == "T-Shirts") {
                sharedViewModel.setCategory("T-Shirts")
                sharedViewModel.addTshirtProducts(tshirtData)
                navController.navigate(route = Screen.Tshirts.route)
            }
        }
        .background(
            if (selectedIndex.value == index) OrderNowBackground else CardItemBg
        ), contentAlignment = Alignment.Center){
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = categoryData.resId!!), contentDescription = categoryData.title,
            modifier = Modifier.size(48.dp), tint = if(selectedIndex.value==index) Color.White else BlackTextColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = categoryData.title, style = Typography.body2, fontSize = 18.sp)

        }
    }
}


@Composable
fun PopularList(popularList: List<Products>, navController: NavController,sharedViewModel: SharedViewModel){
    val selectedItem = remember{
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        for(index in popularList){
            PopularItem(popularData = index, selectedIndex = selectedItem, popularList.indexOf(index), navController,sharedViewModel)
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp),color= Color.Black,1.dp)
        }
    }
    
}

@Composable
fun PopularItem(popularData: Products, selectedIndex : MutableState<Int> , index: Int, navController: NavController,sharedViewModel: SharedViewModel) {

    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(156.dp))
        {
            Box(modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .height(156.dp)
                .clickable {
                    selectedIndex.value = index
                    sharedViewModel.setProduct(popularData)
                    /* using current back stack entry
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "products",
                        value = popularData
                    )*/
                    //navController.popBackStack()
                    navController.navigate(Screen.Detail.route)
                }
                .clip(RoundedCornerShape(20.dp))
                .background(if (selectedIndex.value == index) OrderNowBackground else CardItemBg))
            {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = popularData.resId),
                        contentDescription = popularData.title,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(start = 2.dp)
                    )


                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(text = popularData.title, style = Typography.body2, fontSize = 18.sp)

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = "Star",
                                tint = Color.Black,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Text(
                                text = popularData.rate.toString(),
                                style = Typography.body2,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = "$", fontStyle = Typography.body1.fontStyle, fontSize = 13.sp)
                            Text(
                                text = popularData.price.toString(),
                                style = Typography.body2,
                                fontSize = 15.sp
                            )
                        }

                    }

                }

            }
            }
            }
        }


@Composable
fun Header(){

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 15.dp, end = 18.dp, bottom = 5.dp)
           ) {
            BoxWithRes(resId = R.drawable.menu, description = "Menu")


            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "GLORY",
                    style = Typography.body1,
                    fontSize = 40.sp,
                    color= Color.Black)
            }


            BoxWithRes(resId = R.drawable.search, description = "Search")
        }
    }


@Composable
fun OrderNowBox(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(165.dp)
        .padding(start = 18.dp, end = 15.dp, bottom = 18.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(OrderNowBackground))
    {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(start = 15.dp, top = 5.dp, end = 15.dp, bottom = 5.dp)) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color= Color.White,
                            fontStyle = Typography.body1.fontStyle
                        )
                    ){
                        append("New Arrivals\n"+
                                "New Beginnings\n"+
                        "with" )
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MarkaColor,
                            fontStyle = Typography.body1.fontStyle
                        )
                    ){
                        append(" GLORY")
                    }
                })

                Box(modifier = Modifier
                    .size(width = 126.dp, height = 40.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .background(Color.Black)
                    ,contentAlignment = Alignment.Center)
                {
                    Text(text = "SEE NOW"
                        , style = Typography.body1
                        , color = Color.White
                        , fontSize = 15.sp)
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Image(painter = painterResource(id = R.drawable.newclothes), contentDescription = "Location", modifier = Modifier.size(156.dp) , colorFilter = ColorFilter.tint(Color.Black))
        }
    }
}


@Composable
fun BoxWithRes(
    resId: Int,
    description: String,
    bgColor: Color?= CardItemBg,
    iconColor: Color?= IconColor,
    boxSize : Int?= 40,
    iconSize: Int?=24,
    navController: NavController?=null
){
    Box(
        modifier = Modifier
            .size(boxSize!!.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { navController?.popBackStack() }
            .background(bgColor!!),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = description,
        modifier = Modifier.size(iconSize!!.dp),
        tint = iconColor!!)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    HomeScreen(navController = navController, sharedViewModel = SharedViewModel(), storeAPIService = StoreAPIService())
}



/*
.clickable {
                    selectedIndex.value = index
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "popularData",
                        value = popularData
                    )
                    navController.navigate("detail_screen")
 */