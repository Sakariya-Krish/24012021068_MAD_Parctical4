# ⏰ MAD Practical-4 — Android Alarm Application

This repository contains the implementation of **Practical-4** for the **Mobile Application Development (MAD)** subject.

The practical demonstrates how to create an Android Alarm Application using **AlarmManager, PendingIntent, BroadcastReceiver, Service, MediaPlayer, TimePickerDialog, Calendar, and TextClock**.

The application allows the user to select an alarm time, schedule the alarm, play an alarm sound, and stop the alarm.

---

## 📌 Practical Information

| Information    | Details                              |
| -------------- | ------------------------------------ |
| **Subject**    | Mobile Application Development (MAD) |
| **Practical**  | Practical-4                          |
| **Language**   | Kotlin                               |
| **UI**         | XML                                  |
| **IDE**        | Android Studio                       |
| **Platform**   | Android                              |
| **Repository** | 24012021068_MAD_Parctical4           |

---

# 🎯 Aim

To create an **Android Alarm Application** using **Service** and **BroadcastReceiver**.

The application allows the user to:

* Select an alarm time
* Schedule an alarm
* Start the alarm
* Play an alarm sound
* Stop the alarm

`AlarmManager` is used to schedule the alarm, `PendingIntent` triggers the `BroadcastReceiver`, and the `Service` uses `MediaPlayer` to play the alarm sound.

---

# 🎯 Objectives

The objectives of this practical are:

* Create an Android Alarm Application.
* Use `AlarmManager` to schedule an alarm.
* Use `PendingIntent` to trigger an alarm event.
* Implement `BroadcastReceiver`.
* Implement an Android `Service`.
* Use `MediaPlayer` to play an alarm sound.
* Use `TimePickerDialog` to select an alarm time.
* Use `Calendar` to calculate the alarm time.
* Use `TextClock` to display the current time.
* Use `SimpleDateFormat` for date and time formatting.
* Use `startService()` and `stopService()`.
* Use `sendBroadcast()`.
* Pass data using `Intent.putStringExtra()`.
* Retrieve data using `Intent.getStringExtra()`.
* Use `MaterialCardView`.
* Configure exact alarm permissions.

---

# 🛠️ Technologies Used

* **Android Studio**
* **Kotlin**
* **XML**
* **Android SDK**
* **AlarmManager**
* **PendingIntent**
* **BroadcastReceiver**
* **Service**
* **MediaPlayer**
* **TimePickerDialog**
* **TextClock**
* **MaterialCardView**
* **Git**
* **GitHub**

---

# ⏰ Application Features

The application provides the following features:

* 🕐 Current time display using `TextClock`
* 🕰️ Alarm time selection using `TimePickerDialog`
* ⏱️ Alarm scheduling using `AlarmManager`
* 📦 Alarm triggering using `PendingIntent`
* 📡 Alarm event handling using `BroadcastReceiver`
* ⚙️ Background alarm handling using `Service`
* 🔊 Alarm sound using `MediaPlayer`
* ▶️ Start Alarm functionality
* ⏹️ Stop Alarm functionality
* 🎨 Card-based UI using `MaterialCardView`

---

# 🔄 Application Flow

```text
                    MainActivity
                         │
                         ↓
                Select Alarm Time
                         │
                         ↓
                 TimePickerDialog
                         │
                         ↓
                     Calendar
                         │
                         ↓
                   AlarmManager
                         │
                         ↓
                   PendingIntent
                         │
                         ↓
              AlarmBroadcastReceiver
                         │
                         ↓
                    AlarmService
                         │
                         ↓
                     MediaPlayer
                         │
                         ↓
                   🔔 Alarm Sound
```

---

# 📱 MainActivity

`MainActivity` is the main screen of the application.

It allows the user to:

1. View the current time.
2. Select an alarm time.
3. Start the alarm.
4. Stop the alarm.

The Activity uses:

* `TextClock`
* `TimePickerDialog`
* `MaterialCardView`
* Buttons
* `Calendar`
* `AlarmManager`
* `PendingIntent`

---

# 🕐 TextClock

`TextClock` is used to display the current time on the screen.

### Example

```xml
<TextClock
    android:id="@+id/textClock"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:format12Hour="hh:mm:ss a"
    android:format24Hour="HH:mm:ss" />
```

The `TextClock` automatically updates the displayed time.

---

# 🕰️ TimePickerDialog

`TimePickerDialog` allows the user to select the required alarm hour and minute.

### Example

```kotlin
val timePickerDialog = TimePickerDialog(
    this,
    { _, hourOfDay, minute ->
        // Set selected time
    },
    hour,
    minute,
    false
)

timePickerDialog.show()
```

The selected hour and minute are then used to schedule the alarm.

---

