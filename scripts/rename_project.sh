#!/bin/bash
# Usage: ./scripts/rename_project.sh <new.package.name> "New App Name"
# Example: ./scripts/rename_project.sh com.mycompany.myapp "My App"

set -e

NEW_PACKAGE="${1:-}"
NEW_NAME="${2:-}"
OLD_PACKAGE="com.example.boilerplate"
OLD_NAME="Android Boilerplate"

if [ -z "$NEW_PACKAGE" ] || [ -z "$NEW_NAME" ]; then
  echo "Usage: $0 <new.package.name> \"New App Name\""
  exit 1
fi

echo "Renaming project: $OLD_PACKAGE → $NEW_PACKAGE"
echo "App name: $OLD_NAME → $NEW_NAME"

# Replace package names in all Kotlin files
find . -name "*.kt" -not -path "./.git/*" | xargs sed -i '' "s/$OLD_PACKAGE/$NEW_PACKAGE/g"
find . -name "*.kts" -not -path "./.git/*" | xargs sed -i '' "s/$OLD_PACKAGE/$NEW_PACKAGE/g"
find . -name "*.xml" -not -path "./.git/*" | xargs sed -i '' "s/$OLD_PACKAGE/$NEW_PACKAGE/g"
find . -name "*.toml" -not -path "./.git/*" | xargs sed -i '' "s/$OLD_PACKAGE/$NEW_PACKAGE/g"

# Replace app name in strings.xml
find . -name "strings.xml" | xargs sed -i '' "s/$OLD_NAME/$NEW_NAME/g"

echo "Done! Run './gradlew build' to verify."
