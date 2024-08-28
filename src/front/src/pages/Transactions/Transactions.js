import React, {useEffect} from "react";
import {Link, useNavigate} from "react-router-dom";
import Header from "../../components/Header/Header";
import {DataGrid} from "@mui/x-data-grid";
import useTransactionStore from "../../store/transactionStore";

function Transactions() {
    const { transactions, setTransactions } = useTransactionStore();
    const navigate = useNavigate();


    useEffect(() => {
        fetch('/api/transactions')
            .then(response => response.json())
            .then(data => (data))
            .catch(error => console.error('Error fetching transactions:', error));
    }, [setTransactions]);

    const columns = [
        { field: 'from', headerName: 'From', width: 200 },
        { field: 'to', headerName: 'To', width: 200 },
        { field: 'amount', headerName: 'Amount', width: 200 },
        { field: 'transaction_date', headerName: 'Transaction Date', width: 200 },
        { field: 'status', headerName: 'Status', width: 200
            , renderCell: (params) => (
                <Link to={`/transaction/${params.value}`} className="btn btn-link">{params.value}</Link>
            ) },
    ];

    const handleRowClick = (params) => {
        navigate(`/transactions/${params.id}`);
    };

    const rows = transactions.map(transaction => ({
        from: transaction.from,
        to: transaction.to,
        amount: transaction.amount,
        transaction_date: transaction.transaction_date,
        status: transaction.status,
    }));

    return (
        <>
            <Header/>
            <main className="main-container">
                <div className="data-grid-wrapper">
                    <div style={{ width: '100%', height: '100%' }}>
                        <DataGrid rows={rows} columns={columns} pageSize={5}
                                  onRowClick={handleRowClick} />
                    </div>
                </div>
            </main>
        </>
    );
}

export default Transactions;
