import type { Adventurer } from '../types';
import styles from './AdventurersPage.module.css';

// ── Données fictives ──────────────────────────────────────────────────────────
const FAKE_ADVENTURERS: Adventurer[] = [
  { id: 1, name: 'Grimbold le Prudent',  adventurerType: 'WARRIOR', level: 7, xp: 420, gold: 3240 },
  { id: 2, name: 'Lyrawen Sombre',       adventurerType: 'MAGE',    level: 6, xp: 540, gold: 2100 },
  { id: 3, name: "Fenwick l'Invisible",  adventurerType: 'RANGER',  level: 5, xp: 210, gold: 1860 },
  { id: 4, name: 'Sœur Marvyn',          adventurerType: 'CLERIC',  level: 5, xp: 100, gold: 1640 },
  { id: 5, name: 'Drakh-Baine',          adventurerType: 'WARRIOR', level: 4, xp: 230, gold: 980  },
];

// ── Emoji par classe ──────────────────────────────────────────────────────────
const CLASS_EMOJI: Record<Adventurer['adventurerType'], string> = {
  WARRIOR: '⚔️',
  MAGE:    '🔮',
  RANGER:  '🏹',
  CLERIC:  '✨',
};

// ── Composant ─────────────────────────────────────────────────────────────────
export default function AdventurersPage() {
  return (
    <div className={styles.page}>
      <h1 className={styles.title}>Aventuriers</h1>

      {/* ── Grille de cartes ── */}
      {/* .map() = pour chaque aventurier dans le tableau, on crée une carte */}
      <div className={styles.grid}>
        {FAKE_ADVENTURERS.map(adv => {

          // Calcul de la barre d'XP
          // Règle du PDF : le seuil du niveau suivant = level x 100
          // Ex : niveau 7 → il faut 700 XP pour passer niveau 8
          const xpNeeded  = adv.level * 100;

          // On calcule le pourcentage pour la barre visuelle
          // Ex : 420 XP sur 700 nécessaires = 60%
          const xpPercent = Math.min((adv.xp / xpNeeded) * 100, 100);
          // Math.min(..., 100) = on bloque à 100% max, pas de barre qui déborde

          return (
            <div key={adv.id} className={styles.card}>

              {/* En-tête de la carte */}
              <div className={styles.cardHeader}>
                <span className={styles.emoji}>{CLASS_EMOJI[adv.adventurerType]}</span>
                <div>
                  <div className={styles.name}>{adv.name}</div>
                  <span className={`${styles.classBadge} ${styles[adv.adventurerType]}`}>
                    {adv.adventurerType}
                  </span>
                </div>
                <div className={styles.level}>Niv. {adv.level}</div>
              </div>

              {/* Barre d'XP */}
              <div className={styles.xpSection}>
                <div className={styles.xpLabels}>
                  <span>XP</span>
                  {/* On affiche l'xp actuelle et l'xp nécessaire */}
                  <span>{adv.xp} / {xpNeeded}</span>
                </div>
                {/* La barre grise = le fond */}
                <div className={styles.xpBar}>
                  {/* La barre colorée = le remplissage, sa largeur = le pourcentage */}
                  <div
                    className={styles.xpFill}
                    style={{ width: `${xpPercent}%` }}
                  />
                </div>
              </div>

              {/* Stats or */}
              <div className={styles.stats}>
                <span className={styles.gold}>🪙 {adv.gold} or</span>
              </div>

            </div>
          );
        })}
      </div>
    </div>
  );
}