import { Redirect } from 'react-router-dom';

const Logout = () => {
  localStorage.removeItem('jwtToken');
};

export default Logout;