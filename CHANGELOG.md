# Changelog

All notable changes to the Genetic Timetable Generator project will be documented in this file.

## "Refactor Session IDs to string format and add Placement TimeSlot integration"
- Placement & TimeSlot:
Refactored Placement to use a TimeSlot object instead of separate day and period integers.
Updated TimeSlot.toString() to display 1-indexed periods 

### Added
- Created `RandomTimetableGenerator` scaffolding for initial placement logic.
- Added custom session ID generation (e.g., `JAVA-1`, `DSA-1`) in `SessionFactory`.

### Changed
- Refactored `Session` ID type from `int` to `String`.
- Updated `Placement` model to utilize `TimeSlot` directly instead of raw `day` and `period` integers.
- Updated `TimeSlot` formatting to output 1-indexed periods (`P1` to `P6`).

## [0.1.0] - 2026-08-01
### Added
- Initial project setup with core domain models (`Subject`, `Session`, `TimeSlot`, `Timetable`, `Placement`).
