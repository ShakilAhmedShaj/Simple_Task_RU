<h2 style="margin-bottom: 0;" align="center">Simple Task-RU</h2>

<p align="center">
<img src="https://user-images.githubusercontent.com/15268903/101111325-29dcba80-3605-11eb-8ac1-409b7c9676ca.png" height="100" width="100">
<h3 style="margin-top: 0;" align="center">Simple Task App using MVVM with Online Syncing</h3>
</p>

## App Screenshots
<table>
  <tr>
     <td align="center">Register & Login</td>
     <td align="center">UX</td>
     <td align="center">Search</td>
     <td align="center">Task Details</td>
     <td align="center">Add Task</td>
  </tr>
  <tr>
    <td valign="top"><img src="https://user-images.githubusercontent.com/15268903/101110835-1bda6a00-3604-11eb-8134-89fc8de97d73.gif" height="400" width="200"></td>
    <td valign="top"><img src="https://user-images.githubusercontent.com/15268903/101110904-3f9db000-3604-11eb-965e-7a58324025f4.gif" height="400" width="200"></td>
    <td valign="top"><img src="https://user-images.githubusercontent.com/15268903/101110968-5ba15180-3604-11eb-8d91-5e8293c89696.gif" height="400" width="200"></td>
    <td valign="top"><img src="https://user-images.githubusercontent.com/15268903/101111123-be92e880-3604-11eb-8e29-97c18acc7b4e.gif" height="400" width="200"></td>
    <td valign="top"><img src="https://user-images.githubusercontent.com/15268903/101111055-8be8f000-3604-11eb-831e-727a3bde7d46.gif" height="400" width="200"></td>
  </tr>
 </table>
 <br>


 ## Task
<table>
  <tr>
    <td valign="top"><img src="https://user-images.githubusercontent.com/15268903/101111569-ac657a00-3605-11eb-98ed-7ec8318b24f0.png"></td>
    <td valign="top"><img src="https://user-images.githubusercontent.com/15268903/101111678-e20a6300-3605-11eb-995d-52d779ebdef0.png"></td>
  </tr>
 </table>
 <br>

 ## Backend

* Laravel [Source](https://github.com/ShakilAhmedShaj/laravel_todo_api)


## Features

* Online Register & Login
* Search Task
* Add Task
* Save with Room
* Profile

### Built With

* Android Studio 4.1.1 The latest version can be downloaded from [here](https://developer.android.com/studio/)
* Build gradle 4.1.0
* Android SDK 30
* Kotlin Version 1.4.10

## Libraries Used
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [Room](https://developer.android.com/topic/libraries/architecture/room)
* [Retrofit](https://square.github.io/retrofit/)
* [Lottie](https://airbnb.io/lottie/#/android)
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
* [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)

### Install the apk

<a href="https://shakilahmedshaj.com/apks/SimpleTaskRU.apk"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" width="185" height="70"/></a>

Go to the following [link](https://shakilahmedshaj.com/apks/SimpleTaskRU.apk) to download the app.

### Directory Structure

The following is a high level overview of relevant files and folders.

```
SimpleTask
    └───app
        └───src
            └───main
                └───java
                    └───com
                        └───decimalab
                            └───simpletask
                                ├───base
                                │       BaseFragment.kt
                                │       BaseRepository.kt
                                │       BaseViewModelFactory.kt
                                │
                                ├───data
                                │   ├───local
                                │   │   ├───dao
                                │   │   │       TaskDao.kt
                                │   │   │
                                │   │   ├───datastore
                                │   │   │       UserPreferences.kt
                                │   │   │
                                │   │   ├───db
                                │   │   │       AppDatabase.kt
                                │   │   │
                                │   │   ├───entity
                                │   │   │       TaskEntity.kt
                                │   │   │
                                │   │   └───model
                                │   │           Priority.kt
                                │   │
                                │   ├───remote
                                │   │   ├───model
                                │   │   │   │   Message.kt
                                │   │   │   │   User.kt
                                │   │   │   │
                                │   │   │   ├───request
                                │   │   │   │   ├───auth
                                │   │   │   │   │       LoginRequest.kt
                                │   │   │   │   │       SignUpRequest.kt
                                │   │   │   │   │
                                │   │   │   │   └───task
                                │   │   │   │           AddTaskRequest.kt
                                │   │   │   │
                                │   │   │   └───response
                                │   │   │       ├───auth
                                │   │   │       │   │   ValidateResponse.kt
                                │   │   │       │   │
                                │   │   │       │   ├───login
                                │   │   │       │   │       LoginResponse.kt
                                │   │   │       │   │
                                │   │   │       │   └───signup
                                │   │   │       │           SignUpResponse.kt
                                │   │   │       │
                                │   │   │       └───task
                                │   │   │               AddTaskResponse.kt
                                │   │   │               AllTaskResponse.kt
                                │   │   │
                                │   │   └───network
                                │   │           ApiService.kt
                                │   │           EndPoints.kt
                                │   │           RemoteDataSource.kt
                                │   │
                                │   └───repository
                                │           AddTaskRepository.kt
                                │           HomeRepository.kt
                                │           LoginRepository.kt
                                │           SignUpRepository.kt
                                │           SplashRepository.kt
                                │
                                ├───utils
                                │   │   HandleError.kt
                                │   │   Resource.kt
                                │   │   Utils.kt
                                │   │   Validator.kt
                                │   │   ViewUtils.kt
                                │   │
                                │   └───network
                                │           DataParser.kt
                                │           NetworkError.kt
                                │           NetworkStatus.kt
                                │
                                └───view
                                    ├───adapter
                                    │       TaskAdapter.kt
                                    │       TaskCallBack.kt
                                    │
                                    └───ui
                                        ├───auth
                                        │   │   AuthActivity.kt
                                        │   │
                                        │   ├───login
                                        │   │       LoginFragment.kt
                                        │   │       LoginViewModel.kt
                                        │   │
                                        │   └───signup
                                        │           SignUpFragment.kt
                                        │           SignUpViewModel.kt
                                        │
                                        ├───home
                                        │       HomeActivity.kt
                                        │       HomeFragment.kt
                                        │       HomeViewModel.kt
                                        │
                                        ├───profile
                                        │       ProfileFragment.kt
                                        │       ProfileViewModel.kt
                                        │
                                        ├───splash
                                        │       SplashActivity.kt
                                        │       SplashViewModel.kt
                                        │
                                        └───task
                                            ├───add
                                            │       AddTaskFragment.kt
                                            │       AddTaskViewModel.kt
                                            │
                                            └───detail
                                                    TaskDetailFragment.kt
                                                    TaskDetailViewModel.kt
```

## License
```
MIT License

Copyright (c) 2020 Simple Task-RU

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```