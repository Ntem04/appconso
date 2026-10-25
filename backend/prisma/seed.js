// --- Seed PostgreSQL database with initial real data ---
import { PrismaClient } from '@prisma/client';
import bcrypt from 'bcryptjs';

const prisma = new PrismaClient();

async function main() {
    console.log("🌱 Démarrage du remplissage de la base de données PostgreSQL...");

    // 1. Créer ou récupérer un utilisateur par défaut
    const hashedPassword = await bcrypt.hash("Password123", 10);
    const user = await prisma.user.upsert({
        where: { email: "user@ecoconso.com" },
        update: {},
        create: {
            fullName: "Jean Dupont",
            email: "user@ecoconso.com",
            phone: "+2250700000000",
            password: hashedPassword,
        },
    });

    // 2. Créer un compteur pour cet utilisateur
    await prisma.meter.upsert({
        where: { meterNumber: "E-100234908123" },
        update: {},
        create: {
            meterNumber: "E-100234908123",
            subscribedPowerKva: 6,
            userId: user.id,
        },
    });

    // 3. Insérer les enregistrements de consommation si inexistants
    const existingConso = await prisma.consommation.findFirst({
        where: { userId: user.id },
    });

    if (!existingConso) {
        await prisma.consommation.create({
            data: {
                totalKwh: 14.8,
                percentVsYesterday: -5.2,
                estimatedCostFcfa: 3200,
                activePowerKw: 3.5,
                subscribedPowerKva: 6,
                weeklyTotalKwh: 98.4,
                userId: user.id,
                weeklyData: {
                    create: [
                        { day: "Lun", kwh: 12.5 },
                        { day: "Mar", kwh: 15.1 },
                        { day: "Mer", kwh: 13.8 },
                        { day: "Jeu", kwh: 16.2 },
                        { day: "Ven", kwh: 14.0 },
                        { day: "Sam", kwh: 12.0 },
                        { day: "Dim", kwh: 14.8 },
                    ],
                },
            },
        });
        console.log("✅ Données de consommation enregistrées dans PostgreSQL !");
    }

    // 4. Insérer les catégories d'appareils
    const existingAppliances = await prisma.appliance.count({
        where: { userId: user.id },
    });

    if (existingAppliances === 0) {
        await prisma.appliance.createMany({
            data: [
                { category: "Électroménager", description: "Réfrigérateur, lave-linge, four", percentage: 45, userId: user.id },
                { category: "Climatisation", description: "Climatiseur salon et chambres", percentage: 30, userId: user.id },
                { category: "Éclairage", description: "Ampoules LED et spots", percentage: 15, userId: user.id },
                { category: "Autres", description: "TV, Box internet et petits appareils", percentage: 10, userId: user.id },
            ],
        });
        console.log("✅ Appareils enregistrés dans PostgreSQL !");
    }

    console.log("🎉 Base de données PostgreSQL alimentée avec succès !");
}

main()
    .catch((e) => {
        console.error("❌ Erreur Seeding :", e);
        process.exit(1);
    })
    .finally(async () => {
        await prisma.$disconnect();
    });
