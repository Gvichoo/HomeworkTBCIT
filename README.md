1. Purpose of the Application
The Events Application allows users to register, log in, and browse a variety of events. Users can save events to their personal collection,
 view event details, and manage their profile. The application also provides features to change language preferences, log out, and delete session data.
It's designed to keep users engaged with upcoming events and offer a seamless experience for browsing and managing their event preferences.

2. How to Use the Application
Follow the steps below to interact with the application:

Registration and Login
Register: If you’re a new user, you can register using your email and password. After registration, you’ll be logged in automatically.
Login: If you’re an existing user, enter your credentials to log in. Once logged in, you’ll be redirected to the Events screen.
Events Fragment
The Events fragment fetches event data from an API and stores it in a local Room database.
Browse through the list of events presented in a RecyclerView.
Event Details: Tap on any event to view its detailed information in a new fragment.
Add to Store: If you want to save an event to your personal collection, tap the "Add to Store" button. This event will be stored in both the RecyclerView and the Room database.
Delete Event: To delete an event, press and hold on the event in the RecyclerView. It will be removed from both the RecyclerView and the Room database.
Profile Fragment
View the email you used to log in vith Gmail.
Access your Settings from the Profile fragment, where you can:
Log Out: Log out and delete your session data if the "Remember me" checkbox is checked.
Change Language: Select the language using a Spinner (from Georgian to English) and press the save button.
Add Events Fragment
use this fragment to add new events to the RecyclerView.

3. Technologies and Attributes Used
This application leverages several modern Android development tools and practices:

Firebase Authentication: For user login and registration.
Room Database: To store events locally on the device.
Retrofit: For fetching event data from the API.
Hilt/Dagger: For dependency injection and managing app dependencies.
SafeArgs: For safe navigation between fragments with type-safety.
Flows: Used for managing UI state.
ViewPager: For navigating between fragments.
RecyclerView: For displaying events in a list.
Spinner: For language selection (Georgian and English).
DataBinding/ViewBinding: For simplifying UI interactions and binding views in a type-safe manner.
Base ViewModel: A custom BaseViewModel class, which is extended by other ViewModels to manage state, handle events, and emit side effects (using StateFlow and MutableSharedFlow).
