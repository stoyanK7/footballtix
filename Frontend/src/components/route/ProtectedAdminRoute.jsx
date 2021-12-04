import { Redirect, Route } from "react-router-dom";

import React from "react";
import useToken from '../../hooks/useToken';

const ProtectedAdminRoute = ({ children, ...props }) => {
  const { token, isAdmin } = useToken();

  if (!token) return <Redirect to={{ pathname: '/login', state: { message: 'You need to login first.' } }} />;

  if (!isAdmin()) return <Redirect to={{ pathname: '/', state: { message: 'You do not have permissions to view this page.' } }} />;

  return <Route {...props}>{children}</Route>;
}

export default ProtectedAdminRoute;
