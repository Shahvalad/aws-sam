build-GetAllPiratesFunction:
	.\gradlew :GetAllPiratesFunction:buildZip
	powershell -Command "Expand-Archive -Force GetAllPiratesFunction\\build\\distributions\\GetAllPiratesFunction.zip $$Env:ARTIFACTS_DIR"

build-GetPirateByIdFunction:
	.\gradlew :GetPirateByIdFunction:buildZip
	powershell -Command "Expand-Archive -Force GetPirateByIdFunction\\build\\distributions\\GetPirateByIdFunction.zip $$Env:ARTIFACTS_DIR"

build-SavePirateFunction:
	.\gradlew :SavePirateFunction:buildZip
	powershell -Command "Expand-Archive -Force SavePirateFunction\\build\\distributions\\SavePirateFunction.zip $$Env:ARTIFACTS_DIR"