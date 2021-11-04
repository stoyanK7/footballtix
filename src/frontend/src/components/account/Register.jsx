import { Link, Redirect } from 'react-router-dom';
import { useEffect, useState } from 'react';

import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import calcReadTime from '../../util/calcReadTime';

const Register = () => {
  const [fields, setFields] = useState({
    email: '',
    fullName: '',
    password: '',
  });

  const onChangeHandler = e => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const [successfulRegister, setSuccessfulRegister] = useState(false);
  const [error, setError] = useState();

  const onSubmitHandler = async e => {
    e.preventDefault();

    await axios.post('/api/register', { ...fields })
      .then(res => setSuccessfulRegister(true))
      .catch(err => { if (err.response) setError(err.response.data.message) });
  };

  useEffect(() => {
    if (error) {
      const timeout = calcReadTime(error);
      setTimeout(() => setError(null), timeout + 500)
    }
  }, [error]);

  if (successfulRegister) return <Redirect to={{ pathname: '/login', state: { message: 'Registration successful. Please confirm your email.' } }} />;

  return (
    <>
      {error && <MessageBox content={error} type='error' />}
      <div className='form-wrapper'>
        <img src='/img/ticket.png' alt='Ticket' />
        <h1>Register</h1>
        <form onSubmit={onSubmitHandler}>
          <input
            name='email'
            placeholder='Email'
            type='email'
            defaultValue={fields.email}
            onChange={onChangeHandler}
            required />
          <input
            name='fullName'
            placeholder='Full name'
            type='text'
            defaultValue={fields.fullName}
            onChange={onChangeHandler}
            required />
          <input
            name='password'
            placeholder='Password'
            type='password'
            defaultValue={fields.password}
            onChange={onChangeHandler}
            required
            minLength='6'
            pattern='^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@_$!%*?&])[A-Za-z\d@_$!%*?&]{6,}$'
            title='Minimum six characters, at least one uppercase letter, one lowercase letter, one number and one special character:' />
          <p><Link to='/login'>Already have an account?</Link></p>
          <button type='submit' disabled={error}>Register</button>
        </form>
      </div>
    </>
  );
};

export default Register;
