import jwt from 'jwt-decode';
import { useState } from 'react';

const useToken = () => {
  const getToken = () => {
    const tokenString = localStorage.getItem('jwtToken');
    return tokenString || undefined;
  };

  const [token, setToken] = useState(getToken());

  const saveToken = (userToken) => {
    localStorage.setItem('jwtToken', userToken);
    setToken(token);
  };

  const isAdmin = () => jwt(getToken()).role === 'ADMIN';

  const deleteToken = () => {
    localStorage.removeItem('jwtToken');
    setToken(null);
  };

  return {
    setToken: saveToken,
    token,
    deleteToken,
    isAdmin
  };
};

export default useToken;
