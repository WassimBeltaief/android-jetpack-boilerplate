<h1 align="center">Android Jetpack Boilerplate</h1>

<p align="center">
  A production-ready Android template — Clean Architecture, Jetpack Compose, Hilt, Room, and Retrofit wired up so you can ship instead of scaffold.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-2.x-7F52FF?style=flat-square&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/minSdk-24-brightgreen?style=flat-square" />
  <img src="https://img.shields.io/badge/targetSdk-35-brightgreen?style=flat-square" />
  <img src="https://img.shields.io/badge/Architecture-Clean%20%2B%20MVVM-blue?style=flat-square" />
  <img src="https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square" />
</p>

---

## Why this exists

Setting up a new Android project means wiring the same 10 libraries together every time. This boilerplate does that once — correctly — so the first commit you make is feature code, not plumbing.

---

## Stack

| Layer | Library |
|---|---|
| UI | [Jetpack Compose](https://developer.android.com/jetpack/compose) |
| Navigation | [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) |
| DI | [Hilt](https://dagger.dev/hilt/) |
| Async | Kotlin Coroutines + Flow |
| Networking | [Retrofit](https://square.github.io/retrofit/) + OkHttp |
| Local DB | [Room](https://developer.android.com/training/data-storage/room) |
| Serialization | [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) |
| Testing | JUnit 5 · MockK · Turbine |

---

## Architecture

Clean Architecture with MVVM — three layers, one direction of dependency.

```
app/
├── data/           # Repositories, Room DAOs, Retrofit services, mappers
├── domain/         # UseCases, domain models, repository interfaces
└── presentation/   # ViewModels, Compose screens, UI state
```

```
Compose Screen
     ↓
 ViewModel          ← UI state (StateFlow)
     ↓
  UseCase           ← business logic only
     ↓
 Repository         ← decides: cache or network
   /     \
Room    Retrofit
```

Data flows **up** as `Flow`/`StateFlow`. The domain layer has zero Android imports.

---

## Features

- **Unidirectional data flow** — `UiState` sealed class per screen, no shared mutable state
- **Offline-first** — Room caches responses; UI works without a network connection
- **Hilt everywhere** — ViewModel, WorkManager, Navigation, and test components pre-wired
- **Type-safe navigation** — no string routes; destinations are sealed objects
- **Coroutine-aware Room** — suspend queries and `Flow` streams out of the box
- **Baseline OkHttp config** — logging interceptor, timeout, and auth header hook
- **Unit tests scaffolded** — UseCase and ViewModel tests with MockK + Turbine examples

---

## Getting started

```bash
# 1. Clone
git clone https://github.com/YOUR_USERNAME/android-jetpack-boilerplate.git

# 2. Open in Android Studio Hedgehog or newer

# 3. Set your base URL
#    app/src/main/java/.../data/network/NetworkModule.kt
const val BASE_URL = "https://api.yourservice.com/"

# 4. Run
./gradlew assembleDebug
```

Minimum requirements: **Android Studio Hedgehog (2023.1.1+)**, JDK 17.

---

## Project structure

```
app/src/main/
├── data/
│   ├── local/          # Room database, DAOs, entities
│   ├── remote/         # Retrofit services, DTOs
│   └── repository/     # Repository implementations + mappers
├── domain/
│   ├── model/          # Pure Kotlin domain models
│   ├── repository/     # Repository interfaces
│   └── usecase/        # One class per use case
└── presentation/
    ├── navigation/     # NavGraph + destinations
    ├── theme/          # MaterialTheme, typography, colors
    └── feature/
        └── <feature>/  # Screen.kt + ViewModel.kt + UiState.kt
```

---

## Running tests

```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

---

## Roadmap

- [ ] Pagination with Paging 3
- [ ] WorkManager background sync
- [ ] Baseline Profiles
- [ ] Screenshot testing with Paparazzi
- [ ] CI with GitHub Actions

---

## Contributing

1. Fork the repo
2. Create a branch: `git checkout -b feat/your-feature`
3. Commit your changes: `git commit -m 'feat: add your feature'`
4. Push and open a PR

---

## License

```
MIT License — Copyright (c) Wassim Beltaief
```

See [LICENSE](LICENSE) for details.
