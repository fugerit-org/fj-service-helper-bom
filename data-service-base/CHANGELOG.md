# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- method save(InputStream,String) to DataService

## [0.3.0 - 2024-02-01]

### Added

- ServiceMap (basically a key/value map interface)

## [0.2.5 - 2023-11-12]

### Added

- DataServiceWrapper

## [0.2.4 - 2023-11-12]

### Added

- generation id : ${yyyyMMddHHmmss}_${UUID}

## [0.2.1 - 2023-11-12]

### Fixed 

- FileDataServiceUUID now correctly handles not existing ID.

## [0.2.0 - 2023-11-12]

### Added 

- DataService, DataServiceIO utilities and basic test implementation FileDataServiceUUID
