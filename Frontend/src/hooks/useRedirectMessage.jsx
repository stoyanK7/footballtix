import { useLocation } from 'react-router';

const useRedirectMessage = () => {
  const location = useLocation();
  return location.state !== undefined ? location.state.message : null;
};

export default useRedirectMessage;
