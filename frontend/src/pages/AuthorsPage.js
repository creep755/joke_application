import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, Card, CardContent, Typography, CircularProgress, TextField, Button, Dialog, DialogTitle, DialogContent, DialogActions, IconButton } from '@mui/material';
import { Delete as DeleteIcon, Edit as EditIcon } from '@mui/icons-material';
import './AuthorsPage.css';

function AuthorsPage() {
    const [authors, setAuthors] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [newAuthor, setNewAuthor] = useState({ firstName: '', lastName: '', nation: '' });
    const [openDialog, setOpenDialog] = useState(false);
    const [isEditing, setIsEditing] = useState(false);
    const [currentAuthorId, setCurrentAuthorId] = useState(null);
    const [jokes, setJokes] = useState([]);
    const [selectedAuthorId, setSelectedAuthorId] = useState(null);

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/authors')
            .then(response => {
                setAuthors(response.data);
                setLoading(false);
            })
            .catch(error => {
                setError('Ошибка при загрузке данных');
                setLoading(false);
            });
    }, []);

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setNewAuthor({ ...newAuthor, [name]: value });
    };

    const handleSubmit = () => {
        if (isEditing) {
            axios.put(`http://localhost:8080/api/v1/authors/${currentAuthorId}`, newAuthor)
                .then(response => {
                    setAuthors(authors.map(author => (author.id === currentAuthorId ? response.data : author)));
                    resetForm();
                })
                .catch(error => {
                    console.error('Ошибка при обновлении автора:', error);
                });
        } else {
            axios.post('http://localhost:8080/api/v1/authors', newAuthor)
                .then(response => {
                    setAuthors([...authors, response.data]);
                    resetForm();
                })
                .catch(error => {
                    console.error('Ошибка при добавлении автора:', error);
                });
        }
    };

    const handleDelete = (id) => {
        axios.delete(`http://localhost:8080/api/v1/authors/${id}`)
            .then(() => {
                setAuthors(authors.filter(author => author.id !== id));
            })
            .catch(error => {
                console.error('Ошибка при удалении автора:', error);
            });
    };

    const handleEdit = (author) => {
        setIsEditing(true);
        setCurrentAuthorId(author.id);
        setNewAuthor({ firstName: author.firstName, lastName: author.lastName, nation: author.nation });
        setOpenDialog(true);
    };

    const handleOpenDialog = () => {
        setOpenDialog(true);
    };

    const handleCloseDialog = () => {
        resetForm();
    };

    const resetForm = () => {
        setNewAuthor({ firstName: '', lastName: '', nation: '' });
        setIsEditing(false);
        setCurrentAuthorId(null);
        setOpenDialog(false);
    };

    const handleAuthorClick = (id) => {
        if (selectedAuthorId === id) {
            setSelectedAuthorId(null);
            setJokes([]);
        } else {
            setSelectedAuthorId(id);
            axios.get(`http://localhost:8080/api/v1/jokes/author/${id}`)
                .then(response => {
                    setJokes(response.data);
                })
                .catch(error => {
                    console.error('Ошибка при загрузке шуток:', error);
                });
        }
    };

    if (loading) {
        return <div className="loading-container"><CircularProgress /></div>;
    }

    if (error) {
        return <div className="error-container">{error}</div>;
    }

    return (
        <Container maxWidth="md" className="author-container">
            <Typography variant="h4" gutterBottom className="author-header">
                Authors
            </Typography>
            <div className="author-list">
                {authors.map(author => (
                    <Card key={author.id} className="author-card" onClick={() => handleAuthorClick(author.id)}>
                        <CardContent>
                            <Typography variant="h6">{author.firstName} {author.lastName}</Typography>
                            <Typography variant="body2" color="textSecondary">{author.nation}</Typography>
                            <div className="card-actions">
                                <IconButton onClick={(e) => { e.stopPropagation(); handleEdit(author); }} className="icon-button">
                                    <EditIcon style={{ color: '#ffffff' }} />
                                </IconButton>
                                <IconButton onClick={(e) => { e.stopPropagation(); handleDelete(author.id); }} className="icon-button">
                                    <DeleteIcon style={{ color: '#ffffff' }} />
                                </IconButton>
                            </div>
                        </CardContent>
                        {selectedAuthorId === author.id && (
                            <CardContent>
                                {jokes.length > 0 ? (
                                    jokes.map(joke => (
                                        <Card key={joke.id} className="joke-card">
                                            <CardContent>
                                                <Typography variant="body1" className="joke-text">
                                                    {joke.setup}
                                                </Typography>
                                                <Typography variant="body1" className="joke-text">
                                                    {joke.punchline}
                                                </Typography>
                                            </CardContent>
                                        </Card>
                                    ))
                                ) : (
                                    <Typography variant="body2" color="textSecondary">No jokes available</Typography>
                                )}
                            </CardContent>
                        )}
                    </Card>
                ))}
            </div>
            <Button variant="contained" onClick={handleOpenDialog} className="add-author-button">Add Author</Button>
            <Dialog open={openDialog} onClose={handleCloseDialog}>
                <DialogTitle>{isEditing ? 'Update Author' : 'Add New Author'}</DialogTitle>
                <DialogContent className="dialog-content">
                    <TextField
                        name="firstName"
                        label="First Name"
                        value={newAuthor.firstName}
                        onChange={handleInputChange}
                        variant="outlined"
                        fullWidth
                        className="input-field"
                    />
                    <TextField
                        name="lastName"
                        label="Last Name"
                        value={newAuthor.lastName}
                        onChange={handleInputChange}
                        variant="outlined"
                        fullWidth
                        className="input-field"
                    />
                    <TextField
                        name="nation"
                        label="Nation"
                        value={newAuthor.nation}
                        onChange={handleInputChange}
                        variant="outlined"
                        fullWidth
                        className="input-field"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog} className="dialog-button">Cancel</Button>
                    <Button onClick={handleSubmit} className="dialog-button">{isEditing ? 'Update' : 'Add'}</Button>
                </DialogActions>
            </Dialog>
        </Container>
    );
}

export default AuthorsPage;
