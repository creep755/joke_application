import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

function Header() {
    return (
        <header className="header">
            <nav className="nav">
                <ul className="nav-list">
                    <li className="nav-item">
                        <Link to="/">
                            <img src="joker.png" alt="joker-img" width="35" height="35" className="nav-logo" />
                            Home
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/authors">Authors</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/collections">Collections</Link>
                    </li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;
