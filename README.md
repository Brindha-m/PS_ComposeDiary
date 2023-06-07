# ComposeDiary

<!-- <img width="302" alt="Screenshot_20230603_122540-removebg-preview" src="https://github.com/Brindha-m/PS_ComposeDiary/assets/72887609/5df62eb1-0ecf-456a-8f40-2e7242aab807"> -->

Utilizing the jetpack compose with lazycolumns &amp; lazyrows.

## Learned

```
    
    1. Jetpack Compose - Without XML creating UI With Kotlin, Made Dynamic Data providers.
    
    2. @Composable
    
    3. Surface(), Column(), Modifiers, Padding, Align
    
    4. Instead of Recycler view - LazyRows and LazyColumns
    
    5. AppNavigation - Sealed Class, Onclick Activities
    
    6. FlowRows(), FlowColumns(),
    
    7. Confetti Party popper effect
    
    8. Scaffold - TopAppBar
    
    9. Material3 - Buttons, Text, Icon, Images etc..
    
    10. theme - Type(For custom Fonts), Theme(For BG Colors and inapp content color theme), Shapes & Colors.
    
```

## TODOS

```
   1. Room Database
   
   2. Efficient and fast retrieval of images - firebase ? want to work even in offline
   
   3. Login auths
   
```


## Firebase
```
    var meetups by remember { mutableStateOf(emptyList<MeetUp>()) }

    // Connect to Firebase and retrieve meetups
    // Fetch meetups from Firebase Firestore
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
