.PHONY: build test lint format clean screenshot-record screenshot-verify

build:
	./gradlew assembleDebug

test:
	./gradlew testDebug

lint:
	./gradlew lintDebug detekt spotlessCheck

format:
	./gradlew spotlessApply

screenshot-record:
	./gradlew recordRoborazziDebug

screenshot-verify:
	./gradlew verifyRoborazziDebug

clean:
	./gradlew clean
