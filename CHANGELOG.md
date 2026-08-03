# Changelog

All notable changes to the Genetic Timetable Generator project will be documented in this file.

## 6. proper scheduling engine
Subjects
      │
      ▼
Sessions
      │
      ▼
Random Timetable Generator
      │
      ▼
Placements (Session → TimeSlot)
      │
      ▼
Timetable (List<Placement>)
      │
      ▼
Printer (5×6 Grid)

## 5 "generated a random timetable, separated lecture/lab"
-  Created `RandomTimetableGenerator` scaffolding for initial placement logic.
- Added custom session ID generation (e.g., `JAVA-1`, `DSA-1`) in `SessionFactory`.
- Refactored `Session` ID type from `int` to `String`.
- Updated `Placement` model to utilize `TimeSlot` directly instead of raw `day` and `period` integers.
- Updated `TimeSlot` formatting to output 1-indexed periods (`P1` to `P6`).
- DISADV - Placement[][] is a view of the timetable.


## 4 "Refactor Session IDs to string format and add Placement TimeSlot integration"
- Placement & TimeSlot:
Refactored Placement to use a TimeSlot object instead of separate day and period integers.
Updated TimeSlot.toString() to display 1-indexed periods 

### 3 Added
- Created `RandomTimetableGenerator` scaffolding for initial placement logic.
- Added custom session ID generation (e.g., `JAVA-1`, `DSA-1`) in `SessionFactory`.

### 2 Changed
- Refactored `Session` ID type from `int` to `String`.
- Updated `Placement` model to utilize `TimeSlot` directly instead of raw `day` and `period` integers.
- Updated `TimeSlot` formatting to output 1-indexed periods (`P1` to `P6`).

## 1 [0.1.0] - 2026-08-01
### Added
- Initial project setup with core domain models (`Subject`, `Session`, `TimeSlot`, `Timetable`, `Placement`).
