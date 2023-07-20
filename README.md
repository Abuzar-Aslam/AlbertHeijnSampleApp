## SetUp

Clone the repository from GitHub:

git clone https://github.com/Abuzar-Aslam/AlbertHeijnSampleApp.git

Open the project in Android Studio.

Create a gradle.properties file in the project's root directory if it does not exist.

Add your API key from The Cat API in the gradle.properties file:

**Replace YOUR_API_KEY with your actual API key from the [https://thecatapi.com/]**
- apiAccessKey="YOUR_API_KEY"

**This API key is required to access the images of cat breeds from The Cat API.**

## Architecture

The app follows the Clean Architecture principles and is divided into three layers: data, domain, and presentation. Each layer has its own responsibilities and dependencies.

# Data Layer
The data layer is responsible for retrieving data from external sources, such as APIs or databases. It consists of the following components:

- DataSource: The DataSource interface defines the contract for retrieving data from the API. 
- BreedingPagingSource: The BreedPagingSource class implements the PagingSource interface and handles pagination and data loading from the API using dataSource

- Model: The BreedResponse, ImageResponse, BreedDetailResponse and BreedDetailItemResponse data classes represent the response models for a breedListing and breedDetail response.

- Repository: The BreedRepository and BreedDetailRepository interface defines the contract for accessing breeds data. The BreedRepositoryImpl and breedDetailRepositoryImpl classes implements these interfaces and interacts with the BreedPagingSource and dataSource to retrieve paginated breed data and breed detail, respectively.

# Domain Layer

The domain layer contains the business logic and models of the application. It includes the following components:

- Model: The BreedResult, ImageResult, BreedDetailResult and BreedDetailItemResult data classes represent the domain models for a breed listing and breed detail, respectively.

- Use Case: The BreedUseCase and BreedDetailUseCase classes provides methods to retrieve a list of breeds and details of breeds from the data layer and maps the data models to the domain models.

# Presentation Layer
The presentation layer handles UI-related logic and user interactions. It uses Jetpack Compose for building the UI. Key components include:

- ViewModel: The BreedListViewModel and BreedDetailViewModel classes is responsible for managing the state and business logic of the main screen. It interacts with the BreedUseCase and BreedDetailUseCase to retrieve breeds and handles UI actions, respectively.

- UI: The BreedListUI and BreedDetailUI composable function represents the screen for showing breed listing and detail in the app. It displays a list of cat breeds using the BetItem and displayBreedList composable functions and show breed details using DetailUI and TableRow. BreedListViewModel and BreedDetailViewModel handle their own specific UI states, and error handling is done using the ErrorSnackbar composable.

# Dependencies
The app utilizes the following libraries and tools:

- Retrofit: For making API requests to The Cat API.
- Hilt: For dependency injection and managing app-level dependencies.
- Jetpack Compose: For building the user interface.
- Compose Navigation: For Navigation between composables.
- Paging 3: For implementing pagination in the list of cat breeds.
- Coil: For loading and displaying images efficiently.
- ViewModel: For managing UI-related data and state.

# Summary

The app follows the Clean Architecture principles and is divided into three layers: data, domain, and presentation. Each layer has its own responsibilities and dependencies.

- Data Layer: Responsible for retrieving data from external sources such as APIs or databases. It includes DataSource, BreedPagingSource, and Repository components. The data layer uses the BreedResponse, ImageResponse, BreedDetailResponse, and BreedDetailItemResponse models to represent API responses.

- Domain Layer: Contains the business logic and domain models of the application. It includes the BreedResult, ImageResult, BreedDetailResult, and BreedDetailItemResult data classes. The domain layer also provides Use Cases (BreedUseCase and BreedDetailUseCase) for accessing data from the data layer and mapping it to domain models.

- Presentation Layer: Handles UI-related logic and user interactions. It uses Jetpack Compose for building the UI. The presentation layer includes ViewModel classes (BreedListViewModel and BreedDetailViewModel) to manage UI state and interact with the Use Cases for data retrieval. The UI is composed of composable functions such as BreedListUI, BreedDetailUI, DetailUI, and TableRow.


- Conclusion
AlbertHeijn SampleApp is an example of a modern Android app following the Clean Architecture principles, utilizing Jetpack Compose for UI, compose Navigation to navigate between compositions and showcasing best practices in Android development. The app allows users to explore a list and details of cat breeds and view their images and temperaments.

# Future Improvements

- Add more test coverage which includes (Unit, UI)
- Save the data in local database to support offline usage of the application
- better data loading, first page data loading to splash screen for better user experience