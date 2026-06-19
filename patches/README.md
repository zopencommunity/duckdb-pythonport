# duckdb-python z/OS port patches

No standalone patch files are applied by zopen-build.

The buildenv applies two small source-tree adjustments in `zopen_init()` because DuckDB is checked out as a git submodule under `external/duckdb`, and root-level patch application cannot reliably modify files inside that submodule:

- avoid CMake `$<LINK_LIBRARY:WHOLE_ARCHIVE,...>` for z/OS, where this generator expression is unsupported for the CXX link language
- define `MAP_ANONYMOUS` for z/OS if it is not exposed by the active feature-test macro set

The buildenv also:

- uses zopen-provided `cmake`/`ninja` with `python -m build --no-isolation --skip-dependency-check`
- links Python's z/OS side deck (`libpython3.12.x`) explicitly for the `_duckdb` extension
- runs a smoke test with parsed results instead of upstream pytest, because the packaged zopen pytest reports a nonstandard version string that fails upstream `minversion` validation
