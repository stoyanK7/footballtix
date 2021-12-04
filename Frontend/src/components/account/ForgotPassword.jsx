import { Link } from 'react-router-dom';
import MessageBox from '../shared/MessageBox';
import { Redirect } from 'react-router';
import axios from 'axios';
import { useState } from 'react';

const ForgotPassword = () => {
  const [email, setEmail] = useState('');
  const [redirect, setRedirect] = useState();
  const [responseError, setResponseError] = useState();

  const onChangeHandler = e => setEmail(e.target.value);
  const onSubmitHandler = async e => {
    e.preventDefault();
    await axios.post('/api/authenticate/forgot-password', { email })
      .then(res => setRedirect({ pathname: '/login', state: { message: 'Password reset link sent. Check your email.' } }))
      .catch(err => {
        if (err.response) setResponseError(err.response.data.message);
        else setResponseError('Something went wrong. Please try again later.');
      });
  };

  if (redirect) return <Redirect to={redirect} />;

  return (
    <>
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
      <div className='form-wrapper'>
        <img src='/img/ticket.webp' alt='Ticket' />
        <h1>Forgot password</h1>
        <form onSubmit={onSubmitHandler}>
          <input
            name='email'
            placeholder='Email'
            type='email'
            onChange={onChangeHandler}
            required />
          <button type='submit' disabled={responseError}>Send me an email</button>
          <Link to='/login'>Back to login</Link>
        </form>
      </div>
    </>
  );
};

export default ForgotPassword;
