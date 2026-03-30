# Kalasha Ala Keyboard

A custom Android keyboard for the **Kalasha language** (کلښہ الا) — an endangered Dardic language spoken by the Kalasha people of the Nuristan Province in Afghanistan.

## About

The Kalasha language has very limited digital support. This keyboard fills that gap by providing a dedicated input method for Kalasha script on Android, enabling speakers and learners to type in their native language across any Android app.

## Features

- Full Kalasha Ala script character input
- 43 keys across 5 rows with Kalasha-specific letters and numerals
- Shift layer for accessing alternate characters, diacritics, and symbols
- Auto-shift off after each keystroke (standard keyboard behavior)
- Eastern Arabic numerals (۰–۹) with symbol shift layer
- Local processing only — no keystrokes ever leave your device

## Keyboard Layout

```
┌────────────────────────────────────────────┐
│  ۱   ۲   ۳   ۴   ۵   ۶   ۷   ۸   ۹   ۰  │
│  ض   ص   ث   ق   ف   غ   ع   ه   خ   ح   ج   چ  │
│  ش   س   ی   ب   ل   ا   ت   ن   م   ک   گ  │
│  ظ   ط   ز   ر   ذ   د   ړ   و   ږ       │
│  Shift      فاصله (Space)     حذف   ↵    │
└────────────────────────────────────────────┘
```

### Shift Layer (selected mappings)

| Normal | Shifted | | Normal | Shifted |
|--------|---------|---|--------|---------|
| ع | ۊ | | ا | آ |
| ه | ۉ | | ب | پ |
| خ | ۇ | | ن | ڼ |
| ح | ۂ | | ش | ښ |
| ج | ڇ | | ی | ي |
| چ | څ | | م | ݩ |
| ر | ڙ | | ز | ژ |
| د | ۋ | | و | ، |

## Tech Stack

- **Language**: Java
- **Platform**: Android (API 26+)
- **Framework**: Android `InputMethodService`
- **Build**: Gradle with Kotlin DSL

## Requirements

- Android 8.0 (Oreo) or higher
- Android Studio (for building from source)

## Building from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/mnuristani/KalashaAlaKeyboard.git
   cd KalashaAlaKeyboard
   ```

2. Open in Android Studio and let Gradle sync.

3. Build and run on a device or emulator:
   ```bash
   ./gradlew assembleDebug
   ```

## Installation

1. Install the APK on your Android device.
2. Go to **Settings → General Management → Keyboard → On-screen keyboard**.
3. Enable **Kalasha Ala Keyboard**.
4. Select it as your active keyboard when typing.

## Privacy

This keyboard does **not** collect, store, or transmit any keystroke data. All processing happens locally on your device. See [privacy_policy.md](privacy_policy.md) for full details.

## License

Free to use. Built to support the Kalasha language community and promote digital inclusion for indigenous languages.

## Developer

**Munir Nuristani** — nuristani.munir@gmail.com
