import { Link, Redirect } from 'react-router-dom';

import axios from 'axios';
import { useState } from 'react';
import useToken from '../hooks/useToken';

const Login = () => {
  const [fields, setFields] = useState({
    email: null,
    password: null
  });

  const { setToken } = useToken();

  const [redirect, setRedirect] = useState(false);

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
        setRedirect(true);
      })
      .catch(err => {
        // TODO: implement user-friendly way
        console.log(err);
      });
  };

  if (redirect) return <Redirect to='/' />;

  return (
    <div className='form-wrapper'>
      <img src='/img/ticket.png' alt='Ticket' />
      <h1>Log in</h1>
      <form onSubmit={onSubmitHandler}>
        <input
          name='email'
          placeholder='Email'
          type='email'
          onChange={onChangeHandler} />
        <input
          name='password'
          placeholder='Password'
          type='password'
          onChange={onChangeHandler} />
        <p>
          <Link to='/register'>Don't have an account?</Link>
          <br />
          <br />
          <Link to='/forgot-password'>Forgot your password?</Link>
        </p>
        <button type='submit'>Log in</button>
      </form>
    </div>
  );
};

export default Login;
