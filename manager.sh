#!/bin/bash

echo "Its downloading Optiflex"
echo "..."

# Compilar
./gradlew build
if [ $? -ne 0 ]; then
    echo "❌ Error: la compilación falló."
    exit 1
fi

echo "✅ Compilación exitosa."

# Pedir carpeta de mods
read -p "Introduce la ruta de la carpeta de Minecraft (mods): " mc_dir

# Verificar carpeta
if [ -d "$mc_dir" ]; then
    echo "📂 Carpeta encontrada: $mc_dir"
    # aquí podrías copiar el .jar si quieres
else
    echo "⚠️ La carpeta no existe: $mc_dir"
    exit 1
fi
