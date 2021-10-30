import { useLocation } from "react-router";

const useMessage = () => {
  const location = useLocation();
  return location.state !== undefined ? location.state.message : null;
};

export default useMessage;
