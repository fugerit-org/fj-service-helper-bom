# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Refer to subproject's changelog for details : 

- [emp-service-model](https://github.com/fugerit-org/fj-service-helper-bom/blob/main/emp-service-model/CHANGELOG.md)
- [data-service-base](https://github.com/fugerit-org/fj-service-helper-bom/blob/main/data-service-base/CHANGELOG.md)
- [data-service-jvfs](https://github.com/fugerit-org/fj-service-helper-bom/blob/main/data-service-jvfs/CHANGELOG.md)

## [Unreleased]

## [0.3.3 - 2024-03-24]

### Added

- [emp-service-model] native support : relfect-config.json

## [0.3.2 - 2024-03-24]

### Added

- ServiceResponseHelper utility to handle messages

### Changed

- fj-bom set to 1.6.3
- test moved from junit4 to junit5
- quarkus version for demo app set to 3.8.3
- fj-core version set to 8.5.3

## [0.3.1 - 2024-02-01]

### Added

- module quarkus-redis-service-map

### Changed

- minimum java build version : 17
- quarkus version for demo app set to 3.7.1
- fj-core version set to 8.4.7

## [0.3.0 - 2024-02-01]

### Added

- ServiceMap

## [0.2.3 - 2023-11-12]

### Added

- module data-service-jvfs

## [0.2.2 - 2023-11-12]

### Changed

- fj-core version set to 8.4.5

### Fixed

- dependency management

## [0.2.0 - 2023-11-12]

### Added 

- sonar cloud quality gate and coverage badge
- module data-service-base

### Changed

- fj-bom updated to 1.5.0 

## [0.1.0 - 2023-11-12]

### Added 

- module emp-service-model
- [Maven build and sonar cloud scan](.github/workflows/build_maven_package.yml)
- [Maven release](.github/workflows/deploy_maven_package.yml)