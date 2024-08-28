import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Header from '../../components/Header/Header';
import { Card, CardContent, Typography, Grid, CircularProgress } from '@mui/material';
import './AccountDetailPage.css'; // Import the CSS file

const AccountDetailPage = () => {
    const { accountId } = useParams();
    const [account, setAccount] = useState(null);

    useEffect(() => {
        // Fetch account details from the backend using accountId
        fetch(`/api/accounts/${accountId}`)
            .then(response => response.json())
            .then(data => setAccount(data))
            .catch(error => console.error('Error fetching account details:', error));
    }, [accountId]);

    if (!account) {
        return (
            <div className="main-container">
                <CircularProgress />
            </div>
        );
    }

    return (
        <>
            <Header />
            <main className="main-container">
                <div className="data-grid-wrapper">
                    <Card className="card">
                        <CardContent className="card-content">
                            <Typography className="typography-header" gutterBottom>
                                Account Details
                            </Typography>
                            <Grid container spacing={2}>
                                <Grid item xs={12} sm={6}>
                                    <Typography className="typography-body">
                                        <strong>Name:</strong> {account.name}
                                    </Typography>
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <Typography className="typography-body">
                                        <strong>Number:</strong> {account.number}
                                    </Typography>
                                </Grid>
                                <Grid item xs={12}>
                                    <Typography className="typography-body">
                                        <strong>Balance:</strong> {account.balance}
                                    </Typography>
                                </Grid>
                                <Grid item xs={12}>
                                    <Typography className="typography-body">
                                        <strong>Created At:</strong> {new Date(account.created_at).toLocaleString()}
                                    </Typography>
                                </Grid>
                                <Grid item xs={12}>
                                    <Typography className="typography-body">
                                        <strong>Updated At:</strong> {new Date(account.updated_at).toLocaleString()}
                                    </Typography>
                                </Grid>

                            </Grid>
                        </CardContent>
                    </Card>
                </div>
            </main>
        </>
    );
};

export default AccountDetailPage;
