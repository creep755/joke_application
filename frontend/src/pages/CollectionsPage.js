import React, { useEffect, useState } from 'react';
import axios from 'axios';

function CollectionsPage() {
    const [data, setData] = useState(null); // Начальное значение null для ожидания данных
    const [loading, setLoading] = useState(true); // Состояние загрузки
    const [error, setError] = useState(null); // Состояние ошибки

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/jokes/random')
            .then(response => {
                setData(response.data); // Устанавливаем данные
                setLoading(false); // Отключаем состояние загрузки
            })
            .catch(error => {
                setError('Ошибка при загрузке данных'); // Устанавливаем ошибку
                setLoading(false); // Отключаем состояние загрузки
            });
    }, []);

    if (loading) {
        return <p>Загрузка...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <div>
            <h1>Joke Collections</h1>
            {data && (
                <div>
                    <p><strong>Setup:</strong> {data.setup}</p>
                    <p><strong>Punchline:</strong> {data.punchline}</p>

                </div>
            )}
        </div>
    );
}

export default CollectionsPage;
