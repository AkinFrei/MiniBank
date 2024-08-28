import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Accounts from './pages/Accounts/Accounts';
import AccountDetailPage from "./pages/Accounts/AccountDetail";
import Transactions from "./pages/Transactions/Transactions";

function App() {
    return (
        <Router>
            <Routes>
                <Routes>
                    <Route path="/" element={<Accounts />} />
                    <Route path="/account/:id" element={<AccountDetailPage />} />
                    <Route path="/transactions/:id" element={<Transactions />} />
                    <Route path="/transactions/:id" element={<AccountDetailPage />} />
                </Routes>
            </Routes>
        </Router>
    );
}

export default App;
