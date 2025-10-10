\# Elemental ASMR



A mobile ASMR/white noise application built with Kotlin and Jetpack Compose for Android, featuring adaptive layouts for different screen sizes and orientations.



!\[App Icon](https://img.shields.io/badge/Platform-Android-green)

!\[Language](https://img.shields.io/badge/Language-Kotlin-blue)

!\[API](https://img.shields.io/badge/MinAPI-24-orange)



\## 📱 Features



\### Core Functionality

\- \*\*Element-Based Sound Library\*\*: Browse relaxing sounds organized by 4 natural elements (Earth, Fire, Water, Wind)

\- \*\*Advanced Audio Player\*\*: Full-featured player with play/pause, skip controls, progress slider, and volume adjustment

\- \*\*Meditation Center\*\*: Timer-based meditation sessions and guided meditation library

\- \*\*User Profiles\*\*: Customizable settings including download quality, sleep timer defaults, and notifications

\- \*\*Dark/Light Mode\*\*: Automatic theme switching based on device settings



\### Sound Categories

\- 🌍 \*\*Earth\*\*: Forest rain, mountain streams, cave ambience, garden birds

\- 🔥 \*\*Fire\*\*: Crackling fireplace, bonfire, candle flames, desert heat

\- 💧 \*\*Water\*\*: Ocean waves, underwater sounds, rain on windows, waterfalls

\- 💨 \*\*Wind\*\*: Gentle breeze, mountain winds, wind chimes, storm sounds



\## 🎨 Design \& UI



\### Material Design 3 Implementation

\- Follows Android Material Design 3 guidelines

\- Adaptive color schemes for light and dark themes

\- Proper typography hierarchy using Material Type Scale

\- Elevation and shadow effects for depth

\- Smooth animations and micro-interactions



\### Responsive Layouts



\#### Portrait Mode (Phone)

\- \*\*Login/Register\*\*: Centered vertical layout with clear form hierarchy

\- \*\*Home Screen\*\*: Single-column scrollable list with expandable element cards

\- \*\*Library\*\*: Single-column list view with full sound details

\- \*\*Player\*\*: Vertical layout with artwork prominent at top

\- \*\*Meditation\*\*: Scrollable list with expandable timer card

\- \*\*Profile\*\*: Vertical scrolling sections for account info, settings, and statistics



\#### Landscape Mode (Phone)

\- \*\*Login\*\*: Split-screen with branding on left, form on right

\- \*\*Register\*\*: Welcome message (40%) and form (60%) side-by-side

\- \*\*Home Screen\*\*: Two-column layout with welcome/recent sounds on left, elements on right

\- \*\*Library\*\*: 2-column grid with compact sound cards

\- \*\*Player\*\*: Artwork and info on left, controls on right in side-by-side layout

\- \*\*Meditation\*\*: Timer card on left (40%), sessions list on right (60%)

\- \*\*Profile\*\*: Account info on left, settings on right in two columns



\#### Tablet Mode (600dp+)

\- \*\*Home Screen\*\*: 2-column grid for element cards, larger typography

\- \*\*Library\*\*: 3-column grid for optimal space utilization

\- \*\*Meditation\*\*: 2-column grid for meditation session cards

\- \*\*Profile\*\*: Sidebar layout with profile/account (40%), settings panel (60%)



\### Animations \& Micro-interactions

\- \*\*Play Button Animation\*\*: Spring-based scale animation on the player screen

\- \*\*Card Expansion\*\*: Smooth expand/collapse animations for element cards

\- \*\*Navigation Transitions\*\*: Seamless screen transitions with proper back stack handling

\- \*\*Content Animation\*\*: AnimateContentSize for dynamic UI elements



\## 🏗️ Technical Architecture



\### Technology Stack

\- \*\*Language\*\*: Kotlin

\- \*\*UI Framework\*\*: Jetpack Compose

\- \*\*Architecture Pattern\*\*: MVVM (Model-View-ViewModel)

\- \*\*Navigation\*\*: Jetpack Navigation Compose

\- \*\*Design System\*\*: Material Design 3

\- \*\*State Management\*\*: Compose State and remember

\- \*\*Theme\*\*: Custom theme with dynamic color support (Android 12+)



\### Project Structure

```

app/src/main/java/com/example/elementalasmr/

├── MainActivity.kt                 # Entry point with bottom navigation

├── models/

│   ├── Element.kt                  # Data models for sounds and elements

│   ├── Sound.kt

│   ├── MeditationSession.kt

│   └── SampleData.kt               # Static sample data

├── navigation/

│   ├── Screen.kt                   # Navigation routes

│   ├── BottomNavItem.kt            # Bottom nav configuration

│   └── NavGraph.kt                 # Navigation graph setup

├── screens/

│   ├── LoginScreen.kt              # Responsive auth screens

│   ├── RegisterScreen.kt

│   ├── HomeScreen.kt               # Responsive home with element cards

│   ├── LibraryScreen.kt            # Responsive sound library

│   ├── PlayerScreen.kt             # Responsive audio player

│   ├── MeditationScreen.kt         # Responsive meditation center

│   └── ProfileScreen.kt            # Responsive profile \& settings

└── ui/theme/

&nbsp;   ├── Color.kt                    # Color definitions for themes

&nbsp;   ├── Theme.kt                    # Theme configuration

&nbsp;   └── Type.kt                     # Typography definitions

```



\### Key Components



\#### Navigation System

\- Bottom navigation bar with 4 main sections (Home, Library, Meditation, Profile)

\- Type-safe navigation using sealed classes

\- Automatic navigation bar hiding on full-screen views (Login, Player)

\- Deep linking support for direct sound playback



\#### Theme System

\- Automatic dark/light mode detection from device settings

\- Material Design 3 color system with primary, secondary, tertiary colors

\- Element-specific color palettes for visual distinction

\- Dynamic color support for Android 12+ devices



\#### Responsive Design Implementation

Uses `LocalConfiguration.current` to detect:

\- Screen orientation (`Configuration.ORIENTATION\_LANDSCAPE`)

\- Screen width (`screenWidthDp >= 600` for tablet detection)

\- Adaptive layouts with different composables for each configuration





\### Supported Configurations

\- \*\*Minimum SDK\*\*: API 24 (Android 7.0)

\- \*\*Target SDK\*\*: API 34 (Android 14)

\- \*\*Tested Orientations\*\*: Portrait, Landscape

\- \*\*Tested Screen Sizes\*\*: Phone (< 600dp), Tablet (600dp+)





\## 🚀 Installation \& Setup



\### Prerequisites

\- Android Studio Hedgehog (2023.1.1) or later

\- JDK 8 or higher

\- Android SDK with API 24+ installed

\- Git (for version control)






\## 👨‍💻 Author



Dhanushka Sachintha 

\- Student CB number: CB015730

\- Course: COMP50011 - Mobile App Development I

\- Institution: Staffordshire University



\## 🙏 Acknowledgments



\- Material Design 3 Guidelines by Google

\- Jetpack Compose Documentation

\- Android Developer Documentation

\- Course lecturer and teaching assistants



\## 📞 Contact



For questions or issues related to this project:

\- GitHub Issues: \[Repository Issues Page]

\- Email: dhanushkasachintha@gmail.com 








