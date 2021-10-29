import { Link, Redirect } from 'react-router-dom';

import axios from 'axios';
import { useState } from 'react';

const Register = () => {
  const [fields, setFields] = useState({
    email: null,
    fullName: null,
    password: null,
  });

  const [redirect, setRedirect] = useState(false);

  const onChangeHandler = e => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const onSubmitHandler = async e => {
    e.preventDefault();

    await axios.post('/api/register', { ...fields })
      .then(res => {
        setRedirect(true);
      })
      .catch(err => {
        // TODO: display the error
        console.log(err);
      });
  };

  if (redirect) return <Redirect to='/login' />;

  return (
    <div className='form-wrapper'>
      <img src='/img/ticket.png' alt='Ticket' />
      <h1>Register</h1>
      <form onSubmit={onSubmitHandler}>
        <input
          name='email'
          placeholder='Email'
          type='email'
          onChange={onChangeHandler} />
        <input
          name='fullName'
          placeholder='Full name'
          type='text'
          onChange={onChangeHandler} />
        <input
          name='password'
          placeholder='Password'
          type='password'
          onChange={onChangeHandler} />
        <p><Link to='/login'>Already have an account?</Link></p>
        <button type='submit'>Register</button>
      </form>
    </div>
  );
};

export default Register;
