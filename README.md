## SetUp

Clone the repository from GitHub:

git clone https://github.com/Abuzar-Aslam/AlbertHeijnSampleApp.git


2. Open the project in Android Studio.

3. Click on the run button to see the app in the emulator.

## NDK Integration and Securing API Key

The app leverages NDK (Native Development Kit) to securely store the API key in native C code. By storing the API key in the NDK, we ensure that it remains protected and is not easily exposed or accessible from the Java/Kotlin code. The benefits of using NDK for this purpose include:

1. Enhanced Security: Storing the API key in native C code adds an extra layer of security, making it harder for potential attackers to extract the key through reverse engineering.

2. Reduced Exposure: The API key is not visible in the Java/Kotlin code or resources, as it is embedded within the compiled library. This minimizes the risk of unauthorized access.

3. Easy Maintenance: Separating the API key from the main codebase simplifies updates and maintenance. Changes to the API key can be made in the native C code without affecting the Java/Kotlin code.

4. Obfuscation Support: Combined with code obfuscation techniques, NDK integration further enhances the app's overall security, making it more resilient against unauthorized access.

## Architecture

The app adheres to the Clean Architecture principles and is organized into three distinct layers: data, domain, and presentation. Each layer has well-defined responsibilities and clear dependencies.

### Data Layer

The data layer is responsible for fetching data from external sources, such as APIs or databases. Its components include:

- DataSource: The DataSource interface defines the contract for retrieving data from the API.
- BreedingPagingSource: The BreedPagingSource class implements the PagingSource interface and handles pagination and data loading from the API using dataSource.

- Model: The BreedResponse, ImageResponse, BreedDetailResponse, and BreedDetailItemResponse data classes represent the response models for breed listing and breed detail responses.

- Repository: The BreedRepository and BreedDetailRepository interface define the contract for accessing breed data. The BreedRepositoryImpl and BreedDetailRepositoryImpl classes implement these interfaces and interact with the BreedPagingSource and dataSource to retrieve paginated breed data and breed details, respectively.

### Domain Layer

The domain layer contains the business logic and models of the application. Its components include:

- Model: The BreedResult, ImageResult, BreedDetailResult, and BreedDetailItemResult data classes represent the domain models for breed listing and breed detail, respectively.

- Use Case: The BreedUseCase and BreedDetailUseCase classes provide methods to retrieve a list of breeds and details of breeds from the data layer and map the data models to the domain models.

### Presentation Layer

The presentation layer handles UI-related logic and user interactions, utilizing Jetpack Compose for building the UI. Key components include:

- ViewModel: The BreedListViewModel and BreedDetailViewModel classes are responsible for managing the state and business logic of the main screen. They interact with the BreedUseCase and BreedDetailUseCase to retrieve breeds and handle UI actions, respectively.

- UI: The BreedListUI and BreedDetailUI composable functions represent the screen for showing breed listing and detail in the app. They display a list of cat breeds using the BetItem and displayBreedList composable functions and show breed details using DetailUI and TableRow. BreedListViewModel and BreedDetailViewModel handle their specific UI states, and error handling is done using the ErrorSnackbar composable.

## Dependencies

The app utilizes the following libraries and tools:

- Retrofit: For making API requests to The Cat API.
- Hilt: For dependency injection and managing app-level dependencies.
- Jetpack Compose: For building the user interface.
- Compose Navigation: For Navigation between composables.
- Paging 3: For implementing pagination in the list of cat breeds.
- Coil: For loading and displaying images efficiently.
- ViewModel: For managing UI-related data and state.

## Summary

AlbertHeijn SampleApp exemplifies a modern Android app that follows the Clean Architecture principles. Leveraging Jetpack Compose for UI and compose Navigation for navigation between compositions, the app showcases best practices in Android development. Users can explore a list and details of cat breeds and view their images and temperaments.

## Future Improvements

- Enhance test coverage, including Unit and UI testing.
- Implement local database storage to support offline usage of the application.
- Optimize data loading, implement first-page data loading for a smoother user experience.
- Add the search functionality on Listing page