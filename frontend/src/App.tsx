import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import DashboardPage from './pages/DashboardPage';
import QuestsPage from './pages/QuestsPage';
import AdventurersPage from './pages/AdventurersPage';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout />}>
          <Route path="/" element={<DashboardPage />} />
          <Route path="/quests" element={<QuestsPage />} />
          <Route path="/adventurers" element={<AdventurersPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}