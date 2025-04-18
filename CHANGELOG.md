# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## [2025.04.17]

### Added

- Designed kernel implementation for FitnessTracker component

### Updated

- Changed design to include implementations of Standard and Kernel methods using the Java version of Queue and ArrayDeque

## [2025.04.17]

### Added

- Designed abstract class for FitnessTracker component

### Updated

- Changed design to swapped out removeLast kernel method to removeFirst. It makes more sense to implement the abstract class using FIFO since it's easier. I eventually also want to use a Java component (Queue) to implement my methods and using removeFirst is more similar to dequeue. Because of this change, I swapped out the findFirst() enhanced method for a more complex getConsistencyScore() method that finds the standard deviation and therefore variability of the user-entered weights. I wanted to add more data analytics.

## [2025.04.16]

### Added

- Designed kernel and enhanced interfaces for FitnessTracker component

### Updated

- Changed design to include kernel methods addWeight, length, removeLast and enhanced methods findPR, getCurrentProgress, and findFirst.

## [2025.04.15]

### Added

- Designed a proof of concept for FitnessTracker component

### Updated

- Changed design to include wrote instance methods for findPR(), getCurrentProgress(), and findFirst(). Created a main method to show how a user would use these methods. Used the kernel methods to implement the instance methods.

## [2025.02.04]

### Added

- Designed a Fitness Progress Tracker component
- Designed a To-Do List component
- Designed an Application Tracker component