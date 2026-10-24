// --- Routes: Définition des endpoints pour la consommation ---
import { Router } from 'express';
import { getConsommationToday, getAppliances } from '../controllers/consommationController.js';

const router = Router();

router.get('/today', getConsommationToday);
router.get('/appliances', getAppliances);

export default router;
