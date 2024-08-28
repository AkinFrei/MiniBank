// import React, { useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import useAuthStore from '../store/authStore';
//
// function LoginPage() {
//     const [email, setEmail] = useState('');
//     const [password, setPassword] = useState('');
//     const { setToken } = useAuthStore();
//     const navigate = useNavigate();
//
//     const handleLogin = async (e) => {
//         e.preventDefault();
//         try {
//             const response = await fetch('/api/login', {
//                 method: 'POST',
//                 headers: {
//                     'Content-Type': 'application/json',
//                 },
//                 body: JSON.stringify({ email, password }),
//             });
//             const data = await response.json();
//             if (response.ok) {
//                 setToken(data.token);
//                 navigate('/main'); // Redirect to protected page
//             } else {
//                 console.error(data.message);
//             }
//         } catch (error) {
//             console.error('Error logging in:', error);
//         }
//     };
//
//     return (
//         <div>
//             <h1>Login</h1>
//             <form onSubmit={handleLogin}>
//                 <input
//                     type="email"
//                     value={email}
//                     onChange={(e) => setEmail(e.target.value)}
//                     required
//                 />
//                 <input
//                     type="password"
//                     value={password}
//                     onChange={(e) => setPassword(e.target.value)}
//                     required
//                 />
//                 <button type="submit">Login</button>
//             </form>
//         </div>
//     );
// }
//
// export default LoginPage;
