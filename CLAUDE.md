# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

MemberApp is an Android application for member and order management with Supabase backend integration. It provides member lookup and order processing with payment recommendation features.

## Development Commands

### Building and Testing
```bash
# Build the project
./gradlew build

# Run unit tests
./gradlew test

# Run Android instrumentation tests
./gradlew connectedAndroidTest

# Clean build
./gradlew clean
```

### Installation and Debugging
```bash
# Install debug APK
./gradlew installDebug

# Install and run on connected device
./gradlew installDebug && adb shell am start -n com.mcandle.member/.MainActivity
```

## Architecture Overview

### Core Components
- **MainActivity**: Entry point with navigation to Member/Order features
- **Member Module**: Member list viewing and detail dialogs
- **Order Module**: Order processing with payment recommendations
- **Supabase Integration**: Database operations via SupabaseClientProvider

### Data Flow
1. Activities use lifecycleScope for async operations
2. Supabase client provides database access via Postgrest
3. RecyclerView adapters handle list displays
4. Dialogs show detailed information and confirmations

## Key Technologies
- **Language**: Kotlin with ViewBinding
- **Database**: Supabase (PostgreSQL) with PostgREST
- **UI**: Material Design components with RecyclerView
- **Async**: Kotlin Coroutines with lifecycleScope
- **Architecture**: Activity-based with dialog fragments

## Configuration Requirements
- `local.properties` file must contain:
  - `SUPABASE_URL`: Your Supabase project URL
  - `SUPABASE_API_KEY`: Your Supabase anon/public API key
- These are automatically loaded into BuildConfig fields

## Database Schema
- `user` table: Member information (name, grade, phone, membership details)
- Order-related tables: Order details with payment recommendations

## Development Notes
- Uses ViewBinding for type-safe view access
- Coroutines with proper exception handling in try/catch blocks
- Material Design theming with custom dialog backgrounds
- Target SDK 34, minimum SDK 26, Java 17 compatibility