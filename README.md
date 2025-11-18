Pokemon Abilities App — Prueba Técnica Android (Kotlin + Hilt + Fragments)
Esta aplicación fue desarrollada como parte de una prueba técnica para evaluar conocimientos en:
Desarrollo con Kotlin
Arquitectura con ViewModel + Flow
Navegación con Fragments
Inyección de dependencias con Dagger Hilt
Consumo de APIs REST
Diseño UI con Material Design 3
Buenas prácticas de arquitectura Android
La app consume la PokeAPI y permite consultar una lista de habilidades (Abilities) y ver su detalle completo.

Características principales
1. Lista de habilidades Pokémon

Obtención de datos desde https://pokeapi.co/api/v2/ability/?limit=10
Renderizado con RecyclerView + ListAdapter + DiffUtil
Cada item tiene:
Nombre
ID dentro de un badge circular
Flecha de navegación
Tarjeta estilizada con MaterialCardView

2. Detalle completo de una habilidad

Se muestra información obtenida desde /ability/{id}:
Nombre
Efecto principal
Short Effect
Generación
Flavor Text más reciente en inglés (Pokédex)
Lista de Pokémon que pueden tener la habilidad
Secciones separadas con tarjetas de Material Design 3
ScrollView con diseño limpio y moderno

3. Navegación

Fragmentos con comunicación segura mediante argumentos
Botón volver con MaterialButton

4. Manejo de Estados

En el detalle se contemplan:

Loader mientras se obtiene la data
Error si falla la petición
Contenido cuando llega correctamente
Todo manejado desde un StateFlow.

Tecnologías usadas:

Kotlin
Dagger Hilt
Coroutines + Flow
Retrofit
Fragments
Material Design 3
MVVM

Cómo ejecutar este proyecto
1️⃣ Clonar el repositorio
git clone https://github.com/kentandrade1/PokeTest.git
2️⃣ Abrir en Android Studio
Requiere Android Studio 2024 o superior.
3️⃣ Sincronizar Gradle
El proyecto usa:
Kotlin
AGP 
Retrofit
Hilt
Solo abre y sincroniza.
4️⃣ Ejecutar la app
Selecciona un dispositivo físico o emulador Android y presiona ► Run.


Desarrollado por

Kent Leopoldo Andrade Muñoz
Guayaquil, Ecuador