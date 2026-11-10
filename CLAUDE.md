Single-module Android app (`:app`), Kotlin + Jetpack Compose + Material3.

## Build & test

A change is done when both pass:

- `./gradlew :app:compileDebugKotlin`
- `./gradlew :app:testDebugUnitTest`

Add JUnit tests in `app/src/test/` for pure logic you add or change (cart list transforms, price math). Keep that logic in plain functions, separate from DataStore and Compose, so it runs as a local unit test.

Versions live in `gradle/libs.versions.toml`. Pick library versions compiled for the project's Kotlin version: DataStore `1.3.0` (Kotlin 2.2 metadata) broke the build on Kotlin 2.0, hence the pin to `1.1.7`. Compile after every version change.

## Architecture

- **Screens** (`screens/<feature>/`) are stateless about navigation and storage: they take data plus callbacks (`onProductClick`, `onAddToCart`, `onBottomNavClick`). Shared UI lives in `screens/ui_components/`.
- **Navigation** lives only in `navigation/AppNavGraph.kt`: routes in `Routes`, `NavController` calls, and wiring screens to storage. Declare a route in the `NavHost` before navigating to it; an undeclared route crashes.
- **State and coroutines** currently live in `AppNavGraph` (`rememberCoroutineScope`, `collectAsState`). There is no ViewModel or DI yet; confirm with the user before introducing either.
- **Persistence**: `local_storage/` wraps Preferences DataStore. `LocalStorageService` declares `package com.example.mycoffeeapp.data.local` despite its folder; import it from that package. Use `updateString` for read-modify-write so concurrent taps stay atomic.
- **Persisted models are Gson JSON** (`CartStorage`). Gson writes `null` into non-null Kotlin fields, so a field added to a stored model arrives as `null` in old data; filter or default it when parsing.
- **`Product.imageUrl` is an `R.drawable` id**, which changes between builds. Re-resolve stored products from `MockData` by `id` before display, as the Cart route does.

## Agent skills

### Issue tracker

Issues are tracked in this repo's GitHub Issues (namdq061297/MyCoffeeApp) using the `gh` CLI. See `docs/agents/issue-tracker.md`.

### Triage labels

Uses the five default labels: `needs-triage`, `needs-info`, `ready-for-agent`, `ready-for-human`, `wontfix`. See `docs/agents/triage-labels.md`.

### Domain docs

Single-context: one `GLOSSARY.md` and `docs/adr/` at the repo root. See `docs/agents/domain.md`.
