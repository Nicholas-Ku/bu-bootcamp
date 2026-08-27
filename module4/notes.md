# Module 4 Notes: Git, GitHub, and the Hook Analogy

## GitHub side
- GitHub calls them **actions**, not hooks.
- Whenever you do a merge, GitHub runs all the unit tests.
- If it fails a test, GitHub won't let you merge the PR into main.

## Same principle: AI agents
- Claude is an AI model. It can't actually *do* anything on its own. You give it tokens as input, it gives tokens as output.
- That's the difference between an **AI model** and an **autonomous agent**.
- The original model could talk, but it couldn't take actions.
- **Agent = LLM + Tools.** The tools are what let the agent actually do things.
- One tool is `read`. Another is a tool that merges a PR.
- Before it runs a tool, it calls the **pre-hook** for that tool.
- Literally no different than GitHub refusing to merge a PR into main because it failed a unit test. Same principle.

## Post-tool hooks
- Run clang-format before it pushes any code (basically running Prettier).
- clang-tidy is similar to ESLint.
- Both are good examples of a post-tool hook.
