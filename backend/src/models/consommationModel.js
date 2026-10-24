// --- Model: Données de consommation via Prisma / PostgreSQL ---
import prisma from '../config/prisma.js';

export const getConsommationTodayModel = async () => {
    try {
        const result = await prisma.consommation.findFirst({
            include: { weeklyData: true },
            orderBy: { date: 'desc' }
        });
        if (result) return result;
    } catch (error) {
        console.warn("⚠️ Prisma / Base de données non connectée, données par défaut (0) :");
    }

    return {
        totalKwh: 0,
        percentVsYesterday: 0,
        estimatedCostFcfa: 0,
        activePowerKw: 0,
        subscribedPowerKva: 0,
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
    };
};

export const getAppliancesModel = async () => {
    try {
        const result = await prisma.appliance.findMany();
        if (result && result.length > 0) return result;
    } catch (error) {
        console.warn("⚠️ Prisma / Base de données non connectée, données par défaut :");
    }

    return [
        { category: "Électroménager", description: "Réfrigérateur, lave-linge, four", percentage: 45 },
        { category: "Chauffage", description: "Radiateurs du domicile", percentage: 30 },
        { category: "Éclairage", description: "Ampoules et luminaires", percentage: 15 },
        { category: "Autres", description: "Télévision, box et petits appareils", percentage: 10 }
    ];
};
