# Movie App

A modern Kotlin Android app built with Jetpack Compose that allows users to browse a list of popular movies and view detailed information about each movie. The app fetches data from The Movie Database (TMDb) API and displays it in a clean and intuitive user interface.

## Features
* Movie List: Displays a list of popular movies with their posters, titles, release years, durations, and genres.

* Search Functionality: Allows users to search for movies by title, genre, or year.

* Movie Details: Displays detailed information about a selected movie, including its poster, title, release year, duration, genre, and storyline.

* Play, Download, and Share: Provides buttons to play, download, and share the movie.

* Responsive UI: Built with Jetpack Compose for a modern and responsive user interface.

## Screenshots
### Movie List Screen
<img src="https://github.com/user-attachments/assets/c49c3a02-0aef-4fec-a09a-217516e20219" alt="Movie List Screen Image" width="300" height="600">

### Movie Details Screen
<img src="https://github.com/user-attachments/assets/665ae3ed-92c8-4134-82dc-60c7fbb2a681" alt="Movie Details Screen Image" width="300" height="600">

### Error Screen
<img src="https://github.com/user-attachments/assets/18228f6c-9b92-4b90-89a7-66159cffbd4d" alt="Movie Details Screen Image" width="300" height="600">


## Technologies Used

* Jetpack Compose: For building the UI.

* Retrofit: For making API calls to TMDb.

* Coil: For loading and displaying images.

* Kotlin Coroutines: For asynchronous programming.

* ViewModel: For managing UI-related data.

* Navigation Compose: For handling navigation between screens.

## Setup
### Prerequisites

* Android Studio (latest version recommended).

* An API key from The Movie Database (TMDb).

### Steps
* Clone the Repository:
  ```  
    git clone https://github.com/BlauerTon/MovieAPI.git
    cd MovieAPI

* Add Your API Key:

    Open the RetrofitClient.kt file.

    Replace the placeholder API_KEY with your TMDb API key:
    ```
    const val API_KEY = "your_api_key_here"

* Run the App:

    Open the project in Android Studio.

    Connect an Android device or emulator.

    Click the "Run" button in Android Studio to build and run the app.

## API Reference
This app uses the TMDb API to fetch movie data. You can find more information about the API endpoints and parameters in the official documentation.

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

### Fork the repository.

* Create a new branch (git checkout -b feature/your-feature).

* Commit your changes (git commit -m 'Add some feature').

* Push to the branch (git push origin feature/your-feature).

* Open a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgments
* The Movie Database (TMDb) for providing the movie data.

* Jetpack Compose for making UI development easier and more fun.

