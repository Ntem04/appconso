// --- Main Entry Point ---
import app from './src/app.js';

const PORT = process.env.PORT || 3000;

app.listen(PORT, '0.0.0.0', () => {
    console.log(`✅ Serveur Démarré sur http://0.0.0.0:${PORT}`);
    console.log(`🔄 Mode MVC opérationnel !`);
});
