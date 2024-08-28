import create from 'zustand';

const useAccountsStore = create((set) => ({
    accounts: [],
    setAccounts: (accounts) => set({ accounts }),
}));

export default useAccountsStore;