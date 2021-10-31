import '../../css/account/Profile.css';

import Loading from '../shared/Loading';
import Logout from './Logout';
import MessageBox from '../shared/MessageBox';
import { Redirect } from 'react-router';
import axios from 'axios';
import jwt from 'jwt-decode';
import useGET from '../../hooks/useGET';
import { useState } from 'react';
import useToken from '../../hooks/useToken';

const Profile = () => {
  const [infoFields, setInfoFields] = useState({
    newEmail: '',
    newFullName: ''
  });

  const onChangeInfoFields = e => {
    setInfoFields({
      ...infoFields,
      [e.target.name]: e.target.value
    });
  };

  const [passwordFields, setPasswordFields] = useState({
    currentPassword: '',
    newPassword: '',
    confirmPassword: ''
  });

  const onChangePasswordFields = e => {
    setPasswordFields({
      ...passwordFields,
      [e.target.name]: e.target.value
    });
  };

  const { token } = useToken();

  const { data: info, isPending, error } = useGET('/api/users/info', { params: { jwt: token } });

  const [redirect, setRedirect] = useState(false);

  const handleSubmitEditInfo = async e => {
    e.preventDefault();

    await axios.put('/api/users/info', {
      oldEmail: jwt(token).sub,
      ...infoFields
    })
      .then(res => {
        console.log(res);
        Logout();
        setRedirect(true);
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  const handleSubmitUpdatePassword = async e => {
    e.preventDefault();

    await axios.put('/api/users/password', {
      jwt: token,
      ...passwordFields
    })
      .then(res => {
        Logout();
        setRedirect(true);
      })
      .catch(err => {
        console.log(err);
      });
  };

  if (redirect) return <Redirect to='/login' />;

  return (
    <>
      {isPending && <Loading />}
      {error && <MessageBox content={error} type='error' />}
      {info && <div className='profile'>
        <img src='/img/user-settings.png' alt='' />
        <div>
          <h2>Edit information</h2>
          <form onSubmit={handleSubmitEditInfo}>
            <input
              id='email'
              name='newEmail'
              placeholder='E-mail'
              type='email'
              defaultValue={info.email}
              onChange={onChangeInfoFields} />
            <input
              id='fullName'
              name='newFullName'
              placeholder='Full name'
              type='text'
              defaultValue={info.fullName}
              onChange={onChangeInfoFields} />
            <button type='submit'>Save information</button>
          </form>
        </div>
        <div>
          <h2>Edit password</h2>
          <form onSubmit={handleSubmitUpdatePassword}>
            <input
              name='currentPassword'
              placeholder='Current password'
              type='password'
              onChange={onChangePasswordFields} />
            <input
              name='newPassword'
              placeholder='New password'
              type='password'
              onChange={onChangePasswordFields} />
            <input
              name='confirmPassword'
              placeholder='Confirm password'
              type='password'
              onChange={onChangePasswordFields} />
            <button type='submit'>Save password</button>
          </form>
        </div>
      </div>}
    </>
  );
};

export default Profile;