# 📅 Calendar

`Calendar` is used to create and manage the alarm date and time.

### Example

```kotlin
val calendar = Calendar.getInstance()

calendar.set(
    Calendar.HOUR_OF_DAY,
    hour
)

calendar.set(
    Calendar.MINUTE,
    minute
)

calendar.set(
    Calendar.SECOND,
    0
)
```

The calculated time is passed to `AlarmManager`.

---

# ⏱️ AlarmManager

`AlarmManager` is an Android system service used to schedule operations at a specified time.

### Example

```kotlin
val alarmManager =
    getSystemService(ALARM_SERVICE) as AlarmManager
```

The alarm is scheduled using a `PendingIntent`.

---

# 📦 PendingIntent

`PendingIntent` allows Android to execute a predefined Intent on behalf of the application at a later time.

### Example

```kotlin
val pendingIntent = PendingIntent.getBroadcast(
    this,
    0,
    intent,
    PendingIntent.FLAG_UPDATE_CURRENT or
            PendingIntent.FLAG_IMMUTABLE
)
```

The `PendingIntent` is supplied to `AlarmManager` and triggers the `AlarmBroadcastReceiver` when the scheduled time is reached.

---

# 📡 BroadcastReceiver

`BroadcastReceiver` is an Android component that receives broadcast messages from the Android system or applications.

In this practical, `AlarmBroadcastReceiver` receives the broadcast generated by `AlarmManager`.

### Example

```kotlin
class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        // Start alarm service
    }
}
```

### Alarm Flow

```text
AlarmManager
     ↓
PendingIntent
     ↓
BroadcastReceiver
     ↓
AlarmService
```

---

# ⚙️ AlarmService

`AlarmService` is responsible for performing the alarm operation.

When the `BroadcastReceiver` receives the alarm event, the Service is started.

### Example

```kotlin
startService(
    Intent(this, AlarmService::class.java)
)
```

The Service uses `MediaPlayer` to play the alarm sound.

---

# 🔊 MediaPlayer

`MediaPlayer` is used to play audio files stored in the application.

### Example

```kotlin
mediaPlayer = MediaPlayer.create(
    this,
    R.raw.alarm
)

mediaPlayer.start()
```

The alarm sound continues until the Service is stopped or the `MediaPlayer` is released.

---

# ▶️ startService()

`startService()` is used to start an Android Service.

### Example

```kotlin
startService(
    Intent(this, AlarmService::class.java)
)
```

In this practical, it is used to start the alarm service after the alarm is triggered.

---

# ⏹️ stopService()

`stopService()` is used to stop a running Service.

### Example

```kotlin
stopService(
    Intent(this, AlarmService::class.java)
)
```

It can be used to stop the alarm when the user presses the **Stop Alarm** button.

---

# 📢 sendBroadcast()

`sendBroadcast()` sends a broadcast Intent to registered BroadcastReceivers.

### Example

```kotlin
sendBroadcast(intent)
```

It allows Android components to communicate through broadcast messages.

---

# 📤 Intent.putStringExtra()

`putStringExtra()` is used to pass String data through an Intent.

### Example

```kotlin
intent.putStringExtra(
    "alarmTime",
    selectedTime
)
```

The key `"alarmTime"` identifies the data being passed.

---

# 📥 Intent.getStringExtra()

`getStringExtra()` retrieves String data from an Intent.

### Example

```kotlin
val alarmTime =
    intent.getStringExtra("alarmTime")
```

It retrieves the value previously stored using `putStringExtra()`.

---

# 🔐 Exact Alarm Permission

The application may require the following permission for exact alarm scheduling on supported Android versions:

```xml
<uses-permission
    android:name="android.permission.SCHEDULE_EXACT_ALARM" />
```

The exact permission and scheduling behavior depend on the Android version and application implementation.

---

# 🧩 MaterialCardView

`MaterialCardView` is used to create attractive card-style UI components.

### Example

```xml
<com.google.android.material.card.MaterialCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <!-- UI Components -->

</com.google.android.material.card.MaterialCardView>
```

It can be used to organize the alarm time and control buttons into a clean card-based interface.

---

# 🗓️ SimpleDateFormat

`SimpleDateFormat` is used to format date and time values into a readable String.

### Example

```kotlin
val dateFormat =
    SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

val currentTime =
    dateFormat.format(Date())
```

It can be used to display or log formatted date and time information.

---

# 🧾 AndroidManifest Configuration

The required `BroadcastReceiver` and Service are registered in `AndroidManifest.xml`.

### Example

```xml
<receiver
    android:name=".AlarmBroadcastReceiver"
    android:exported="false" />

<service
    android:name=".AlarmService"
    android:exported="false" />
```

The exact configuration may vary depending on the Android version and implementation.

