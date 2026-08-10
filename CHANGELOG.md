# Changelog

All notable changes to the Genetic Timetable Generator project will be documented in this file.

## 15  "add MaxDailyLectureConstraint and optimize constraint grid evaluation"
- Refactor Constraint interface to accept pre-built Placement[][] grid
- Build grid matrix once per evaluation in FitnessEvaluator to avoid redundant O(N) calls
- Implement MaxDailyLectureConstraint using Map<Subject, Integer> daily frequency counting
- Add equals() and hashCode() to Subject for safe HashMap key operations


## 14 "REFACTOR : Constraint Interface"
- created Constraint interface for all constraints

------------- Iteration 1 Completed -----------------

## 13 "Dependency injection for Genetic Algorithm"
- add dependency injection for GeneticAlgorithm class
- Refactor Main to instantiate and inject dependencies into GeneticAlgorithm

## 12 "implement GeneticAlgorithm loop and add bounds checking to SwapMutation"

- Create GeneticAlgorithm class orchestrating selection, crossover, repair, mutation, and evaluation
- Add legal slot bounds checking to SwapMutation to prevent grid index overflow
- Add GA execution call and formatted section headers to Main

## 11 " add SwapMutation operator and enhance TimetablePrinter"
 Add swapSlot method to Placement model
- Implement SwapMutation class for genetic algorithm mutation step
- Update TimetablePrinter to accept Chromosome and display Subject Name
- Update Main to test and display timetable mutation

## 10 "add OnePointCrossover & repair operator"
- Add OnePointCrossover class to combine parent chromosome placements
- Add RepairOperator class to resolve placement overlaps and relocate invalid slots. FLAW : O(n²)

## 9 "add deep copy constructors for Placement and Chromosome"

- Implement copy constructor and copy() method for Placement
- Implement deep copy constructor and copy() method for Chromosome
- Add deep copy verification test and section comments in Main
- Add initial OnePointCrossover class setup

## 8. "Tournament Selection"
## Added
- TournamentSelection.java: Implemented tournament selection algorithm to sample candidate chromosomes and select the best candidate (lowest penalty score).

## Changed
Main.java Execution Pipeline:
- Added FitnessEvaluator loop to calculate penalties for every chromosome in the population.
- Added console output listing individual fitness scores for all 10 generated chromosomes.
Integrated TournamentSelection(3) and printed the selected parent's fitness score.


## 7. Configuration Centralization, GA Setup & Fitness Evaluator
### Added
- **`SchedulingConfig`**: Centralized `WORKING_DAYS` (5) and `PERIODS_PER_DAY` (6) constants.
- **`ga` Package**: Created `Chromosome`, `Population`, `PopulationGenerator`, and initial `FitnessEvaluator` (consecutive lecture penalty).
- **`TimetableGridBuilder`**: Utility to project a `Chromosome` into a 2D `Placement[][]` matrix.
### Changed
- **Config Refactoring**: Replaced all magic numbers (`5` and `6`) across `RandomTimetableGenerator`, `TimetablePrinter`, `TimetableGridBuilder`, and `FitnessEvaluator` with `SchedulingConfig` constants.
- **Generator**: `RandomTimetableGenerator` now constructs and returns `Chromosome` objects directly.

## 6. "Created Chromosome, Population, and PopulationGenerator"
- Added ga Package: Created Chromosome, Population, and PopulationGenerator classes for Genetic Algorithm initialization.
- Updated RandomTimetableGenerator: Refactored the generator to build and return Chromosome objects directly (addGene()) instead of Timetable.
- Refactored Chromosome: Renamed internal field genes to placements while keeping addGene() and getGenes() interface.

## 5. proper scheduling engine
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

## 4 "generated a random timetable, separated lecture/lab"
-  Created `RandomTimetableGenerator` scaffolding for initial placement logic.
- Added custom session ID generation (e.g., `JAVA-1`, `DSA-1`) in `SessionFactory`.
- Refactored `Session` ID type from `int` to `String`.
- Updated `Placement` model to utilize `TimeSlot` directly instead of raw `day` and `period` integers.
- Updated `TimeSlot` formatting to output 1-indexed periods (`P1` to `P6`).
- DISADV - Placement[][] is a view of the timetable.


## 3 "Refactor Session IDs to string format and add Placement TimeSlot integration"
- Placement & TimeSlot:
Refactored Placement to use a TimeSlot object instead of separate day and period integers.
Updated TimeSlot.toString() to display 1-indexed periods 

### 2 Added
- Created `RandomTimetableGenerator` scaffolding for initial placement logic.
- Added custom session ID generation (e.g., `JAVA-1`, `DSA-1`) in `SessionFactory`.

- Refactored `Session` ID type from `int` to `String`.
- Updated `Placement` model to utilize `TimeSlot` directly instead of raw `day` and `period` integers.
- Updated `TimeSlot` formatting to output 1-indexed periods (`P1` to `P6`).

## 1 [0.1.0] - 2026-08-01
### Added
- Initial project setup with core domain models (`Subject`, `Session`, `TimeSlot`, `Timetable`, `Placement`).
