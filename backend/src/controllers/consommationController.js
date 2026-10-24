// --- Controller: Logique de traitement des requêtes de consommation ---
import { getConsommationTodayModel, getAppliancesModel } from '../models/consommationModel.js';

export const getConsommationToday = async (req, res) => {
    try {
        const data = await getConsommationTodayModel();
        res.status(200).json(data);
    } catch (error) {
        res.status(500).json({
            message: "Erreur serveur lors de la récupération de la consommation",
            error: error.message
        });
    }
};

export const getAppliances = async (req, res) => {
    try {
        const data = await getAppliancesModel();
        res.status(200).json(data);
    } catch (error) {
        res.status(500).json({
            message: "Erreur serveur lors de la récupération des appareils",
            error: error.message
        });
    }
};
