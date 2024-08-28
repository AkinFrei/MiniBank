import React, { useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import useAccountsStore from '../../store/accountStore';
import Header from "../../components/Header/Header";
import './Accounts.css';
import {Link} from "react-router-dom";
import {CircularProgress} from "@mui/material";

function Accounts() {
    const { accounts, setAccounts } = useAccountsStore();

    useEffect(() => {
        fetch('/api/accounts')
            .then(response => response.json())
            .then(data => setAccounts(data))
            .catch(error => console.error('Error fetching accounts:', error));
    }, [setAccounts]);

    const columns = [
        { field: 'name', headerName: 'Name', width: 200 },
        { field: 'number', headerName: 'Number', width: 200 },
        { field: 'balance', headerName: 'Surname', width: 200 },
        { field: 'created_at', headerName: 'Created At', width: 200 },
        { field: 'updated_at', headerName: 'Updated At', width: 200
            , renderCell: (params) => (
                <Link to={`/account/${params.value}`} className="btn btn-link">{params.value}</Link>
            ) },
    ];


    const rows = accounts.map(account => ({
        name: account.name,
        number: account.number,
        balance: account.balance,
        created_at: account.created_at,
        updated_at: account.updated_at,
    }));

    return (
        <>
            <Header/>
            <main className="main-container">
                <div className="data-grid-wrapper">
                    <div style={{ width: '100%', height: '100%' }}>
                        <DataGrid rows={rows} columns={columns} pageSize={5} />
                    </div>
                </div>
            </main>
        </>
    );
}

export default Accounts;
