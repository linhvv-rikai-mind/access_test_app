# App Exercise
This app layers its presentation through MVVM presentation pattern.

* UI
    * [Compose](https://developer.android.com/jetpack/compose) declarative UI framework
    * [Material design](https://material.io/design)


* Tech/Tools
  * [Kotlin](https://kotlinlang.org/) 100% coverage
  * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow) for async operations
  * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
  * [Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose)
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) for navigation between composable
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that stores, exposes and manages UI state
  * [Retrofit](https://square.github.io/retrofit/) for networking
  * [Coil](https://github.com/coil-kt/coil) for image loading

* Modern Architecture
  * Single activity architecture (with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)) that defines navigation graphs
  * MVVM for presentation layer
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions

## Presentation patterns layers
* Model - Data source classes that retrieve content. In a Clean architecture context, one could use UseCases or Interacts that tap into repositories or data sources directly.
* View - Composable screens that consume state, apply effects and delegate events upstream.
* ViewModel - [AAC ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that manages and set the state of the corresponding screen. Additionally, it intercepts UI events as callbacks and produces side-effects. The ViewModel is scoped to the lifetime of the corresponding screen composable in the backstack.


### Dependency injection
[Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is used for Dependency Injection as a wrapper on top of [Dagger](https://github.com/google/dagger).

Most of the dependencies are injected with `@Singleton` scope and are provided within the `UserApiModule` module.

For ViewModels, we use the out-of-the-box `@HiltViewModel` annotation that injects them with the scope of the navigation graph composable that represent the screens.
