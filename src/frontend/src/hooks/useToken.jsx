import { useState } from "react";

const useToken = () => {
  const getToken = () => {
    const tokenString = localStorage.getItem('jwtToken');
    return tokenString || undefined;
  };

  const [token, setToken] = useState(getToken());

  const saveToken = (userToken) => {
    const token = (JSON.stringify(userToken));
    localStorage.setItem('jwtToken', token);
    setToken(token);
  };

  return {
    setToken: saveToken,
    token
  };
};

export default useToken;
