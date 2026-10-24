import express from 'express';
import cors from 'cors';

const app = express();
const PORT = process.env.PORT || 3000;

// Middlewares
app.use(cors()); // Permet à l'application Android (ou autres clients) de communiquer avec l'API
app.use(express.json()); // Permet de lire le JSON envoyé dans les requêtes (ex: pour le login)

// --- ROUTES DE TEST (Bouchons / Mock) ---

// Route par défaut pour vérifier que le serveur tourne
app.get('/', (req, res) => {
    res.json({ message: "Bienvenue sur l'API E-Consommation ! Le serveur fonctionne 🚀" });
});

// Mock: Données de consommation de la page d'accueil
app.get('/api/consommation/today', (req, res) => {
    res.json({
        totalKwh: 12.4,
        percentVsYesterday: -8,
        estimatedCostFcfa: 2850,
        activePowerKw: 3.2,
        subscribedPowerKva: 6,
        weeklyTotalKwh: 0,
        weeklyData: [
            { day: "Lun", kwh: 0 },
            { day: "Mar", kwh: 0 },
            { day: "Mer", kwh: 0 },
            { day: "Jeu", kwh: 0 },
            { day: "Ven", kwh: 0 },
            { day: "Sam", kwh: 0 },
            { day: "Dim", kwh: 0 }
        ]
    });
});

// Mock: Répartition par appareil
app.get('/api/consommation/appliances', (req, res) => {
    res.json([
        { category: "Électroménager", description: "Réfrigérateur, lave-linge, four", percentage: 45 },
        { category: "Chauffage", description: "Radiateurs du domicile", percentage: 30 },
        { category: "Éclairage", description: "Ampoules et luminaires", percentage: 15 },
        { category: "Autres", description: "Télévision, box et petits appareils", percentage: 10 }
    ]);
});

// Lancement du serveur
app.listen(PORT, '0.0.0.0', () => {
    console.log(`✅ Serveur Démarré sur http://0.0.0.0:${PORT}`);
    console.log(`🔄 Mode Dev avec Nodemon activé !`);
});