
[Jikan API](https://docs.api.jikan.moe/)

# Kogen - code generator

```bash
 java -jar kogen-1.0-all.jar --gen   
 ```

# Mocking network requests

Debug builds can answer API requests from JSON files instead of performing real network calls.

- Mock responses live in `libs/mock/src/debug/assets/mock/`.
- Routes are configured in `libs/mock/src/debug/assets/mock/mock_config.json`.
- `core/network` reads the config and substitutes a matching request response with the file content.
- A route can be matched by exact `path` or by `pathRegex`. `status` allows returning error responses.

```json
{
  "routes": [
    {
      "method": "GET",
      "path": "v4/top/anime",
      "asset": "mock/top/200_anime_top.json",
      "status": 200
    },
    {
      "method": "GET",
      "pathRegex": "v4/anime/.+",
      "asset": "mock/details/200_anime_details.json",
      "status": 200
    }
  ]
}
```

Mocking is enabled for debug builds and disabled for release builds by default. It can be overridden at build time:

```bash
./gradlew :app:assembleDevDebug -PmockEnabled=false
```

or in `gradle.properties`:

```properties
mockEnabled=false
```

It can also be switched at runtime:

```kotlin
MockSettings.isEnabled = false
```
