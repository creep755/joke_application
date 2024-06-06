import React from 'react';
import "./Footer.css";

function Footer() {
    return (
        <footer className="footer">
            <p>
                © 2024 Creep Production
                <a href="https://www.instagram.com/requestbody" target="_blank" rel="noopener noreferrer">
                    <img src="instagram.png" alt="instagram" height="25" width="25" className="footer-icon" />
                </a>
            </p>
        </footer>
    );
}

export default Footer;
