# Changelog

### [1.2.1] - 2026/04/25

JAL Implementation: 1.2.2

This release includes new features and improvements.

- **feat**: Partially support classes/methods navigation in the editor.
- fix: Correct rule index for lookupswitch instruction.
- fix: Calculation logic of instruction indexes.

### [1.2.0] - 2026/04/24

JAL Implementation: 1.2.2

This release includes new features and improvements.

- **Now supports IntelliJ IDEA 2026.1+**
- **feat**: Allow instructions at the first label of a method.
- feat: Reanalyse files on save.
- chore: Update JVM instruction documentation to Java 26.
- fix: Handle exception during file loading.

### [1.1.1] - 2026/04/21

JAL Implementation: 1.2.1

This release includes a minor bug fix to improve the stability of the application.

- fix: Fixed a bug related to the legacy configurable id calculation mode for the JALCodeStyleSettingsProvider class, which caused a PluginException in IntelliJ IDEA.

## [1.1.0] - 2025/08/26

JAL Implementation: 1.2.1

This release introduces several new features and improvements to enhance the user experience.

- feat: Instruction names are now sorted by category in the code completion list.
- chore: Changing stack viewer icon to a more suitable one.
- fix: Fixed a bug on cumulative offset calculation when a label is declared at the first line of a method.
- fix: Fixed a bug on cumulative offset calculation when the default label of tableswitch instruction is not considered as a target label.

## [1.0.0] - 2025/07/23

JAL Implementation: 1.0.0

The first stable release.

- refactor: Divided JAL modules from this big repository.
- feat: Code folding feature on editor.
- feat: Class-name omitting when each JAL file declares a valid class structure.
- feat: Braces paring for better visualization.
- fix: Broken label name renaming.


## [0.0.1] - 2025/07/19

JAL Implementation: 0.0.1

This is the first release of this project. Yay!

## [バージョン名] - yyyy/MM/dd

JAL Implementation: M.m.p

### Added

### Fixed

### etc...
