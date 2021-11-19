import { Link, Redirect } from 'react-router-dom';
import { useEffect, useState } from 'react';

import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import calcReadTime from '../../util/calcReadTime';
import useMessage from '../../hooks/useMessage';

const ForgotPassword = () => {
  const [email, setEmail] = useState('');

  const onChangeHandler = e => setEmail(e.target.value)

  const [error, setError] = useState();
  const [message, setMessage] = useState();

  const onSubmitHandler = async e => {
    e.preventDefault();

    await axios.post('/api/authenticate/forgot-password', { email })
      .then(res => {
        setMessage(res.data);
      })
      .catch(err => { if (err.response) setError(err.response.data.message) });
  };

  useEffect(() => {
    if (error) {
      const timeout = calcReadTime(error);
      setTimeout(() => setError(null), timeout + 500)
    }
  }, [error]);


  return (
    <>
      {message && <MessageBox content={message} type='success' />}
      {error && <MessageBox content={error} type='error' />}
      <div className='form-wrapper'>
        <img src='/img/ticket.png' alt='Ticket' />
        <h1>Forgot password</h1>
        <form onSubmit={onSubmitHandler}>
          <input
            name='email'
            placeholder='Email'
            type='email'
            onChange={onChangeHandler}
            required />
          <button type='submit' disabled={error}>Send me an email</button>
        </form>
      </div>
    </>
  );
};

export default ForgotPassword;
