import { Redirect } from 'react-router';
import { useEffect } from 'react';
import useToken from '../../hooks/useToken';

const Logout = () => {
  const { deleteToken } = useToken();

  useEffect(() => {
    deleteToken();
  }, []);
  
  return <Redirect to={{ pathname: '/login', state: { message: 'Until next time.' } }} />;
};

export default Logout;
