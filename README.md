# News App
![NewsA App](/docs/banner.png)

This is an App for showing the List of News from [News API](https://newsapi.org/).


# Features
**News App** is a List-Detail app that contains 2 screen, List News & Detail News. 


## Design Inspiration
For the UI Design, the list page I was inspired by [Dicky Bhismawan H's work](https://www.figma.com/community/file/975336242667665188)
![Dicky Bhismawan H Design](/docs/inspiration.png)


## Tech stack & Open-source library
- Min SDK 21
- Kotlin based, Coroutines + Flow for asynchronous
- JetPack Compose
- StateFlow
- Hilt
- Architecture
  - MVVM, Model View ViewModel
  - Clean Architecture (Presenter/View - Data Layer)
- Retrofit2 & OkHttp3
- Gson
- Glide


## Architecture
**News App** is based on the Model View ViewModel architecture and Clean Architecture, which follows the [Guide to app architecture](https://developer.android.com/topic/architecture#modern-app-architecture).
![Typical App Architecture](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png)


The architecture of **News App** is composed by two layers, the *UI Layer* and *Data Layer*.  For now, it doesn't use the domain layer. 
![Architecture Overview](/docs/architecture.jpg)

## Demo
[Download APK](/demo/app-debug.apk)

### Contact Person
👨 : Muhammad Naufal Fadhillah
✉ : m.naufal.fadhillah98@gmail.com
