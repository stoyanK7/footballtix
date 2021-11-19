import { Link, Redirect } from 'react-router-dom';
import { useEffect, useState } from 'react';

import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import calcReadTime from '../../util/calcReadTime';
import useMessage from '../../hooks/useMessage';
import useToken from '../../hooks/useToken';

const Login = () => {
  const [fields, setFields] = useState({
    email: '',
    password: ''
  });

  const { setToken } = useToken();

  const onChangeHandler = e => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const [successfulLogin, setSuccessfulLogin] = useState(false);
  const [error, setError] = useState();

  const onSubmitHandler = async e => {
    e.preventDefault();

    await axios.post('/api/authenticate', { ...fields })
      .then(res => {
        setToken(res.data.jwt);
        setSuccessfulLogin(true);
      })
      .catch(err => { if (err.response) setError(err.response.data.message) });
  };

  useEffect(() => {
    if (error) {
      const timeout = calcReadTime(error);
      setTimeout(() => setError(null), timeout + 500)
    }
  }, [error]);

  const message = useMessage();

  if (successfulLogin) return <Redirect to={{ pathname: '/', state: { message: 'Welcome.' } }} />;

  return (
    <>
      {message && <MessageBox content={message} type='success' />}
      {error && <MessageBox content={error} type='error' />}
      <div className='form-wrapper'>
        <img src='/img/ticket.png' alt='Ticket' />
        <h1>Log in</h1>
        <form onSubmit={onSubmitHandler}>
          <input
            name='email'
            placeholder='Email'
            type='email'
            onChange={onChangeHandler} 
            required/>
          <input
            name='password'
            placeholder='Password'
            type='password'
            onChange={onChangeHandler} 
            required
            minLength='6'/>
          <button type='submit' disabled={error}>Log in</button>
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
