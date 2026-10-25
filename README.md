# ⚡ E-Consommation (EcoConso) - Application Mobile & API Backend

**E-Consommation** est une application Android moderne de suivi et de gestion de la consommation électrique, couplée à un serveur backend Node.js / Express structuré en architecture **MVC** avec **Prisma ORM** et une base de données **PostgreSQL**.

---

## 🛠️ Stack Technique

### **Application Mobile (Frontend)**
* **Langage :** Kotlin
* **UI Framework :** Jetpack Compose (Material 3)
* **Architecture :** MVVM (Model-View-ViewModel) + StateFlow
* **Réseau :** Retrofit 2 & Gson
* **Design & Thématique :** Support complet du Mode Sombre (Dark Mode) & Devise en **FCFA**

### **Serveur API (Backend)**
* **Environnement :** Node.js (ES Modules) & Express
* **Architecture :** MVC (Models, Controllers, Routes)
* **ORM & Base de données :** Prisma ORM & PostgreSQL
* **Sécurité :** Hachage des mots de passe avec `bcrypt` / `bcryptjs`

---

## 🚀 Guide de Démarrage Rapide pour les Tests

### **1. Prérequis**
Avant de commencer, assurez-vous d'avoir installé :
* [Android Studio](https://developer.android.com/studio) avec un émulateur configuré (ex: **Pixel 6**).
* [Node.js](https://nodejs.org/) (Version 18 ou supérieure) & `npm`.
* [PostgreSQL](https://www.postgresql.org/) en cours d'exécution localement sur le port `5432`.
* `adb` (Android Debug Bridge) configuré dans votre variable `PATH`.

---

### **2. Démarrer le Backend & la Base de Données PostgreSQL**

1. Ouvrez un terminal et naviguez dans le dossier `backend` :
   ```bash
   cd backend
   ```

2. Installez les dépendances :
   ```bash
   npm install
   ```

3. Configurez l'accès à votre base de données PostgreSQL dans le fichier `.env` :
   ```env
   DATABASE_URL="postgresql://postgres:postgres@localhost:5432/ecoconso_db?schema=public"
   PORT=3000
   ```

4. Générez le client Prisma et appliquez les migrations PostgreSQL :
   ```bash
   npx prisma generate
   npx prisma migrate dev --name init
   ```

5. (Optionnel) Alimentez la base de données avec des données initiales de test :
   ```bash
   node prisma/seed.js
   ```

6. Lancez le serveur backend Express :
   ```bash
   npm start
   ```
   *Le serveur démarrera sur `http://localhost:3000`.*

---

### **3. Configurer la Redirection de Port ADB**

Pour permettre à l'émulateur Android de communiquer directement avec votre serveur local `localhost:3000`, exécutez la commande suivante dans votre terminal :

```bash
adb reverse tcp:3000 tcp:3000
```

---

### **4. Compiler et Lancer l'Application Mobile**

#### **Option A : Via Android Studio (Recommandé)**
1. Ouvrez le dossier `appconso` dans Android Studio.
2. Attendez la fin de la synchronisation Gradle.
3. Lancez l'émulateur (ex: **Pixel 6**).
4. Cliquer sur le bouton **Run** (ou appuyez sur `Shift + F10`).

#### **Option B : Via la Ligne de Commande**
1. À la racine du projet `appconso`, compilez et installez l'APK sur l'émulateur connecté :
   * **Sur Windows :**
     ```powershell
     .\gradlew.bat installDebug
     ```
   * **Sur Linux / macOS :**
     ```bash
     ./gradlew installDebug
     ```

2. Lancez l'application sur l'émulateur :
   ```bash
   adb shell monkey -p com.example.ecoconso -c android.intent.category.LAUNCHER 1
   ```

---

## 📱 Fonctionnalités Principales à Tester

1. **Écran d'Accueil (`HomeScreen`) :**
   * Affichage de la consommation du jour, puissance active et coût estimé en **FCFA**.
   * Graphique sparkline et historique hebdomadaire dynamique.

2. **Écran Détails de Consommation (`ConsoDetailScreen`) :**
   * **Animation de chargement progressif** au démarrage lors de la récupération des données depuis PostgreSQL via Prisma.
   * Graphique en courbe lissée (`EcoSmoothAreaChart`) et graphique en anneau (`EcoDonutChart`) de la répartition des appareils.

3. **Mode Sombre (Dark Mode) :**
   * Basculement fluide entre le Mode Clair et le Mode Sombre via l'icône dans la barre supérieure ou les paramètres.
   * Palette de couleurs optimisée pour un contraste et une lisibilité parfaite.

4. **Authentification (`RegisterScreen`, `LoginScreen`) :**
   * Formulaires d'inscription et de connexion avec conteneurs ajustés pour le mode sombre.

---

## 📁 Structure du Projet

```text
appconso/
├── app/                        # Application Android (Kotlin + Compose)
│   └── src/main/java/com/example/ecoconso/
│       ├── data/               # API Retrofit Clients, Models
│       ├── ui/                 # Écrans Compose, ViewModels, Composants
│       └── theme/              # Couleurs (Light/Dark), Thèmes
├── backend/                    # Serveur Node.js (Architecture MVC)
│   ├── prisma/                 # Schéma Prisma, Migrations & Script Seed
│   └── src/
│       ├── config/             # Configuration Prisma Client
│       ├── controllers/        # Contrôleurs HTTP
│       ├── models/             # Modèles de données PostgreSQL
│       ├── routes/             # Routes Express API
│       └── utils/              # Hachage bcrypt des mots de passe
└── README.md                   # Documentation du projet
```
