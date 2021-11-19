import { useEffect, useState } from 'react';

import { Link } from 'react-router-dom';
import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import calcReadTime from '../../util/calcReadTime';
import { useLocation } from 'react-router';

const ResetPassword = () => {
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

  const [error, setError] = useState();
  const [message, setMessage] = useState();

  const location = useLocation();

  const onSubmitHandler = async e => {
    e.preventDefault();

    await axios.post('/api/authenticate/reset-password' + location.search, {  ...passwordFields })
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
            <p><Link to='/login'>Back to login?</Link></p>
        </form>
      </div>
    </>
  );
};

export default ResetPassword;
