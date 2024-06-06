import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, Card, CardContent, Typography, CircularProgress } from '@mui/material';
import './HomePage.css';

function HomePage() {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showPunchline, setShowPunchline] = useState(false);

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/jokes/random')
            .then(response => {
                setData(response.data);
                setLoading(false);
            })
            .catch(error => {
                setError('Ошибка при загрузке данных');
                setLoading(false);
            });
    }, []);

    const handleShowPunchline = () => {
        setShowPunchline(true);
    };

    if (loading) {
        return <div className="loading-container"><CircularProgress /></div>;
    }

    if (error) {
        return <div className="error-container">{error}</div>;
    }

    return (
        <Container maxWidth="sm" className="joke-container">
            <Typography variant="h4" align="center" gutterBottom className="neon-text">
                Random Joke
            </Typography>
            <Card className="joke-card">
                <CardContent>
                    {data.author && ( // Проверяем наличие данных об авторе
                        <Typography variant="body2" className="joke-text">Author: {data.author.firstName} {data.author.lastName}</Typography>
                    )}
                    <Typography variant="body1" className="neon-text">{data.setup}</Typography>
                    {showPunchline && (
                        <Typography variant="body1" className="neon-text">{data.punchline}</Typography>
                    )}
                    {!showPunchline && (
                        <button className="neon-button" onClick={handleShowPunchline}><div className="neon-text">???</div></button>
                    )}
                </CardContent>
            </Card>
        </Container>
    );
}

export default HomePage;
