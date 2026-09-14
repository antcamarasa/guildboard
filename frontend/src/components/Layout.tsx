import { NavLink, Outlet } from 'react-router-dom';
import styles from './Layout.module.css';

export default function Layout() {
  return (
    <div className={styles.wrapper}>
      <header className={styles.topbar}>
        <span className={styles.logo}>Guild Board</span>
        <nav className={styles.nav}>
          <NavLink to="/" end className={({ isActive }) => isActive ? styles.active : ''}>
            Dashboard
          </NavLink>
          <NavLink to="/quests" className={({ isActive }) => isActive ? styles.active : ''}>
            Quêtes
          </NavLink>
          <NavLink to="/adventurers" className={({ isActive }) => isActive ? styles.active : ''}>
            Aventuriers
          </NavLink>
        </nav>
      </header>

      <main className={styles.main}>
        <Outlet />
      </main>
    </div>
  );
}

//outlet est un composant de react-router-dom qui permet d'afficher le contenu de la page en fonction de l'URL. Il est utilisé ici pour afficher le contenu des pages Dashboard, Quests et Adventurers.