---

# 📂 Project Structure

```text
24012021068_MAD_Parctical4/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── ...
│           │       ├── MainActivity.kt
│           │       ├── AlarmBroadcastReceiver.kt
│           │       └── AlarmService.kt
│           │
│           ├── res/
│           │   ├── drawable/
│           │   │
│           │   ├── layout/
│           │   │   └── activity_main.xml
│           │   │
│           │   ├── raw/
│           │   │   └── alarm.mp3
│           │   │
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

# 🧪 How the Application Works

## Step 1 — Open Application

The `MainActivity` is displayed.

## Step 2 — View Current Time

The current time is displayed automatically using `TextClock`.

## Step 3 — Select Alarm Time

The user selects the alarm time using `TimePickerDialog`.

## Step 4 — Set Alarm

The selected hour and minute are stored in a `Calendar` object.

## Step 5 — Schedule Alarm

`AlarmManager` schedules the alarm using a `PendingIntent`.

## Step 6 — Alarm Triggered

At the scheduled time:

```text
AlarmManager
      ↓
PendingIntent
      ↓
AlarmBroadcastReceiver
```

## Step 7 — Start Service

The `AlarmBroadcastReceiver` starts `AlarmService`.

```text
AlarmBroadcastReceiver
          ↓
      AlarmService
          ↓
       MediaPlayer
          ↓
      Alarm Sound 🔔
```

## Step 8 — Stop Alarm

The user can stop the alarm using the **Stop Alarm** button.

The Service stops and the `MediaPlayer` is released.

---

# 📚 Study Topics

This practical covers:

* AlarmManager
* PendingIntent
* BroadcastReceiver
* Service
* TextClock
* TimePickerDialog
* Calendar
* SimpleDateFormat
* `getSystemService()`
* `sendBroadcast()`
* MediaPlayer
* `startService()`
* `stopService()`
* `Intent.getStringExtra()`
* `Intent.putStringExtra()`
* MaterialCardView
* Exact Alarm Permission
* AndroidManifest
* Intent
* Alarm Scheduling
* Background Services

---

# ❓ Important Viva Questions

### 1. What is AlarmManager?

`AlarmManager` is an Android system service used to schedule operations at a specified time.

### 2. What is BroadcastReceiver?

`BroadcastReceiver` is an Android component that receives and handles broadcast messages.

### 3. What is a Service?

A Service is an Android component used to perform operations without providing a user interface.

### 4. What is PendingIntent?

`PendingIntent` allows Android or another component to execute an Intent on behalf of an application at a later time.

### 5. Why is Calendar used?

`Calendar` is used to calculate and store the required alarm date and time.

### 6. What is MediaPlayer?

`MediaPlayer` is used to play audio and video resources.

### 7. What is TimePickerDialog?

`TimePickerDialog` provides a dialog that allows the user to select an hour and minute.

### 8. What is TextClock?

`TextClock` is a UI component that displays the current time and updates automatically.

### 9. What is `startService()`?

`startService()` is used to start an Android Service.

### 10. What is `stopService()`?

`stopService()` is used to stop a running Android Service.

### 11. What is `putStringExtra()`?

`putStringExtra()` is used to attach String data to an Intent.

### 12. What is `getStringExtra()`?

`getStringExtra()` retrieves String data that was previously added to an Intent.

---

# ▶️ How to Run

1. Clone the repository.
2. Open the project in **Android Studio**.
3. Allow Gradle synchronization to complete.
4. Connect an Android device or start an emulator.
5. Click **Run ▶**.
6. Select an alarm time.
7. Start the alarm.
8. Wait until the scheduled time.
9. `AlarmBroadcastReceiver` receives the alarm event.
10. `AlarmService` starts and plays the alarm sound.
11. Stop the alarm using the **Stop Alarm** button.

---

# 🎓 Learning Outcomes

After completing this practical, the following concepts are understood:

* Alarm scheduling
* AlarmManager
* PendingIntent
* BroadcastReceiver
* Android Service
* MediaPlayer
* TimePickerDialog
* TextClock
* Calendar
* Date and time formatting
* Intent communication
* AndroidManifest configuration
* Exact alarm permissions
* Background operations
* Material UI components

---

# 📌 Conclusion

MAD Practical-4 provides practical knowledge of **AlarmManager, BroadcastReceiver, and Service** in Android.

The application demonstrates how an alarm can be scheduled for a specific time, how Android triggers a `BroadcastReceiver`, and how a `Service` can play an alarm sound using `MediaPlayer`.

This practical helps understand **Android background components, system services, Intent communication, and alarm scheduling**.

---

## 👨‍💻 Author

**Krish Sakariya**

**Course:** B.Tech Information Technology
**Subject:** Mobile Application Development (MAD)
