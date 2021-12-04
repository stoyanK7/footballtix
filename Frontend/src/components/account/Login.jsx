import { Link, Redirect } from 'react-router-dom';

import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import { useState } from 'react';
import useToken from '../../hooks/useToken';

const Login = () => {
  const [fields, setFields] = useState({
    email: '',
    password: ''
  });
  const { setToken } = useToken();
  const [redirect, setRedirect] = useState();
  const [response, setResponse] = useState();
  const [responseError, setResponseError] = useState();

  const onChangeHandler = e => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const onSubmitHandler = async e => {
    e.preventDefault();
    await axios.post('/api/authenticate', { ...fields })
      .then(res => {
        setToken(res.data.jwt);
        setRedirect({ pathname: '/', state: { message: 'Welcome.' } });
      })
      .catch(err => {
        if (err.response.data.status === 'LOCKED' &&
          window.confirm(err.response.data.message)) {
          axios.post('/api/register/send-token', { email: fields.email })
            .then(response => setResponse('Check your email for confirmation token.'))
            .catch(error => { if (err.response) setResponseError(err.response.data.message) });
        }
        if (err.response && err.response.data.status !== 'LOCKED') setResponseError(err.response.data.message);
      });
  };

  if (redirect) return <Redirect to={redirect} />;

  return (
    <>
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
      {response && <MessageBox content={response} setContent={setResponse} type='success' />}
      <div className='form-wrapper'>
        <img src='/img/ticket.webp' alt='Ticket' />
        <h1>Log in</h1>
        <form onSubmit={onSubmitHandler}>
          <input
            name='email'
            placeholder='Email'
            type='email'
            onChange={onChangeHandler}
            required />
          <input
            name='password'
            placeholder='Password'
            type='password'
            onChange={onChangeHandler}
            required />
          <button type='submit' disabled={responseError}>Log in</button>
          <p>
            <Link to='/register'>Don't have an account?</Link>
            <br />
            <br />
            <Link to='/forgot-password'>Forgot your password?</Link>
          </p>
        </form>
      </div>
    </>
  );
};

export default Login;
