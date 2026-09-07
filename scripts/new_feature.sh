#!/bin/bash
# Usage: ./scripts/new_feature.sh <feature_name>
# Example: ./scripts/new_feature.sh settings

set -e

FEATURE="${1:-}"
if [ -z "$FEATURE" ]; then
  echo "Usage: $0 <feature_name>"
  exit 1
fi

PACKAGE="com.example.boilerplate.feature.$FEATURE"
CAPITALIZED="$(tr '[:lower:]' '[:upper:]' <<< "${FEATURE:0:1}")${FEATURE:1}"
ROOT="feature/$FEATURE"

echo "Creating feature module: $FEATURE"

# Create directories
mkdir -p "$ROOT/src/main/kotlin/$(echo $PACKAGE | tr '.' '/')"
mkdir -p "$ROOT/src/test/kotlin/$(echo $PACKAGE | tr '.' '/')"
mkdir -p "$ROOT/src/androidTest/kotlin/$(echo $PACKAGE | tr '.' '/')"

# build.gradle.kts
cat > "$ROOT/build.gradle.kts" << EOF
plugins {
    id("boilerplate.android.feature")
}

dependencies {
    implementation(project(":core:domain"))
}
EOF

# AndroidManifest.xml
cat > "$ROOT/src/main/AndroidManifest.xml" << EOF
<?xml version="1.0" encoding="utf-8"?>
<manifest />
EOF

# UiState
cat > "$ROOT/src/main/kotlin/$(echo $PACKAGE | tr '.' '/')/${CAPITALIZED}UiState.kt" << EOF
package $PACKAGE

sealed interface ${CAPITALIZED}UiState {
    data object Loading : ${CAPITALIZED}UiState
    data class Success(val data: Unit) : ${CAPITALIZED}UiState
    data class Error(val message: String) : ${CAPITALIZED}UiState
}
EOF

echo "Feature '$FEATURE' created at $ROOT"
echo "Next steps:"
echo "  1. Add route to core/navigation/Routes.kt"
echo "  2. Wire in app/BoilerplateNavHost.kt"
echo "  3. Add ':feature:$FEATURE' to settings.gradle.kts"
