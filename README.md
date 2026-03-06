# 💰 Nova Capital — App Android (Proyecto DAM)

Aplicación educativa de simulación de inversiones con moneda virtual **Aurus**.

---

## 📁 Estructura del proyecto

```
NovaCapital/
├── app/
│   ├── build.gradle                    ← Dependencias (Room, RecyclerView...)
│   └── src/main/
│       ├── AndroidManifest.xml         ← Activities declaradas
│       ├── java/com/novacapital/
│       │   ├── activities/
│       │   │   ├── MainActivity.java           ← Pantalla principal
│       │   │   ├── ListaProyectosActivity.java ← Lista de proyectos
│       │   │   ├── CrearProyectoActivity.java  ← Formulario nuevo proyecto
│       │   │   ├── DetalleProyectoActivity.java← Invertir en proyecto
│       │   │   ├── PerfilActivity.java         ← Perfil + historial
│       │   │   └── RetosActivity.java          ← Lista de retos
│       │   ├── adapters/
│       │   │   ├── ProyectoAdapter.java        ← RecyclerView proyectos
│       │   │   ├── InversionAdapter.java       ← RecyclerView historial
│       │   │   └── RetoAdapter.java            ← RecyclerView retos
│       │   ├── database/
│       │   │   ├── AppDatabase.java            ← Base de datos Room (Singleton)
│       │   │   ├── UsuarioDao.java             ← Consultas usuario
│       │   │   ├── ProyectoDao.java            ← Consultas proyectos
│       │   │   ├── InversionDao.java           ← Consultas inversiones
│       │   │   └── RetoDao.java                ← Consultas retos
│       │   └── models/
│       │       ├── Usuario.java                ← Entidad usuario
│       │       ├── Proyecto.java               ← Entidad proyecto
│       │       ├── Inversion.java              ← Entidad inversión
│       │       └── Reto.java                   ← Entidad reto
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml
│           │   ├── activity_lista_proyectos.xml
│           │   ├── activity_crear_proyecto.xml
│           │   ├── activity_detalle_proyecto.xml
│           │   ├── activity_perfil.xml
│           │   ├── activity_retos.xml
│           │   ├── item_proyecto.xml
│           │   ├── item_inversion.xml
│           │   └── item_reto.xml
│           └── values/
│               ├── strings.xml
│               └── themes.xml
├── build.gradle
└── settings.gradle
```

---

## 🚀 Cómo abrir en Android Studio

1. Abre **Android Studio**
2. Selecciona **"Open an existing project"**
3. Navega hasta la carpeta `NovaCapital/`
4. Espera a que Gradle sincronice las dependencias
5. Conecta un dispositivo o abre el **AVD Manager** (emulador)
6. Pulsa ▶️ **Run 'app'**

---

## 🧪 Flujo de prueba

1. **Primera apertura**: se crea el usuario con 1000 Aurus y 4 retos
2. **Crear proyecto**: ir a "Crear Proyecto", rellenar el formulario
   - Se completa el reto "Crear tu primer proyecto" → +75 Aurus
3. **Invertir**: entrar en "Ver Proyectos" → pulsar "Invertir" en un proyecto
   - Al hacer la primera inversión → reto "Invertir por primera vez" → +50 Aurus
   - Al invertir en 3 proyectos distintos → reto → +150 Aurus
   - Al financiar un proyecto 100% → reto → +200 Aurus
4. **Perfil**: ver saldo actualizado e historial de inversiones
5. **Retos**: ver qué retos están completados (verde) y cuáles pendientes (gris)

---

## 🛠️ Tecnologías usadas

| Tecnología | Uso |
|-----------|-----|
| Java | Lógica de la aplicación |
| Room (SQLite) | Base de datos local |
| RecyclerView | Listas de proyectos, historial y retos |
| LinearLayout | Diseño de pantallas |
| Material Design | Tema y botones |

---

## 📋 Base de datos — Tablas Room

```
usuarios     → id, nombre, saldoAurus
proyectos    → id, nombre, descripcion, inversionObjetivo, inversionActual, estado
inversiones  → id, idProyecto, cantidad, fecha
retos        → id, titulo, recompensa, completado
```

---

## ⚠️ Notas para el estudiante

- `allowMainThreadQueries()` en AppDatabase está activado para simplificar el código.
  En una app de producción se usaría `AsyncTask` o `LiveData`.
- Los retos se completan automáticamente al realizar acciones, no hace falta pulsarlos.
- El usuario siempre es el mismo (local, sin login).
- Para resetear la app: desinstalar y volver a instalar.
