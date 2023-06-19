# ComposeDiary

<!-- <img width="302" alt="Screenshot_20230603_122540-removebg-preview" src="https://github.com/Brindha-m/PS_ComposeDiary/assets/72887609/5df62eb1-0ecf-456a-8f40-2e7242aab807"> -->


## Learned 💭

| Component | Description |
|-----------|-------------|
| **Jetpack Compose** | Modern UI toolkit for building native Android apps, without XML creating UI With Kotlin. |
| **Coil** | An image loading library with caching capabilities for Android that is compatible with Jetpack Compose. |
| **Confetti Party Popper Effect** | 🎉🎉 |
| **Firestore** | Firestore is a NoSQL document database provided by Firebase. Store and retrieve data for your app. |
| **LazyRows and LazyColumns** | Replacement for RecyclerView and offer improved performance and simpler implementation for displaying large datasets |
| **Lottie Animation** | Animation library that allows you to render and play back After Effects animations natively in your Android app.|
| **Material 3** | Latest version of the Material Design, it offers a fresh and modern approach to UI design in Android apps.|
| **Navigation-Compose** | To handle navigation within your app, including features like navigation graphs, destinations, arguments passing, and deep linking. |
| **Scaffold** | Common UI elements such as AppBar and FloatingActionButton etc.. |
| **TopAppBars** | Commonly used for titles, menus, and action buttons in your app's UI. |

### UI/theme default Components in Compose

| **File** | **Context** |
|-----------|-------------|
| **Type.kt** | For custom Fonts settings |
| **Theme.kt** | For BG Colors and inapp content color theme |
| **Color.kt** | colorScheme for the Navigation bar and System Bar Colors |


## Firebase
```
    var meetups by remember { mutableStateOf(emptyList<MeetUp>()) }

    // Connect to Firebase and retrieve meetups
    // Fetch meetups from Firebase Firestor
    LaunchedEffect(Unit) {
        retrieveMeetupsFromFirebase(context) { meetupsList ->
            meetups = meetupsList
        }
    }
    
    // "Here MeetUp IS MY DATA CLASS"
```


For retrieval from firebase-firestore

```

private fun retrieveMeetupsFromFirebase(
    context: Context,
    onMeetupsLoaded: (List<MeetUp>) -> Unit
){
    val db = FirebaseFirestore.getInstance()
    val meetupsCollection = db.collection("meetupProvider") // Replace with your Firestore collection name

    meetupsCollection.get()
        .addOnSuccessListener { result ->
            val meetupsList = mutableListOf<MeetUp>()

            for (document in result) {
                val meetup = document.toObject(MeetUp::class.java)
                meetupsList.add(meetup)
            }
            onMeetupsLoaded(meetupsList)
        }
        .addOnFailureListener { exception ->
            exception.printStackTrace()
        }

}
    
```
<img width="400" alt="Screenshot_20230603_122540-removebg-preview" src="https://github.com/Brindha-m/PS_ComposeDiary/assets/72887609/23f96272-5297-4d33-bd4e-8bc5a0624186">
