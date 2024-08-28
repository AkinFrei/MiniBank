// import React, { useState } from 'react';
// import { useNavigate } from 'react-router-dom';
//
// function RegisterPage() {
//     const [email, setEmail] = useState('');
//     const [password, setPassword] = useState('');
//     const navigate = useNavigate();
//
//     const handleRegister = async (e) => {
//         e.preventDefault();
//         try {
//             const response = await fetch('/api/register', {
//                 method: 'POST',
//                 headers: {
//                     'Content-Type': 'application/json',
//                 },
//                 body: JSON.stringify({ email, password }),
//             });
//             if (response.ok) {
//                 // After registration, navigate to login page
//                 navigate('/login');
//             } else {
//                 console.error('Registration failed');
//             }
//         } catch (error) {
//             console.error('Error registering:', error);
//         }
//     };
//
//     return (
//         <div>
//             <h1>Register</h1>
//             <form onSubmit={handleRegister}>
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
//                 <button type="submit">Register</button>
//             </form>
//         </div>
//     );
// }
//
// export default RegisterPage;
