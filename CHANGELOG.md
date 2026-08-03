# Changelog

All notable changes to the Genetic Timetable Generator project will be documented in this file.

## 7. "Created Chromosome, Population, and PopulationGenerator"
- Added ga Package: Created Chromosome, Population, and PopulationGenerator classes for Genetic Algorithm initialization.
- Updated RandomTimetableGenerator: Refactored the generator to build and return Chromosome objects directly (addGene()) instead of Timetable.
- Refactored Chromosome: Renamed internal field genes to placements while keeping addGene() and getGenes() interface.

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

### Added
- Implemented core placement algorithms (`separateSessions`, `placeLabs`, `placeLectures`) in `RandomTimetableGenerator`.
- Added collision detection logic (`canPlaceLab` & `occupied`) to prevent overlapping slots.
### Changed
- **`Timetable`**: Refactored internal representation from a fixed 2D array (`Placement[][]`) to a dynamic `List<Placement>`.
- **`Placement`**: Updated `slot` field to be mutable with `getSlot()` and `setSlot()`.
- **`TimetablePrinter`**: Reconstructed 5×6 grid printing to render from `List<Placement>`.

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
