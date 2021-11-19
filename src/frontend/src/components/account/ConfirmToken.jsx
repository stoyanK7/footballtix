import { Redirect, useParams } from "react-router";

import axios from "axios";
import { useState } from "react";

const ConfirmToken = () => {
  const { confirmationToken } = useParams();
  const [redirect, setRedirect] = useState(false);
  const link = '/api/register/confirm?token=' + confirmationToken;

  axios.get(link)
    .then((res) => {
      setRedirect(true);
    })

  if (redirect) return <Redirect to={{ pathname: '/login', state: { message: 'Account is enabled. You can log in' } }} />;

  return null;
};

export default ConfirmToken;
