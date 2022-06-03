# deps-proto: compiling protobuf for the deps toolchain

This deps tool expose three functions to generate and compile protobuf
source files.

## Prerequisites

This tool assumes you have a `protoc` binary somewhere on the system.

## Installation

The tool can be used as a global clojure tool:

``` shell
clj -Ttools install io.github.pyr/deps-proto '{:git/sha "…"}' :as proto
```

Or can be defined in your `deps.edn` as an alias:

``` clojure
{:aliases
 {:proto
   {:deps {io.github.pyr/deps-proto {:git/sha "…"}}
    :ns-default spootnik.deps-proto}}}
```

## Functions

### `generate`

Generates java code from protobuf files.

``` shell
clj -Tproto generate
```

Supported options:

- `:source-path`: where protobuf files are stored, defaults to `"proto"`
- `:generate-path`: where to store generated java files, defaults to `"target/java"`
- `:protoc-bin`: path to the protoc binary, defaults to `"protoc"`
- `:proto-files`: A collection of protobuf files to generate code from. Defaults to
  all protobuf files found in the source path.
  
### `compile-java`

Compiles the generated source

``` shell
clj -Tproto compile-java
```
Supported options:

- `:classes-path`: where to store compiled java classes, defaults to `"target/classes"`
- `:generate-path`: where to store generated java files, defaults to `"target/java"`

### `generate-and-comile`

Generates java code from protobuf files and compiles the generated source.

``` shell
clj -Tproto generate-and-compile
```
Supported options:

- `:classes-path`: where to store compiled java classes, defaults to `"target/classes"`
- `:source-path`: where protobuf files are stored, defaults to `"proto"`
- `:generate-path`: where to store generated java files, defaults to `"target/java"`
- `:protoc-bin`: path to the protoc binary, defaults to `"protoc"`
- `:proto-files`: A collection of protobuf files to generate code from. Defaults to
  all protobuf files found in the source path.







