;;; mc8-mode.el --- A major mode for writing MC8 files.

;; Author: Jonathan Uhler

;;; Code:

;; Define syntactically important tokens based on their category. These are
;; used to build regular expressions with `regexp-opt', which are then used
;; to define font lock defaults.
(defvar mc8-mode-keyword-list  `("func" "if" "elif" "else" "for" "while" "continue" "break"))
(defvar mc8-mode-type-list     `("uint8" "bool"))
(defvar mc8-mode-constant-list `("false" "true"))
;; Regular expressions from the tokens above
(defvar mc8-mode-keyword-regex (regexp-opt mc8-mode-keyword-list))
(defvar mc8-mode-type-regex (regexp-opt mc8-mode-type-list))
(defvar mc8-mode-constant-regex (regexp-opt mc8-mode-constant-list))
;; Font locks from the regular expressions
(defvar mc8-mode-font-lock-defaults
  `((
	(,mc8-mode-keyword-regex . font-lock-keyword-face)
	(,mc8-mode-type-regex . font-lock-type-face)
	(,mc8-mode-constant-regex . font-lock-constant-face)
	))
  )

;; Indentation function that indents after open brackets (`(', `{', or `[')
;; and de-indents after matching closing brackets (`)', `}', or `]').
;; Source: https://www.omarpolo.com/post/writing-a-major-mode.html
(defun mc8-mode-indent-line ()
  "Indent the current line based on the MC8 grammar rules."
  (let (indent
        boi-p
        move-eol-p
        (point (point)))
    (save-excursion
      (back-to-indentation)
      (setq indent (car (syntax-ppss))
            boi-p (= point (point)))
      (when (and (eq (char-after) ?\n)
                 (not boi-p))
        (setq indent 0))
      (when boi-p
        (setq move-eol-p t))
      (when (or (eq (char-after) ?\))
                (eq (char-after) ?\}))
        (setq indent (1- indent)))
      (delete-region (line-beginning-position)
                     (point))
      (indent-to (* tab-width indent)))
    (when move-eol-p
      (move-end-of-line nil))))

;; Set the tab width
(defvar mc8-mode-tab-width 4 "Width of a tab for MC8 mode")

;; Define the mode
(define-derived-mode mc8-mode fundamental-mode "MC8 mode"
  "Major mode for editing MC8 files"
  (setq font-lock-defaults mc8-mode-font-lock-defaults)
  (setq tab-width mc8-mode-tab-width)
  
  (setq comment-start "//")
  (setq comment-end "")
  
  (modify-syntax-entry ?/ ". 124b" mc8-mode-syntax-table)
  (modify-syntax-entry ?\n "> b" mc8-mode-syntax-table)

  (setq indent-line-function #'mc8-mode-indent-line)
  )

(add-to-list 'auto-mode-alist '("\\.mc8\\'" . mc8-mode))
(provide 'mc8-mode)
