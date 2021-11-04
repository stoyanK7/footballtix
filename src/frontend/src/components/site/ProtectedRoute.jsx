import { Redirect, Route } from "react-router-dom";

import React from "react";
import useToken from '../../hooks/useToken';

const ProtectedRoute = ({ children, ...props }) => {
  const { token } = useToken();

  if (!token) return <Redirect to={{ pathname: '/login', state: { message: 'You need to login first.' } }} />;

  return <Route {...props}>{children}</Route>;
}

export default ProtectedRoute;
