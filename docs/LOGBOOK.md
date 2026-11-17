# Logbook

Every problem, bug, query and design decision, with the fix and the lesson.

## Template (copy for each entry)

### [LOG-001] Short title
- Date / Step:
- Type: Bug | Query | Design decision | Learning
- Problem: (exact error message)
- Cause:
- Fix: (commit link)
- Lesson:

---

### [LOG-001] PowerShell treated pasted commands as one line
- Date / Step: 2026-11-17 / Step 0
- Type: Bug
- Problem: Pasting several git commands at once gave "The provider does not support transactions".
- Cause: The commands were joined into one line, so PowerShell parsed one broken command.
- Fix: Ran each command separately and removed the < > from the GitHub username.
- Lesson: Paste one command at a time and read the output before the next.

### [LOG-002] First commit had empty files
- Date / Step: 2026-11-17 / Step 0
- Type: Bug
- Problem: The commit showed "0 insertions" and the docs folder was missing.
- Cause: README and .gitignore were empty, and docs was a plain file instead of a folder.
- Fix: Pasted the content into each file, recreated docs as a real folder, then add, commit, push.
- Lesson: Run git status before committing and check the insertion count.