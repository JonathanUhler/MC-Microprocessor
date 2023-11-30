# MC8-Mode
A major mode for Emacs that provides very basic syntax highlighting and indentation for mc8
source files.


# Installation
The major mode is a single file, `src/main/mc8-mode.el`. This file can be copied to some place
on the disk and referenced in `init.el` with:
```lisp
(load-file "/path/to/mc8-mode.el")
```
Files ending with `.mc8` will automatically start in MC8 mode.


# Usage
Like any major mode, MC8 mode can be entered from any buffer with `M-x mc8-mode`. Once in MC8 mode,
the tokens in the buffer will be highlighted and indentation rules will take effect when pressing
the tab key. \
\
All keywords, constants, and datatypes in MC8 are highlighted to provide some increased
readability.
