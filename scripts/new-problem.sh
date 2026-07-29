#!/usr/bin/env bash

set -euo pipefail

if [[ $# -ne 2 ]]; then
    echo "Usage:"
    echo "  $0 <topic> <problem-name>"
    echo
    echo "Example:"
    echo "  $0 arrays two-sum"
    exit 1
fi

TOPIC="$1"
PROBLEM="$2"

# Convert kebab-case to PascalCase
to_pascal_case() {
    local input="$1"
    local output=""
    local part

    IFS='-' read -ra parts <<< "$input"

    for part in "${parts[@]}"; do
        first=$(printf "%s" "$part" | cut -c1 | tr '[:lower:]' '[:upper:]')
        rest=$(printf "%s" "$part" | cut -c2-)
        output="${output}${first}${rest}"
    done

    echo "$output"
}

CLASS_NAME=$(to_pascal_case "$PROBLEM")

DIR="problems/${TOPIC}/${PROBLEM}"

mkdir -p "${DIR}/java"
mkdir -p "${DIR}/python"
mkdir -p "${DIR}/go"

cat > "${DIR}/README.md" <<EOF
# ${CLASS_NAME}

## Problem Statement

## Constraints

## Brute Force Approach

## Optimized Approach

## Time Complexity

## Space Complexity

## Java Solution

## Python Solution

## Go Solution

## Code Comparison

## Key Learnings

## Interview Questions
EOF

touch "${DIR}/java/${CLASS_NAME}.java"
touch "${DIR}/python/${CLASS_NAME}.py"
touch "${DIR}/go/${CLASS_NAME}.go"

echo
echo "Created:"
echo "  ${DIR}"
echo
echo "Files:"
echo "  java/${CLASS_NAME}.java"
echo "  python/${CLASS_NAME}.py"
echo "  go/${CLASS_NAME}.go"