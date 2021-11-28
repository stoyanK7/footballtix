import '../../css/account/Profile.css';

import { useEffect, useState } from 'react';

import Loading from '../shared/Loading';
import MessageBox from '../shared/MessageBox';
import { Redirect } from 'react-router';
import axios from 'axios';
import jwt from 'jwt-decode';
import useFetch from '../../hooks/useFetch';
import useToken from '../../hooks/useToken';

const Profile = () => {
  const { token, deleteToken } = useToken();
  const { fetchData: info, isFetching, fetchError } = useFetch('/api/users/info', { params: { jwt: token } });
  const [redirect, setRedirect] = useState();
  const [responseError, setResponseError] = useState();
  const [infoFields, setInfoFields] = useState({
    newEmail: '',
    newFullName: ''
  });
  const [passwordFields, setPasswordFields] = useState({
    currentPassword: '',
    newPassword: '',
    confirmPassword: ''
  });

  useEffect(() => {
    if (info) setInfoFields({ newEmail: info.email, newFullName: info.fullName })
  }, [info]);


  const onChangeInfoFields = e => {
    setInfoFields({
      ...infoFields,
      [e.target.name]: e.target.value
    });
  };

  const onChangePasswordFields = e => {
    setPasswordFields({
      ...passwordFields,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmitEditInfo = async e => {
    e.preventDefault();
    await axios.put('/api/users/info', { oldEmail: jwt(token).sub, ...infoFields })
      .then(res => {
        deleteToken();
        setRedirect({ pathname: '/login', state: { message: 'Info successfully changed. You can now log in again.' } });
      })
      .catch(err => {
        if (err.response) setResponseError(err.response.data.message);
        else setResponseError('Something went wrong. Please try again later.');
      });
  };

  const handleSubmitUpdatePassword = async e => {
    e.preventDefault();
    await axios.put('/api/users/password', { jwt: token, ...passwordFields })
      .then(res => {
        deleteToken();
        setRedirect({ pathname: '/login', state: { message: 'Password successfully changed. You can now log in again.' } });
      })
      .catch(err => {
        if (err.response) setResponseError(err.response.data.message);
        else setResponseError('Something went wrong. Please try again later.');
      });
  };

  const handleDeleteAccount = async e => {
    if (window.confirm('Are you sure?')) {
      await axios.delete('/api/users?jwt=' + token)
        .then(res => {
          deleteToken();
          setRedirect({ pathname: '/login', state: { message: 'Account deleted successfully.' } });
        })
        .catch(err => {
          if (err.response) setResponseError(err.response.data.message);
          else setResponseError('Something went wrong. Please try again later.');
        });
    }
  };

  if (redirect) return <Redirect to={redirect} />;

  return (
    <>
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
      {isFetching && <Loading />}
      {fetchError && <MessageBox content={fetchError} type='error' />}
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
              defaultValue={infoFields.newEmail}
              onChange={onChangeInfoFields}
              required />
            <input
              id='fullName'
              name='newFullName'
              placeholder='Full name'
              type='text'
              defaultValue={infoFields.newFullName}
              onChange={onChangeInfoFields}
              required />
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
              onChange={onChangePasswordFields}
              required />
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
            <button type='submit'>Save password</button>
          </form>
          <button className='delete-button' onClick={handleDeleteAccount}>Delete account</button>
        </div>
      </div>}
    </>
  );
};

export default Profile;
