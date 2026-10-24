// --- Model: Données de consommation ---

export const getConsommationTodayModel = async () => {
    return {
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
    };
};

export const getAppliancesModel = async () => {
    return [
        { category: "Électroménager", description: "Réfrigérateur, lave-linge, four", percentage: 45 },
        { category: "Chauffage", description: "Radiateurs du domicile", percentage: 30 },
        { category: "Éclairage", description: "Ampoules et luminaires", percentage: 15 },
        { category: "Autres", description: "Télévision, box et petits appareils", percentage: 10 }
    ];
};
