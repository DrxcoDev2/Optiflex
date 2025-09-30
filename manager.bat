@echo off
echo Its downloading Optiflex
echo ...

REM Compilar
call gradlew build
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Error: la compilación falló.
    exit /b 1
)

echo ✅ Compilación exitosa.

set /p mc_dir=Introduce la ruta de la carpeta de Minecraft (mods):

if exist "%mc_dir%" (
    echo 📂 Carpeta encontrada: %mc_dir%
) else (
    echo ⚠️ La carpeta no existe: %mc_dir%
    exit /b 1
)
