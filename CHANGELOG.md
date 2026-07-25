# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-07-25

### Added
- Complete rebrand from DropSauce to Yomira.
- Repository identity migration to `heyshaquib/Yomira`.
- Android identity migration to `com.yomira.reader` (production) and `com.yomira.reader.preview` (preview).
- Secure release signing configuration isolated from Git via `keystore.properties`.
- GitHub community and documentation files (Contributing, Security, Code of Conduct, Issue Forms).

### Fixed
- Completed a runtime safety verification audit ensuring production resilience.
- Eradicated all remaining legacy hardcoded references from the prior DropSauce sandbox.
