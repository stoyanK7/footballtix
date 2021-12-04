import { Redirect, useLocation } from 'react-router';

import { Link } from 'react-router-dom';
import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import { useState } from 'react';

const ResetPassword = () => {
  const [redirect, setRedirect] = useState();
  const [responseError, setResponseError] = useState();
  const location = useLocation();
  const [passwordFields, setPasswordFields] = useState({
    newPassword: '',
    confirmPassword: ''
  });

  const onChangePasswordFields = e => {
    setPasswordFields({
      ...passwordFields,
      [e.target.name]: e.target.value
    });
  };

  const onSubmitHandler = async e => {
    e.preventDefault();
    await axios.post('/api/authenticate/reset-password' + location.search, { ...passwordFields })
      .then(res => setRedirect({ pathname: '/login', state: { message: 'Password successfully reset. You can now log in.' } }))
      .catch(err => { if (err.response) setResponseError(err.response.data.message) });
  };

  if (redirect) return <Redirect to={redirect} />;

  return (
    <>
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
      <div className='form-wrapper'>
        <img src='/img/ticket.png' alt='Ticket' />
        <h1>Reset your password</h1>
        <form onSubmit={onSubmitHandler}>
          <input
            name='newPassword'
            placeholder='New password'
            type='password'
            onChange={onChangePasswordFields}
            reqired
            minLength='6'
            pattern='^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@_$!%*?&])[A-Za-z\d@_$!%*?&]{6,}$'
            title='Minimum six characters, at least one uppercase letter, one lowercase letter, one number and one special character:' />
          <input
            name='confirmPassword'
            placeholder='Confirm password'
            type='password'
            onChange={onChangePasswordFields}
            required
            minLength='6'
            pattern='^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@_$!%*?&])[A-Za-z\d@_$!%*?&]{6,}$'
            title='Minimum six characters, at least one uppercase letter, one lowercase letter, one number and one special character:' />
          <button type='submit'>Reset password</button>
          <Link to='/login'>Back to login?</Link>
        </form>
      </div>
    </>
  );
};

export default ResetPassword;
