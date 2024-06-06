import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import HomePage from './pages/HomePage';
import AuthorsPage from './pages/AuthorsPage';
import CollectionsPage from "./pages/CollectionsPage";
import "./App.css"

function App() {
    return (
        <Router>
            <div className="App">
                <Header />
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/authors" element={<AuthorsPage />} />
                    <Route path="/collections" element={<CollectionsPage />} />
                </Routes>
                <Footer />
            </div>
        </Router>
    );
}

export default App;
