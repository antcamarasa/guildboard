import { useState } from 'react';
import type { Quest, QuestStatus, Difficulty } from '../types';
import styles from './QuestsPage.module.css';

// ── Données fictives -----
const FAKE_QUESTS: Quest[] = [
  { id: 1, title: 'La Crypte Oubliée',   description: 'Des bruits étranges viennent des profondeurs.', difficulty: 'HARD',   status: 'ON_GOING',  minLevel: 5, goldReward: 800,  xpReward: 300 },
  { id: 2, title: 'Escorte du Marchand', description: "Protéger un marchand jusqu'à la ville.",        difficulty: 'EASY',   status: 'AVAILABLE', minLevel: 1, goldReward: 150,  xpReward: 80  },
  { id: 3, title: 'Le Dragon de Givre',  description: 'Un dragon terrorise les villages du nord.',     difficulty: 'EPIC',   status: 'AVAILABLE', minLevel: 8, goldReward: 2000, xpReward: 900 },
  { id: 4, title: 'Rats des Égouts',     description: 'Nettoyer les égouts de la ville.',              difficulty: 'EASY',   status: 'COMPLETED', minLevel: 1, goldReward: 50,   xpReward: 30  },
  { id: 5, title: 'Tour du Nécromant',   description: 'Infiltrer la tour et récupérer le grimoire.',   difficulty: 'MEDIUM', status: 'ON_GOING',  minLevel: 3, goldReward: 400,  xpReward: 180 },
];

// ── Libellés lisibles pour la difficulté ──
// On traduit les valeurs techniques en français pour l'affichage
const STATUS_LABEL: Record<QuestStatus, string> = {
  AVAILABLE: 'Disponible',
  ON_GOING:  'En cours',
  COMPLETED: 'Terminée',
};

// ── Composant ─
export default function QuestsPage() {

  // useState = une variable que React surveille
  // Quand elle change → React réaffiche automatiquement la page
  // 'ALL' = valeur par défaut = pas de filtre
  const [statusFilter, setStatusFilter]       = useState<QuestStatus | 'ALL'>('ALL');
  const [difficultyFilter, setDifficultyFilter] = useState<Difficulty | 'ALL'>('ALL');

  // ── Calcul des quêtes filtrées ──
  // .filter() = parcourt le tableau et garde seulement ce qui passe la condition
  const filteredQuests = FAKE_QUESTS.filter(quest => {
    // Si le filtre est 'ALL' → on garde tout
    // Sinon → on garde seulement si le statut correspond au filtre
    const statusOk    = statusFilter     === 'ALL' || quest.status     === statusFilter;
    const difficultyOk = difficultyFilter === 'ALL' || quest.difficulty === difficultyFilter;

    // Les deux conditions doivent être vraies en même temps
    return statusOk && difficultyOk;
  });

  return (
    <div className={styles.page}>
      <h1 className={styles.title}>Quêtes</h1>

      {/* ── Filtres ── */}
      <div className={styles.filters}>

        {/* Filtre par statut */}
        <div className={styles.filterGroup}>
          <span className={styles.filterLabel}>Statut :</span>

          {/* Bouton "Toutes" */}
          <button
            className={`${styles.filterBtn} ${statusFilter === 'ALL' ? styles.active : ''}`}
            onClick={() => setStatusFilter('ALL')}
          >
            Toutes
          </button>

          {/* On crée un bouton pour chaque statut possible */}
          {(['AVAILABLE', 'ON_GOING', 'COMPLETED'] as QuestStatus[]).map(s => (
            <button
              key={s}
              className={`${styles.filterBtn} ${statusFilter === s ? styles.active : ''}`}
              onClick={() => setStatusFilter(s)}
            >
              {STATUS_LABEL[s]}
            </button>
          ))}
        </div>

        {/* Filtre par difficulté */}
        <div className={styles.filterGroup}>
          <span className={styles.filterLabel}>Difficulté :</span>

          <button
            className={`${styles.filterBtn} ${difficultyFilter === 'ALL' ? styles.active : ''}`}
            onClick={() => setDifficultyFilter('ALL')}
          >
            Toutes
          </button>

          {(['EASY', 'MEDIUM', 'HARD', 'EPIC'] as Difficulty[]).map(d => (
            <button
              key={d}
              className={`${styles.filterBtn} ${styles[d]} ${difficultyFilter === d ? styles.active : ''}`}
              onClick={() => setDifficultyFilter(d)}
            >
              {d}
            </button>
          ))}
        </div>

      </div>

      {/* ── Tableau des quêtes ── */}
      {/* Si aucune quête ne correspond aux filtres → message */}
      {filteredQuests.length === 0 ? (
        <p className={styles.empty}>Aucune quête ne correspond à ces filtres.</p>
      ) : (
        <table className={styles.table}>
          <thead>
            <tr>
              <th>Titre</th>
              <th>Difficulté</th>
              <th>Statut</th>
              <th>Niv. min</th>
              <th>Or 🪙</th>
              <th>XP ⭐</th>
            </tr>
          </thead>
          <tbody>
            {filteredQuests.map(quest => (
              <tr key={quest.id}>
                <td>
                  <div className={styles.questTitle}>{quest.title}</div>
                  <div className={styles.questDesc}>{quest.description}</div>
                </td>
                <td>
                  <span className={`${styles.badge} ${styles[quest.difficulty]}`}>
                    {quest.difficulty}
                  </span>
                </td>
                <td>
                  <span className={`${styles.statusBadge} ${styles[quest.status]}`}>
                    {STATUS_LABEL[quest.status]}
                  </span>
                </td>
                <td className={styles.center}>{quest.minLevel}</td>
                <td className={styles.center}>{quest.goldReward}</td>
                <td className={styles.center}>{quest.xpReward}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}