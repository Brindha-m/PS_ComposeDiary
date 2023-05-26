package com.implementing.pscomposediary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.implementing.pscomposediary.navigation.AppDestinations.MEETUP_DETAIL_ID_KEY
import com.implementing.pscomposediary.ui.meetup.MeetUpList
import com.implementing.pscomposediary.ui.meetupdetail.MeetupDetails

private object AppDestinations {
    const val MEETUP_ROUTE = "meetups"
    const val MEETUP_DETAIL_ROUTE = "meetup"
    const val MEETUP_DETAIL_ID_KEY = "meetupId"
}

@Composable
fun AppNavigation(
    startDestination: String = AppDestinations.MEETUP_ROUTE){

    val navController = rememberNavController()
    val actions = remember(navController) {
//        Constructor
        AppActions(navController)
    }

    NavHost(navController = navController, startDestination = startDestination)
    {
        composable(
            AppDestinations.MEETUP_ROUTE
        ) {
                MeetUpList(selectedMeetup = actions.selectedMeetup)
        }

        composable(
            "${AppDestinations.MEETUP_DETAIL_ROUTE}/{$MEETUP_DETAIL_ID_KEY}",
            arguments = listOf(
                navArgument(MEETUP_DETAIL_ID_KEY){
                    type = NavType.IntType
                }
            )
        )
        {
            navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            MeetupDetails(
                meetupId = arguments.getInt(MEETUP_DETAIL_ID_KEY),
                navigateUp = actions.navigateUp
            )
        }
    }

}




// Constructor
private class AppActions(navController: NavHostController) {
    val selectedMeetup: (Int) -> Unit = { meetupId: Int ->
        navController.navigate("${AppDestinations.MEETUP_DETAIL_ROUTE}/$meetupId")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}