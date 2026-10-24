// --- App Express configuration ---
import express from 'express';
import cors from 'cors';
import consommationRoutes from './routes/consommationRoutes.js';

const app = express();

// Middlewares
app.use(cors());
app.use(express.json());

// Base Route
app.get('/', (req, res) => {
    res.json({ message: "Bienvenue sur l'API E-Consommation ! Le serveur fonctionne 🚀" });
});

// Register MVC Routes
app.use('/api/consommation', consommationRoutes);

export default app;
