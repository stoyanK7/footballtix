import { Redirect, useParams } from "react-router";
import { useEffect, useState } from "react";

import MessageBox from "../shared/MessageBox";
import axios from "axios";

const ConfirmToken = () => {
  const { confirmationToken } = useParams();
  const [redirect, setRedirect] = useState(false);
  const [responseError, setResponseError] = useState();

  useEffect(() => {
    axios.get(`/api/register/confirm?token=${confirmationToken}`)
      .then(res => setRedirect({ pathname: '/login', state: { message: 'Account is enabled. You can log in.' } }))
      .catch(err => {
        if (err.response) setResponseError(err.response.data.message);
        else setResponseError('Something went wrong. Please try again later.');
      })
  }, [])

  if (redirect) return <Redirect to={redirect} />;

  return (
    <>
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
    </>
  )
};

export default ConfirmToken;
