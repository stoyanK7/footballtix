import useToken from '../hooks/useToken';

const Logout = () => {
  localStorage.removeItem('jwtToken');
  const [setToken] = useToken();
  setToken(undefined);
};

export default Logout;
