
# Scan Ease [Scan,Save,Simplify]

Scan Ease is your ultimate document scanning companion, transforming physical papers into crisp digital files in seconds. With its range of built-in tools, Scan Ease ensures top-notch image quality, making document scanning a breeze. Automatically detect and scan documents with precision using auto-capture, enhance scan quality with customizable filters, and remove noise and correct orientation with the advanced document cleaner.


## Screenshots
![Unefwtitlfewed](https://github.com/user-attachments/assets/85d4c531-ea15-49af-a348-caf2b60dc429)
![Untitlrgeed(1)](https://github.com/user-attachments/assets/9793f71f-808b-47a0-a719-e1dd9e1ce109)
![Untitled(2)](https://github.com/user-attachments/assets/80f676a5-845d-444d-97e1-c4138942cafb)




## Tech Stack

**-Kotlin** 

**-Jetpack Compose**

**-Gradle** 

**-MVVM**
## Features

- Light/dark mode toggle.
- Scribble Remover.
- Auto Doc Scanning.
- AI enabled noise reduction.
- Docs Management.
- In built Filters.


## UI/UX
The app was designed using [Material 3 guidelines](https://m3.material.io/) .
Learn more about the design process and obtain the design files in the 
[Now in Android Material 3 Case Study](https://www.figma.com/community/file/1164313362327941158/now-in-android-case-study).

The Screens and UI elements are built entirely using [Jetpack Compose](https://developer.android.com/compose).

The app has two themes:

Dynamic color - uses colors based on the [user's current color](https://m3.material.io/blog/announcing-material-you/) theme (if supported)

Default theme - uses predefined colors when dynamic color is not supported
Each theme also supports dark mode.

The app uses adaptive layouts to [support different screen sizes](https://developer.android.com/develop/ui/compose/layouts/adaptive/support-different-screen-sizes).

Find out more about the [UI architecture here](https://github.com/android/nowinandroid/blob/main/docs/ArchitectureLearningJourney.md#ui-layer).
## Baseline Profiles
The baseline profile for this app is located at app/src/main/baseline-prof.txt. It contains rules that enable AOT compilation of the critical user path taken during app launch. For more information on baseline profiles, read this [document](https://developer.android.com/topic/performance/baselineprofiles/overview).

**Note:**
The baseline profile needs to be re-generated for release builds that touch code which changes app startup.

To generate the baseline profile, select the benchmark build variant and run the BaselineProfileGenerator benchmark test on an AOSP Android Emulator. Then copy the resulting baseline profile from the emulator to ```app/src/main/baseline-prof.txt```.
## License

Now in Android is distributed under the terms of the MIT License (Version 2.0). See the license for more information. [MIT](https://choosealicense.com/licenses/mit/)


