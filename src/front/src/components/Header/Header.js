import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

function Header() {
    return (
        <header className="header">
            <nav className="nav">
                <Link to="/accounts" className="nav-item">Accounts</Link>
                <Link to="/transactions" className="nav-item">Transactions</Link>
                <Link to="/logout" className="nav-item">Log Out</Link>
            </nav>
        </header>
    );
}

export default Header;
