import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Header from "../../components/Header/Header";
import { Card, CardContent, Typography, Grid, CircularProgress } from "@mui/material";
import './TransactionDetailPage.css';

const TransactionDetailPage = () => {
    const { transactionId } = useParams();
    const [transaction, setTransaction] = useState(null);

    useEffect(() => {
        fetch(`/api/transactions/${transactionId}`)
            .then(response => response.json())
            .then(data => setTransaction(data))
            .catch(error => console.error('Error fetching transaction details:', error));
    }, [transactionId]);

    if (!transaction) {
        return (
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '2rem' }}>
                <CircularProgress />
            </div>
        );
    }

    return (
        <>
            <Header />
            <main className="main-container">
                <div className="data-grid-wrapper" style={{ display: 'flex', justifyContent: 'center', marginTop: '2rem' }}>
                    <Card sx={{ maxWidth: 600, width: '100%', padding: '1rem' }}>
                        <CardContent>
                            <Typography variant="h5" component="div" gutterBottom>
                                Transaction Details
                            </Typography>
                            <Grid container spacing={2}>
                                <Grid item xs={12} sm={6}>
                                    <Typography variant="body1" component="p">
                                        <strong>From:</strong> {transaction.from}
                                    </Typography>
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <Typography variant="body1" component="p">
                                        <strong>To:</strong> {transaction.to}
                                    </Typography>
                                </Grid>
                                <Grid item xs={12}>
                                    <Typography variant="body1" component="p">
                                        <strong>Amount:</strong> {transaction.amount}
                                    </Typography>
                                </Grid>
                                <Grid item xs={12}>
                                    <Typography variant="body1" component="p">
                                        <strong>Transaction Date:</strong> {transaction.transaction_date}
                                    </Typography>
                                </Grid>
                                <Grid item xs={12}>
                                    <Typography variant="body1" component="p">
                                        <strong>Status:</strong> {transaction.status}
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

export default TransactionDetailPage;
