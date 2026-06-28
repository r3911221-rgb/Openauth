# OpenAuth Authenticator for Android

A modern, open-source two-factor authentication (2FA) application for Android.

OpenAuth Authenticator is an independent fork inspired by the original Google Authenticator Android open-source project. It generates secure one-time passwords (OTP) locally on your device using open authentication standards.

The application works completely offline after account setup and does not require an internet connection to generate verification codes.

---

## Features

### 🔐 Secure OTP Generation

- Time-based One-Time Password (TOTP) support
- HMAC-Based One-Time Password (HOTP) support
- Compatible with services using RFC 4226 and RFC 6238 standards
- Offline code generation

### 📷 Easy Account Setup

- QR code scanning support
- Manual secret key entry
- Automatic OTP parameter detection

### 🛡️ Security

- Android Keystore based key protection
- Encrypted local storage
- AES-GCM encryption support
- Optional biometric authentication
- Secure random number generation

### 🎨 Modern Android Experience

- Jetpack Compose UI
- Material 3 design
- Dark theme support
- Clean Architecture structure
- Kotlin-first development

---

# Supported Standards

| Standard | Support |
|---|---|
| TOTP | ✅ RFC 6238 |
| HOTP | ✅ RFC 4226 |
| SHA-1 | ✅ |
| SHA-256 | ✅ |
| SHA-512 | ✅ |
| QR Provisioning | ✅ |

---

# Architecture

The project follows a modular clean architecture approach.
