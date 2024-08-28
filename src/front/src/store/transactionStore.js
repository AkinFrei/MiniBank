import create from 'zustand';

const useTransactionStore = create((set) => ({
    accounts: [],
    setAccounts: (transactions) => set({ transactions }),
}));

export default useTransactionStore;