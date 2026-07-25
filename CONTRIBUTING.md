# Contributing to Yomira

Welcome to Yomira! We appreciate your help to make this reader better.

## Development Requirements
- **IDE:** Android Studio (latest stable version recommended).
- **JDK:** Java 17.
- **SDK:** Android API 34.

## Building the App
1. Clone the repository: `git clone https://github.com/heyshaquib/Yomira.git`
2. Open the project in Android Studio.
3. To build a debug variant, run:
   ```bash
   ./gradlew assembleDebug
   ```
4. **Release Builds:** Release builds require a secure `keystore.properties` file located in the project root. Without it, the release build will fail natively.

## Workflow
1. Fork the repository and create a new branch (`feature/your-feature` or `bugfix/issue-description`).
2. Make your changes locally.
3. Ensure the project compiles successfully and no new warnings are introduced.
4. Commit your changes. Use clear, concise commit messages outlining *what* was changed and *why*.
5. Open a Pull Request targeting the `devel` or `main` branch (depending on current default).

## Code Style
- We follow standard Kotlin coding conventions.
- Keep your changes concise and avoid unnecessary formatting changes to unrelated lines of code.

## Reporting Bugs & Requesting Features
Please use the provided GitHub Issue templates to report bugs or request features. Fill out all relevant details to help us investigate efficiently.
